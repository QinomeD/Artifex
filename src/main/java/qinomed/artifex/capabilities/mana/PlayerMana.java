package qinomed.artifex.capabilities.mana;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {
    private int mana;
    private final int MIN_MANA = 0;
    private int MAX_MANA = 100;


    public int getMana() {
        return mana;
    }

    public void addMana(int amount) {
        mana = Math.min(mana + amount, MAX_MANA);
    }

    public void subMana(int amount) {
        mana = Math.max(mana - amount, MIN_MANA);
    }

    public void setMana(int mana) {
        this.mana = mana;
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
