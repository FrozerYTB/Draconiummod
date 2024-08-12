package fr.frozerytb.draconiummod.util.handlers;

import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class PlayerJoinEventHandler {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        World world = player.world;

        // Jouer la musique de fond pour le joueur
        if (!world.isRemote) {
            world.playSound(null, player.getPosition(), SoundEvent.REGISTRY.getObject(new ResourceLocation(Reference.MODID, "music.background")), SoundCategory.MUSIC, 1.0F, 1.0F);
        }
    }
}