package qinomed.artifex.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import qinomed.artifex.Artifex;
import qinomed.artifex.item.weapon.MetalIconItem;
import qinomed.artifex.item.weapon.physical.BronzeSword;

public class ArtifexItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Artifex.MODID);

    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BRONZE_ICON = ITEMS.register("bronze_icon", () -> new MetalIconItem(new Item.Properties()));
    public static final RegistryObject<Item> TIN_ICON = ITEMS.register("tin_icon", () -> new MetalIconItem(new Item.Properties()));

    public static final RegistryObject<Item> BRONZE_SWORD = ITEMS.register("bronze_sword",
            () -> new BronzeSword(Tiers.IRON, 3, -2.4f, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
