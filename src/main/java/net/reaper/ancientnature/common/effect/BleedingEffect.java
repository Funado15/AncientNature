package net.reaper.ancientnature.common.effect;


import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.reaper.ancientnature.AncientNature;
import net.reaper.ancientnature.core.init.ModEffects;

import java.util.Collections;
import java.util.List;

public class  BleedingEffect extends MobEffect {
    public BleedingEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {

            int damage = (pAmplifier + 1);
            pLivingEntity.hurt(pLivingEntity.damageSources().magic(), damage);
        }

        if (pAmplifier == 2) {
            pLivingEntity.addEffect(new CustomEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, false, false, false));
        } else if (pAmplifier >= 3) {
            pLivingEntity.addEffect(new CustomEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, false, false, false));
            pLivingEntity.addEffect(new CustomEffectInstance(MobEffects.CONFUSION, 100, 0, false, false, false));
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        if (this == ModEffects.BLEEDING.get()) {
            return pDuration % 40 == 0;
        }
        return false;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }

    public String getDescriptionId() {
        return "effect.ancientnature.bleeding";
    }
}
