package net.reaper.ancientnature.core.datagen.server.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.reaper.ancientnature.core.init.ModBlocks;
import net.reaper.ancientnature.core.init.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockLoot extends BlockLootSubProvider {

    protected List<Block> knowBlocks = new ArrayList<>();

    public ModBlockLoot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.FOSSILIZED_GRAVEL.get());
        makeAmberdrops(ModBlocks.DEEPSLATE_AMBER.get(), new int[]{20, 1, 1}, ModItems.AMBER.get(), ModItems.MOSQUITO_AMBER.get(), ModItems.LIZARD_AMBER.get());
        this.createOreDrop(ModBlocks.DEEPSLATE_CAMBRIAN_FOSSIL.get(), ModItems.CAMBRIAN_FOSSIL.get());
    }

    protected void makeAmberdrops(Block amber, int[] weights, ItemLike... drops) {
        if (weights.length != drops.length)
            throw new IllegalArgumentException("there where more entires then weights");
        LootPool.Builder ambers = LootPool.lootPool().when(HAS_NO_SILK_TOUCH);
        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];
            ItemLike drop = drops[i];
            ambers.add(LootItem.lootTableItem(drop).setWeight(weight));
        }
        LootTable.Builder table = LootTable.lootTable().withPool(LootPool.lootPool().when(HAS_SILK_TOUCH).add(
                LootItem.lootTableItem(amber)
        )).withPool(ambers);
        ambers.when(ExplosionCondition.survivesExplosion());
        this.add(amber, table);
    }

    @Override
    protected void add(Block pBlock, LootTable.Builder pBuilder) {
        this.knowBlocks.add(pBlock);
        super.add(pBlock, pBuilder);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return this.knowBlocks;
    }
}
