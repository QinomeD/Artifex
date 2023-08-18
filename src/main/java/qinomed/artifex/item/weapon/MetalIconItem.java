package qinomed.artifex.item.weapon;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;
import qinomed.artifex.Artifex;
import qinomed.artifex.capabilities.mana.PlayerManaProvider;
import qinomed.artifex.capabilities.spells.PlayerSpellsProvider;
import qinomed.artifex.client.ClientManaData;
import qinomed.artifex.network.ArtifexMessages;
import qinomed.artifex.network.ManaSyncS2CPacket;
import qinomed.artifex.spell.SpellRegistry;

import java.util.function.Consumer;

public class MetalIconItem extends Item {
    public MetalIconItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                return Artifex.ICON_HOLD;
            }
        });
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pIsSelected && pEntity instanceof Player player) {
            var cap = player.getCapability(PlayerManaProvider.PLAYER_MANA);
            if (!pLevel.isClientSide) {
                cap.ifPresent(mana -> ArtifexMessages.sendToPlayer(new ManaSyncS2CPacket(mana.getMana()), (ServerPlayer) player));
            } else {
                Minecraft.getInstance().player.displayClientMessage(Component.literal("Current mana: " + ClientManaData.getMana()).withStyle(ChatFormatting.BLUE), true);
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide) {
            if (player.isShiftKeyDown()) {
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    mana.addMana(5);
                });
            } else {
                player.getCapability(PlayerSpellsProvider.PLAYER_SPELLS).ifPresent(spellCap -> {
                    var spells = spellCap.getSpells();
                    if (!spellCap.addSpell(SpellRegistry.SPEED_SPELL.get())) {
                        spells.get(spells.indexOf(SpellRegistry.SPEED_SPELL.get())).onCast(player);
                    }
                });

            }
        }

        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
}
