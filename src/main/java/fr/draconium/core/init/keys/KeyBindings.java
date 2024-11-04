package fr.draconium.core.init.keys;

import org.lwjgl.input.Keyboard;

import fr.draconium.core.proxy.packets.DraconiumCorePackets;
import fr.draconium.core.proxy.packets.EnergyShieldPacket;
import fr.draconium.core.proxy.packets.SpawnWolfPacket;
import fr.draconium.core.proxy.packets.TeleportPacket;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class KeyBindings
{

	public static final KeyBinding TELEPORT_KEY 		= new KeyBinding("key.teleport", Keyboard.KEY_T, "key.categories.draconiummod");
	public static final KeyBinding ENERGY_SHIELD_KEY 	= new KeyBinding("key.energy_shield", Keyboard.KEY_Y, "key.categories.draconiummod");
	public static final KeyBinding SPAWN_ALLIES_KEY 	= new KeyBinding("key.spawn_allies", Keyboard.KEY_U, "key.categories.draconiummod");

	public static void init()
	{
		ClientRegistry.registerKeyBinding(TELEPORT_KEY);
		ClientRegistry.registerKeyBinding(ENERGY_SHIELD_KEY);
		ClientRegistry.registerKeyBinding(SPAWN_ALLIES_KEY);
	}

	@SubscribeEvent
	public static void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if (TELEPORT_KEY.isPressed()) 			DraconiumCorePackets.INSTANCE.sendToServer(new TeleportPacket());
		else if (ENERGY_SHIELD_KEY.isPressed()) DraconiumCorePackets.INSTANCE.sendToServer(new EnergyShieldPacket());
		else if (SPAWN_ALLIES_KEY.isPressed()) 	DraconiumCorePackets.INSTANCE.sendToServer(new SpawnWolfPacket());
	}
}