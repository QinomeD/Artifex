package qinomed.artifex.item.weapon;

import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;
import qinomed.artifex.Artifex;
import qinomed.artifex.capabilities.mana.PlayerManaProvider;
import qinomed.artifex.spell.Spell;
import qinomed.artifex.spell.SpellRegistry;

import java.util.List;
import java.util.function.Consumer;

public class MetalIconItem extends Item {
    final int spellCapacity;

    public MetalIconItem(Properties pProperties, int spellCapacity) {
        super(pProperties);
        this.spellCapacity = spellCapacity;
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
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            CompoundTag nbt = pStack.getTag();
            ListTag spells = nbt.getList("Spells", Tag.TAG_STRING);
            if (spells.size() - 1 >= nbt.getInt("ActiveSpell")) {
                Spell spell = SpellRegistry.SPELLS.get().getValue(new ResourceLocation(spells.getString(nbt.getInt("ActiveSpell"))));
                if (spell != null)
                    pTooltipComponents.add(Component.translatable("spell." + spell.toString().replace(':', '.')).withStyle(ChatFormatting.GRAY));
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.hasTag()) {
            CompoundTag nbt = new CompoundTag();
            nbt.putInt("ActiveSpell", 0);
            nbt.put("Spells", new ListTag());
            stack.setTag(nbt);
        }
        CompoundTag nbt = stack.getTag();

        if (!level.isClientSide) {
            if (player.isShiftKeyDown()) {
                nbt.putInt("ActiveSpell", (nbt.getInt("ActiveSpell") + 1) % spellCapacity);
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> mana.addMana(5, (ServerPlayer) player));
            } else {
                var spells = nbt.getList("Spells", Tag.TAG_STRING);
                var spell = SpellRegistry.SPELLS.get().getValue(new ResourceLocation(spells.getString(nbt.getInt("ActiveSpell"))));
                if (spell != null)
                    spell.onCast(player);
            }
        }

        return InteractionResultHolder.consume(stack);
    }
}
