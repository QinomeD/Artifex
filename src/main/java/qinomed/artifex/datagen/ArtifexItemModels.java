package qinomed.artifex.datagen;

import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import qinomed.artifex.Artifex;
import qinomed.artifex.item.ArtifexItems;

public class ArtifexItemModels extends ItemModelProvider {
    public ArtifexItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Artifex.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generatedItem(ArtifexItems.BRONZE_INGOT);
        generatedItem(ArtifexItems.TIN_INGOT);
        iconItem(ArtifexItems.BRONZE_ICON);
        iconItem(ArtifexItems.TIN_ICON);
        handheldItem(ArtifexItems.BRONZE_SWORD);
    }

    private static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    private static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    private void generatedItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, GENERATED).texture("layer0", new ResourceLocation(Artifex.MODID, "item/" + name));
    }

    private void handheldItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, HANDHELD).texture("layer0", new ResourceLocation(Artifex.MODID, "item/" + name));
    }

    private void iconItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, GENERATED).texture("layer0", new ResourceLocation(Artifex.MODID, "item/" + name))
                .transforms()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
                    .rotation(0, 180, 0)
                    .translation(0, 3, 1)
                    .scale(0.55f)
                    .end()
                .end();
    }
}
