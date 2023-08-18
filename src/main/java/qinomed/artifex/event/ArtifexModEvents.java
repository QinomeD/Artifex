package qinomed.artifex.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import qinomed.artifex.Artifex;
import qinomed.artifex.capabilities.mana.PlayerMana;
import qinomed.artifex.capabilities.mana.PlayerManaProvider;
import qinomed.artifex.capabilities.spells.PlayerSpells;
import qinomed.artifex.capabilities.spells.PlayerSpellsProvider;

@Mod.EventBusSubscriber(modid = Artifex.MODID)
public class ArtifexModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent()) {
                event.addCapability(new ResourceLocation(Artifex.MODID, "mana"), new PlayerManaProvider());
            }

            if(!event.getObject().getCapability(PlayerSpellsProvider.PLAYER_SPELLS).isPresent()) {
                event.addCapability(new ResourceLocation(Artifex.MODID, "spells"), new PlayerSpellsProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore -> newStore.copyFrom(oldStore));
            });

            event.getOriginal().getCapability(PlayerSpellsProvider.PLAYER_SPELLS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerSpellsProvider.PLAYER_SPELLS).ifPresent(newStore -> newStore.copyFrom(oldStore));
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerMana.class);
        event.register(PlayerSpells.class);
    }
}
