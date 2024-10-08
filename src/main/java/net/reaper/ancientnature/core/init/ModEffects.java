package net.reaper.ancientnature.core.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.reaper.ancientnature.AncientNature;
import net.reaper.ancientnature.client.effect.ScareEffect;
import net.reaper.ancientnature.common.effect.BleedingEffect;
import net.reaper.ancientnature.common.effect.FearEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AncientNature.MOD_ID);

    public static final RegistryObject<MobEffect> FEAR = MOB_EFFECTS.register("fear",
            () -> new FearEffect(MobEffectCategory.HARMFUL, 1234567));

    public static final RegistryObject<MobEffect> BLEEDING = MOB_EFFECTS.register("bleeding",
            () -> new BleedingEffect(MobEffectCategory.HARMFUL, 0x630909));

    public static final RegistryObject<MobEffect> SCARE = MOB_EFFECTS.register("scare",
            () -> new ScareEffect(MobEffectCategory.HARMFUL, 0x5A6C81));

    public static void Register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
