package net.reaper.ancientnature.common.entity.smartanimal.ai.goal;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.reaper.ancientnature.common.entity.smartanimal.SmartAnimatedAnimal;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class ConsumeItemFromGroundGoal extends Goal {

    protected final SmartAnimatedAnimal mob;
    private final double speedModifier;
    private final boolean followingTargetEvenIfNotSeen;
    private Path path;
    private double pathedTargetX;
    private double pathedTargetY;
    private double pathedTargetZ;
    private int ticksUntilNextPathRecalculation;
    private int ticksUntilNextAttack;
    private long lastCanUseCheck;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;
    private ItemEntity targetItem;

    private final int eatDelay;
    private final int animationLength;
    private boolean shouldCountTillNextAttack = false;
    private boolean shouldCreateParticles = false;

    public ConsumeItemFromGroundGoal(SmartAnimatedAnimal pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, int eatDelay, int animationLength) {
        this.mob = pMob;
        this.speedModifier = pSpeedModifier;
        this.followingTargetEvenIfNotSeen = pFollowingTargetEvenIfNotSeen;
        this.eatDelay = eatDelay;
        this.animationLength = animationLength;
        this.ticksUntilNextAttack = animationLength - this.eatDelay;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        long currentTime = this.mob.level().getGameTime();
        if (currentTime - this.lastCanUseCheck < 20L) {
            return false;
        }
        this.lastCanUseCheck = currentTime;
        ItemEntity item = getTargetItem();
        if (item == null || !item.isAlive()) {
            return false;
        }
        if (canPenalize) {
            if (--this.ticksUntilNextPathRecalculation <= 0) {
                this.path = this.mob.getNavigation().createPath(item, 0);
                this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                return this.path != null;
            }
            return true;
        }
        this.path = this.mob.getNavigation().createPath(item, 0);
        if (this.path != null) {
            return true;
        }
        return this.getAttackReachSqr(item) >= this.mob.distanceToSqr(item.getX(), item.getY(), item.getZ());
    }

    private ItemEntity getTargetItem() {
        if (targetItem != null && targetItem.isAlive()) {
            return targetItem;
        }
        List<ItemEntity> items = this.mob.level().getEntitiesOfClass(ItemEntity.class, this.mob.getBoundingBox().inflate(16.0D, 8.0D, 16.0D), item -> true);
        targetItem = items.stream()
                .filter(this::validTarget)
                .min(Comparator.comparingDouble(item -> item.distanceTo(this.mob)))
                .orElse(null);
        return targetItem;
    }

    private boolean validTarget(ItemEntity item) {
        return item.isAlive() && mob.getDiet().test(item.getItem());
    }

    @Override
    public boolean canContinueToUse() {
        ItemEntity item = getTargetItem();
        if (item == null || !item.isAlive()) {
            return false;
        }
        return !this.followingTargetEvenIfNotSeen ? !this.mob.getNavigation().isDone() : this.mob.isWithinRestriction(item.blockPosition());
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.path, this.speedModifier);
        this.ticksUntilNextPathRecalculation = 0;
        this.ticksUntilNextAttack = 0;
    }

    @Override
    public void stop() {
        targetItem = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
        ItemEntity item = getTargetItem();
        if (item != null) {
            this.mob.getLookControl().setLookAt(item, 30.0F, 30.0F);
            double distance = this.mob.distanceTo(item);
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
            if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(item)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || item.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
                this.pathedTargetX = item.getX();
                this.pathedTargetY = item.getY();
                this.pathedTargetZ = item.getZ();
                this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                if (this.canPenalize) {
                    this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
                    if (this.mob.getNavigation().getPath() != null) {
                        net.minecraft.world.level.pathfinder.Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
                        if (finalPathPoint != null && item.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1) {
                            failedPathFindingPenalty = 0;
                        } else {
                            failedPathFindingPenalty += 10;
                        }
                    } else {
                        failedPathFindingPenalty += 10;
                    }
                }
                if (distance > 1024.0D) {
                    this.ticksUntilNextPathRecalculation += 10;
                } else if (distance > 256.0D) {
                    this.ticksUntilNextPathRecalculation += 5;
                }
                if (!this.mob.getNavigation().moveTo(item, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15;
                }
                this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
            }
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            this.checkAndPerformAttack(item, distance);

            if (this.mob.position().distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) <= 0.5D) {
                this.shouldCreateParticles = true;
            }

            if (this.shouldCreateParticles) {
                this.calculateAndSpawnItemParticles(item.getItem(), 8);
            }

            if (!this.mob.isTame() && item.getOwner() instanceof Player player) {
                this.mob.tame(player);
            }
        }
    }

    @SuppressWarnings("all")
    private void calculateAndSpawnItemParticles(ItemStack pStack, int particlesToCreate) {

        for(int i = 0; i < particlesToCreate; ++i) {

            final Vec3 location = eatingParticleLocation(this.mob, this.mob.level().random);

            final Vec3 speed = eatingParticleSpeed(this.mob, this.mob.level().random);

            this.spawnEatingParticles(this.mob.level(), pStack, location, speed);
        }

        shouldCreateParticles = false;
    }

    private static Vec3 eatingParticleSpeed(LivingEntity entity, RandomSource random) {

        Vec3 speed = new Vec3(((double)random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
        speed = speed.xRot(-entity.getXRot() * ((float)Math.PI / 180F));
        speed = speed.yRot(-entity.getYRot() * ((float)Math.PI / 180F));

        return speed;
    }

    private static Vec3 eatingParticleLocation(LivingEntity entity, RandomSource random) {
        double initialYSpeed = (double)(-random.nextFloat()) * 0.6D - 0.3D;

        Vec3 location = new Vec3(((double)random.nextFloat() - 0.5D) * 0.3D, initialYSpeed, 0.6D);
        location = location.xRot(-entity.getXRot() * ((float)Math.PI / 180F));
        location = location.yRot(-entity.getYRot() * ((float)Math.PI / 180F));

        location = location.add(entity.getX(), entity.getEyeY(), entity.getZ());

        return location;
    }

    private void spawnEatingParticles(Level level, ItemStack stack, Vec3 location, Vec3 speed) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), location.x, location.y, location.z, 1, speed.x, speed.y + 0.05D, speed.z, 0.0D);
        }
        else {
            level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, stack), location.x, location.y, location.z, speed.x, speed.y + 0.05D, speed.z);
        }
    }

    protected void checkAndPerformAttack(ItemEntity item, double distanceToEnemySqr) {
        if (isEnemyWithinAttackDistance(item, distanceToEnemySqr)) {
            shouldCountTillNextAttack = true;
            if (isTimeToStartAttackAnimation()) {
                mob.setEating(true);
                shouldCreateParticles = true;
            }
            if (isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(item.getX(), item.getEyeY(), item.getZ());
                item.discard();
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            mob.setEating(false);
            mob.eatAnimationTimeout = 0;
            shouldCreateParticles = false;
        }
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= eatDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    private boolean isEnemyWithinAttackDistance(ItemEntity item, double distanceToEnemySqr) {
        return distanceToEnemySqr <= this.getAttackReachSqr(item);
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(animationLength);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected double getAttackReachSqr(ItemEntity attackTarget) {
        return (this.mob.getBbWidth() * 1.2 * this.mob.getBbWidth() * 1.2 + attackTarget.getBbWidth());
    }
}
