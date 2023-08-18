package qinomed.artifex.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;
import qinomed.artifex.Artifex;
import qinomed.artifex.block.ArtifexBlocks;
import qinomed.artifex.item.ArtifexItems;

public class ArtifexLang extends LanguageProvider {
    public ArtifexLang(DataGenerator gen, String locale) {
        super(gen, Artifex.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.artifex", "Artifex");

        add(ArtifexBlocks.TIN_ORE.get());

        add(ArtifexItems.BRONZE_INGOT.get());
        add(ArtifexItems.TIN_INGOT.get());
        add(ArtifexItems.BRONZE_ICON.get());
        add(ArtifexItems.TIN_ICON.get());
        add(ArtifexItems.BRONZE_SWORD.get());
        add("item.artifex.bronze_sword.description", "Deals 1.5x damage to burning mobs");
    }

    private void add(Item item) {
        this.add(item, toTitleCase(item.toString()));
    }

    private void add(Block block) {
        this.add(block.asItem(), toTitleCase(block.asItem().toString()));
    }

    private static String toTitleCase(String str) {
        String[] words = str.split("_");
        StringBuilder builder = new StringBuilder();
        for (String s : words) {
            s = StringUtils.capitalize(s);
            if (!builder.isEmpty())
                builder.append(" ");
            builder.append(s);
        }
        return builder.toString();
    }
}
