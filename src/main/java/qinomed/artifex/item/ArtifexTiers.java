package qinomed.artifex.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ArtifexTiers {
    public static final ForgeTier BRONZE = new ForgeTier(2, 220, 6.0f, 2.0f, 16,
            BlockTags.NEEDS_IRON_TOOL,() -> Ingredient.of(ArtifexItems.BRONZE_INGOT.get()));
}
