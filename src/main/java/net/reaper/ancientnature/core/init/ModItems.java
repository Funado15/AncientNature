package net.reaper.ancientnature.core.init;

import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.reaper.ancientnature.AncientNature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AncientNature.MOD_ID);

    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () ->new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOSQUITO_AMBER = ITEMS.register("mosquito_amber", () ->new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIZARD_AMBER = ITEMS.register("lizard_amber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAMBRIAN_FOSSIL = ITEMS.register("cambrian_fossil", () ->new Item(new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_PERMIAN_FOSSIL = ITEMS.register("deepslate_permian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_PERIMAN_FOSSIL = ITEMS.register("muddy_permian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STONE_PERMIAN_FOSSIL = ITEMS.register("stone_permian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CARBONIFEROUS_FOSSIL = ITEMS.register("carboniferous_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DEVONIAN_FOSSIL = ITEMS.register("devonian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ARANDASPIS_FOSSIL = ITEMS.register("arandaspis_fossil", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> ANOMALOCARIS_FOSSIL = ITEMS.register("anomalocaris_fossil", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> LYTHRONAX_FOSSIL = ITEMS.register("lythronax_fossil", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ROPE = ITEMS.register("rope", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LYTHRONAX_TEETH = ITEMS.register("lythronax_teeth", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<SwordItem> BLOOD_DAGGER = ITEMS.register("blood_dagger", () -> new SwordItem(Tiers.STONE,3, -1.0F, new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> WHERE_YOUR_JOURNEY_BEGINS_MUSIC_DISC = ITEMS.register("where_your_journey_begins_disc", () -> new RecordItem(6, ModSounds.WHERE_YOUR_JOURNEY_BEGINS, new Item.Properties().stacksTo(1),3200));

    public static final RegistryObject<Item> FISH_ROE =  ITEMS.register("fish_roe", () -> new Item(new Item.Properties()));
    public static final RegistryObject<MobBucketItem> ARANDASPIS_BUCKET = ITEMS.register("arandaspis_bucket", () -> new MobBucketItem(ModEntities.ARANDASPIS, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<MobBucketItem> ANOMALOCARIS_BUCKET = ITEMS.register("anomalocaris_bucket", () -> new MobBucketItem(ModEntities.ANOMALOCARIS, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<ForgeSpawnEggItem> ARANDASPIS_SPAWN_EGG = ITEMS.register("arandaspis_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.ARANDASPIS, 0x5887B8, 0x2E4E77, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> ANOMALOCARIS_SPAWN_EGG = ITEMS.register("anomalocaris_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.ANOMALOCARIS, 0x303030, 0xed9237, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}

