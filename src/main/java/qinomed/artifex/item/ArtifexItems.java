package qinomed.artifex.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import qinomed.artifex.Artifex;
import qinomed.artifex.block.ArtifexBlocks;
import qinomed.artifex.item.weapon.MetalIconItem;
import qinomed.artifex.item.weapon.BronzeSword;

public class ArtifexItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Artifex.MODID);
    public static final Item.Properties BASE = new Item.Properties().tab(Artifex.ARTIFEX_TAB);

    public static final RegistryObject<Item> TIN_ORE = ITEMS.register("tin_ore", () -> new BlockItem(ArtifexBlocks.TIN_ORE.get(), BASE));

    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () -> new Item(BASE));
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(BASE));

    public static final RegistryObject<Item> BRONZE_ICON = ITEMS.register("bronze_icon", () -> new MetalIconItem(BASE, 3));
    public static final RegistryObject<Item> TIN_ICON = ITEMS.register("tin_icon", () -> new MetalIconItem(BASE, 3));

    public static final RegistryObject<Item> BRONZE_SWORD = ITEMS.register("bronze_sword",
            () -> new BronzeSword(ArtifexTiers.BRONZE, 3, -2.4f, BASE));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
