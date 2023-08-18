package qinomed.artifex.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import qinomed.artifex.Artifex;
import qinomed.artifex.block.ArtifexBlocks;

public class ArtifexBlockModels extends BlockStateProvider {
    public ArtifexBlockModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Artifex.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlock(ArtifexBlocks.TIN_ORE.get());
        this.simpleBlockItem(ArtifexBlocks.TIN_ORE.get(), this.cubeAll(ArtifexBlocks.TIN_ORE.get()));
    }
}
