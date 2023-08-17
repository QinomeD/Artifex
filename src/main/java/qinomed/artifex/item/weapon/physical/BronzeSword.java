package qinomed.artifex.item.weapon.physical;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import qinomed.artifex.Artifex;
import qinomed.artifex.item.ArtifexItems;

import java.util.List;

@Mod.EventBusSubscriber(modid = Artifex.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BronzeSword extends SwordItem {
    public BronzeSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.translatable("item.artifex.bronze_sword.description").withStyle(ChatFormatting.GRAY));
    }

    @SubscribeEvent
    public static void onEnemyHit(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity entity && entity.getMainHandItem().getItem() == ArtifexItems.BRONZE_SWORD.get()) {
            if (event.getEntity().isOnFire())
                event.setAmount(event.getAmount() * 1.5f);
        }
    }
}
