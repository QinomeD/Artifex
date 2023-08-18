package qinomed.artifex.spell;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import qinomed.artifex.Artifex;
import qinomed.artifex.spell.manipulation.SpeedSpell;

import java.util.function.Supplier;

public class SpellRegistry {
    public static final DeferredRegister<Spell> SPELLS_DEFERRED = DeferredRegister.create(new ResourceLocation(Artifex.MODID, "spells"), Artifex.MODID);
    public static final Supplier<IForgeRegistry<Spell>> SPELLS = SPELLS_DEFERRED.makeRegistry(() -> new RegistryBuilder<Spell>().allowModification());

    public static final RegistryObject<Spell> SPEED_SPELL = SPELLS_DEFERRED.register("speed", () -> new SpeedSpell(Spell.Category.MANIPULATION, 5, 100));

    public static void register(IEventBus eventBus) {
        SPELLS_DEFERRED.register(eventBus);
    }
}
