package qinomed.artifex.event;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import qinomed.artifex.Artifex;
import qinomed.artifex.client.ManaBarOverlay;
import qinomed.artifex.item.weapon.MetalIconItem;

@Mod.EventBusSubscriber(modid = Artifex.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ArtifexClientEvents {

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent event) {
        assert Minecraft.getInstance().player != null;
        if (event.getOverlay().id().equals(new ResourceLocation("experience_bar")) && Minecraft.getInstance().player.isHolding(stack -> stack.getItem() instanceof MetalIconItem)) {
            event.setCanceled(true);
        }
    }

    @Mod.EventBusSubscriber(modid = Artifex.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class ClientModEvents {

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("mana_bar", ManaBarOverlay.MANA_OVERLAY);
        }
    }
}
