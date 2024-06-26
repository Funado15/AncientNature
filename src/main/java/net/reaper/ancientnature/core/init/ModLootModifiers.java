package net.reaper.ancientnature.core.init;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.reaper.ancientnature.AncientNature;
import net.reaper.ancientnature.common.loot_modifiers.ChanceItemModifier;

public class ModLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AncientNature.MOD_ID);

    public static final RegistryObject<Codec<ChanceItemModifier>> CHANCE_ITEM = LOOT_MODIFIERS.register("add_item", () -> ChanceItemModifier.CODEC);
}
