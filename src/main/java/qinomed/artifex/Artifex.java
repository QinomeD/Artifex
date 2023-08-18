package qinomed.artifex;

import com.mojang.logging.LogUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import qinomed.artifex.block.ArtifexBlocks;
import qinomed.artifex.datagen.ArtifexBlockModels;
import qinomed.artifex.datagen.ArtifexItemModels;
import qinomed.artifex.datagen.ArtifexLang;
import qinomed.artifex.item.ArtifexItems;
import qinomed.artifex.network.ArtifexMessages;
import qinomed.artifex.spell.SpellRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Artifex.MODID)
public class Artifex {
    public static HumanoidModel.ArmPose ICON_HOLD;

    // Define mod id in a common place for everything to reference
    public static final String MODID = "artifex";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab ARTIFEX_TAB = new CreativeModeTab("artifex") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ArtifexItems.BRONZE_ICON.get());
        }
    };

    public Artifex() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ArtifexItems.register(modEventBus);
        ArtifexBlocks.register(modEventBus);
        SpellRegistry.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::gatherData);
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        event.enqueueWork(() -> {
            ArtifexMessages.register();
        });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ICON_HOLD = HumanoidModel.ArmPose.create("ICON_HOLD", false, (model, entity, arm) -> {
                    if (arm.getId() == 0) {
                        model.leftArm.xRot = Mth.DEG_TO_RAD * -90;
                        model.leftArm.yRot = Mth.DEG_TO_RAD * 10;
                        model.leftArm.zRot = Mth.DEG_TO_RAD * -10;
                    } else {
                        model.rightArm.xRot = Mth.DEG_TO_RAD * -90;
                        model.rightArm.yRot = Mth.DEG_TO_RAD * -10;
                        model.rightArm.zRot = Mth.DEG_TO_RAD * 10;
                    }
                });
            });
        }
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeServer(), new ArtifexItemModels(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new ArtifexBlockModels(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new ArtifexLang(generator, "en_us"));
    }
}
