package qinomed.artifex.capabilities.mana;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import qinomed.artifex.network.ArtifexMessages;
import qinomed.artifex.network.ManaSyncS2CPacket;

public class PlayerMana {
    private int mana;
    private final int MIN_MANA = 0;
    private int MAX_MANA = 100;

    public int getMana() {
        return mana;
    }

    public void addMana(int amount, ServerPlayer player) {
        mana = Math.min(mana + amount, MAX_MANA);
        ArtifexMessages.sendToPlayer(new ManaSyncS2CPacket(mana), player);
    }

    public void subMana(int amount, ServerPlayer player) {
        mana = Math.max(mana - amount, MIN_MANA);
        ArtifexMessages.sendToPlayer(new ManaSyncS2CPacket(mana), player);
    }

    public void setMana(int mana, ServerPlayer player) {
        this.mana = mana;
        ArtifexMessages.sendToPlayer(new ManaSyncS2CPacket(mana), player);
    }

    public void copyFrom(PlayerMana source) {
        this.mana = source.mana;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("mana", mana);
    }

    public void loadNBTData(CompoundTag nbt) {
        mana = nbt.getInt("mana");
    }
}
