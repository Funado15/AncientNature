package net.reaper.ancientnature.common.entity.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class PanicSprintingGoal extends Goal {
    protected final PathfinderMob mob;
    protected double posX;
    protected double posY;
    protected double posZ;
    protected boolean isRunning;

    public PanicSprintingGoal(PathfinderMob pMob) {
        this.mob = pMob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (!this.shouldPanic()) {
            return false;
        } else {
            if (this.mob.isOnFire()) {
                BlockPos blockpos = this.lookForWater(this.mob.level(), this.mob, 5);
                if (blockpos != null) {
                    this.posX = (double)blockpos.getX();
                    this.posY = (double)blockpos.getY();
                    this.posZ = (double)blockpos.getZ();
                    return true;
                }
            }

            return this.findRandomPosition();
        }
    }

    protected boolean shouldPanic() {
        return this.mob.getLastHurtByMob() != null || this.mob.isFreezing() || this.mob.isOnFire();
    }

    protected boolean findRandomPosition() {
        Vec3 vec3 = DefaultRandomPos.getPos(this.mob, 5, 4);
        if (vec3 == null) {
            return false;
        } else {
            this.posX = vec3.x;
            this.posY = vec3.y;
            this.posZ = vec3.z;
            return true;
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.mob.setSprinting(true);
        this.mob.getNavigation().moveTo(this.posX, this.posY, this.posZ, 1.0d);
        this.isRunning = true;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.isRunning = false;
        this.mob.setSprinting(false);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone();
    }

    @Nullable
    protected BlockPos lookForWater(BlockGetter pLevel, Entity pEntity, int pRange) {
        BlockPos blockpos = pEntity.blockPosition();
        return !pLevel.getBlockState(blockpos).getCollisionShape(pLevel, blockpos).isEmpty() ? null : BlockPos.findClosestMatch(pEntity.blockPosition(), pRange, 1, (p_196649_) -> {
            return pLevel.getFluidState(p_196649_).is(FluidTags.WATER);
        }).orElse((BlockPos)null);
    }
}
