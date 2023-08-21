package qinomed.artifex.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import qinomed.artifex.Artifex;
import qinomed.artifex.capabilities.mana.PlayerManaProvider;
import qinomed.artifex.network.ArtifexMessages;
import qinomed.artifex.network.ManaSyncS2CPacket;

@Mod.EventBusSubscriber(modid = Artifex.MODID)
public class ArtifexCommonEvents {

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> ArtifexMessages.sendToPlayer(new ManaSyncS2CPacket(mana.getMana()), player));
        }
    }
}
