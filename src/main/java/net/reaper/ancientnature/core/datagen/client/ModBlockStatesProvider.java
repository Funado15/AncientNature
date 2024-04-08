package net.reaper.ancientnature.core.datagen.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.reaper.ancientnature.AncientNature;
import net.reaper.ancientnature.common.block.RevivalStand;
import net.reaper.ancientnature.core.init.ModBlocks;

import javax.sound.sampled.ReverbType;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AncientNature.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.DEEPSLATE_AMBER.get());
        simpleBlock(ModBlocks.DEEPSLATE_CAMBRIAN_FOSSIL.get());
        simpleBlock(ModBlocks.DEEPSLATE_CARBONIFEROUS.get());
        simpleBlock(ModBlocks.DEEPSLATE_DEVONIAN_FOSSIL.get());
        simpleBlock(ModBlocks.STONE_PERMIAN_FOSSIL.get());
        makeFossil(ModBlocks.MUD_WITH_FOSSILS.get());
        revivalStand(ModBlocks.REVIVAL_STAND.get());
    }

    protected void revivalStand(Block block){
        getVariantBuilder(block).forAllStates(state -> {
            ResourceLocation registryName = ForgeRegistries.BLOCKS.getKey(block);
            String name = registryName.getPath();
            int stage = state.getValue(RevivalStand.STAGE);
            if (stage < 4){
                name += "_stage" + stage;
            }else {
                boolean active = state.getValue(RevivalStand.ACTIVE);
                name += active ? "_active" : "_unactive";
            }
            return ConfiguredModel.builder().modelFile(models().getBuilder(name).parent(models().getExistingFile(AncientNature.modLoc("block/revival_stand_prefab"))).texture("texture", AncientNature.modLoc("block/" + name))).build();
        });
    }

    protected void makeFossil(BrushableBlock block){
        getVariantBuilder(block).forAllStates(state -> {
            int dusted = state.getValue(BlockStateProperties.DUSTED);
            ResourceLocation registryName = ForgeRegistries.BLOCKS.getKey(block);
            String name = registryName.getPath();
            if (dusted > 0)
                name += "_" + dusted;
            ConfiguredModel.Builder<?> model = ConfiguredModel.builder().modelFile(models().cubeAll(name, modLoc("block/" + name)));
            if (dusted <= 0){
                this.simpleBlockItem(block, models().cubeAll(name, modLoc("block/" + name)));
            }
            return model.build();
        });
    }

    @Override
    public void simpleBlock(Block block, ModelFile model) {
        super.simpleBlock(block, model);
        simpleBlockItem(block, model);
    }
}
