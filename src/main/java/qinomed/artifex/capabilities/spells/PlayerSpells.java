package qinomed.artifex.capabilities.spells;

import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import qinomed.artifex.spell.Spell;
import qinomed.artifex.spell.SpellRegistry;

import java.util.ArrayList;
import java.util.List;

public class PlayerSpells {
    private List<Spell> spells = new ArrayList<>();

    public List<Spell> getSpells() {
        return spells;
    }

    public boolean addSpell(Spell spell) {
        if (!spells.contains(spell)) {
            spells.add(spell);
            return true;
        }
        return false;
    }

    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public void copyFrom(PlayerSpells source) {
        this.spells = source.spells;
    }

    public void saveNBTData(ListTag nbt) {
        for (Spell spell : spells) {
            if (spell != null) {
                nbt.add(StringTag.valueOf(spell.toString()));
            }
        }
    }

    public void loadNBTData(ListTag nbt) {
        spells.clear();
        for (int i = 0; i < nbt.size(); i++) {
            spells.add(SpellRegistry.SPELLS.get().getValue(new ResourceLocation(nbt.getString(i))));
        }
    }
}
