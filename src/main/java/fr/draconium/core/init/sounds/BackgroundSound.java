package fr.draconium.core.init.sounds;

import fr.draconium.core.references.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class BackgroundSound
{

    public static SoundEvent BACKGROUND_MUSIC;
	
    public static void init()
    {
    	BACKGROUND_MUSIC = createSoundEvent("dragon_roar");
    }
    
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event)
	{
		event.getRegistry().registerAll(
				BACKGROUND_MUSIC
		);
	}

	private static SoundEvent createSoundEvent(String soundName)
	{
        ResourceLocation soundID = new ResourceLocation(Reference.MODID, soundName);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }
}
