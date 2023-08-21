package qinomed.artifex.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import qinomed.artifex.Artifex;
import qinomed.artifex.item.weapon.MetalIconItem;

import java.awt.*;

public class ManaBarOverlay {
    private static final ResourceLocation MANA_BAR = new ResourceLocation(Artifex.MODID, "textures/gui/mana_bar.png");

    public static final IGuiOverlay MANA_OVERLAY = (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (Minecraft.getInstance().player.isHolding(stack -> stack.getItem() instanceof MetalIconItem)) {
            int x = screenWidth / 2 - 98;
            int y = screenHeight - 33;

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, MANA_BAR);

            GuiComponent.blit(poseStack, x, y, 0, 0, 0, 196, 13, 256, 256);
            
            if (ClientManaData.getMana() > 0) {
                GuiComponent.blit(poseStack, x+7, y+4, 0, 7, 17, Math.round((ClientManaData.getMana() / 100f) * 182), 5, 256, 256);

                //gui.setupOverlayRenderState(true, false);
                Font font = gui.getFont();
                String level = String.valueOf(ClientManaData.getMana());
                int fontX = (screenWidth - font.width(level)) / 2;
                int fontY = y - 2;

                font.draw(poseStack, level, (fontX + 1), fontY, 0);
                font.draw(poseStack, level, (fontX - 1), fontY, 0);
                font.draw(poseStack, level, fontX, (fontY + 1), 0);
                font.draw(poseStack, level, fontX, (fontY - 1), 0);
                font.draw(poseStack, level, fontX, fontY, new Color(145, 145, 255).getRGB());
            }
        }
    };
}
