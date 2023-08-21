package qinomed.artifex.spell;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import qinomed.artifex.capabilities.mana.PlayerMana;
import qinomed.artifex.capabilities.mana.PlayerManaProvider;

public class Spell {
    final Category category;
    final int cost;
    final int cooldown;

    public Spell(Category category, int cost, int cooldown) {
        this.category = category;
        this.cost = cost;
        this.cooldown = cooldown;
    }

    public boolean onCast(Player caster) {
        if (!caster.getLevel().isClientSide) {
            LazyOptional<PlayerMana> cap = caster.getCapability(PlayerManaProvider.PLAYER_MANA);
            if (cap.resolve().isPresent()) {
                PlayerMana mana = cap.resolve().get();
                if (mana.getMana() < cost)
                    return false;
                mana.subMana(cost, (ServerPlayer) caster);
            }
        }

        return true;
    }

    public String toString() {
        return SpellRegistry.SPELLS.get().getKey(this).toString();
    }

    public enum Category {
        CONJURATION,
        MANIPULATION
    }
}
