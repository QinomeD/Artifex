package qinomed.artifex.capabilities.spells;

import net.minecraft.core.Direction;
import net.minecraft.nbt.ListTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerSpellsProvider implements ICapabilityProvider, INBTSerializable<ListTag> {
    public static Capability<PlayerSpells> PLAYER_SPELLS = CapabilityManager.get(new CapabilityToken<>() {});

    private PlayerSpells spells = null;
    private final LazyOptional<PlayerSpells> optional = LazyOptional.of(this::createPlayerSpells);

    private PlayerSpells createPlayerSpells() {
        if (spells == null) {
            spells = new PlayerSpells();
        }

        return spells;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_SPELLS)
            return optional.cast();

        return LazyOptional.empty();
    }

    @Override
    public ListTag serializeNBT() {
        ListTag nbt = new ListTag();
        createPlayerSpells().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(ListTag nbt) {
        createPlayerSpells().loadNBTData(nbt);
    }
}
