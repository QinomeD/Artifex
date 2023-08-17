package qinomed.artifex.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.StringUtils;
import qinomed.artifex.Artifex;
import qinomed.artifex.item.ArtifexItems;

public class ArtifexLang extends LanguageProvider {
    public ArtifexLang(DataGenerator gen, String locale) {
        super(gen, Artifex.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ArtifexItems.BRONZE_INGOT);
        add(ArtifexItems.TIN_INGOT);
        add(ArtifexItems.BRONZE_ICON);
        add(ArtifexItems.TIN_ICON);
        add(ArtifexItems.BRONZE_SWORD);
        add("item.artifex.bronze_sword.description", "Deals 1.5x damage to burning mobs");
    }

    private void add(RegistryObject<Item> item) {
        this.add(item.get(), toTitleCase(item.get().toString()));
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
