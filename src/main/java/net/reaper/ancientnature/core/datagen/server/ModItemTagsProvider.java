package net.reaper.ancientnature.core.datagen.server;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.reaper.ancientnature.AncientNature;
import net.reaper.ancientnature.core.init.ModItems;
import net.reaper.ancientnature.core.init.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, AncientNature.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.FOSSILS).add(
                ModItems.ANOMALOCARIS_FOSSIL.get(),
                ModItems.DODO_FOSSIL.get(),
                ModItems.CAMBRIAN_FOSSIL.get(),
                ModItems.CARBONIFEROUS_FOSSIL.get(),
                ModItems.DEEPSLATE_PERMIAN_FOSSIL.get(),
                ModItems.STONE_PERMIAN_FOSSIL.get(),
                ModItems.DEVONIAN_FOSSIL.get(),
                ModItems.LYTHRONAX_FOSSIL.get(),
                ModItems.PARANOGMIUS_FOSSIL.get(),
                ModItems.CRETACEOUS_FOSSIL.get(),
                ModItems.QUATERNARY_FOSSIL.get());

        tag(ModTags.Items.ANIMAL_AMBERS).add(
                ModItems.LIZARD_AMBER.get(),
                ModItems.MOSQUITO_AMBER.get());

        tag(ModTags.Items.RAW_MEAT).add(
                ModItems.RAW_DODO.get(),
                ModItems.RAW_PARANOGMIUS.get());

        tag(ModTags.Items.COOKED_MEAT).add(
                ModItems.COOKED_DODO.get(),
                ModItems.COOKED_PARANOGMIUS.get());

        tag(ItemTags.MUSIC_DISCS).add(
                ModItems.WHERE_YOUR_JOURNEY_BEGINS_MUSIC_DISC.get());
    }
}
