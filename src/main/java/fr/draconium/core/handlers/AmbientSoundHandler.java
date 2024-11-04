package fr.draconium.core.handlers;

import fr.draconium.core.init.sounds.BackgroundSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AmbientSoundHandler
{
	private static boolean isPlaying = false;

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event)
	{
		if (Minecraft.getMinecraft().world != null)
		{
			if (!isPlaying)
			{
				Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMusicRecord(BackgroundSound.BACKGROUND_MUSIC));
				isPlaying = true;
			}
		}
		else
		{
			isPlaying = false;
			Minecraft.getMinecraft().getSoundHandler().stopSounds();
		}
	}
}
