package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.network.EnergyShieldPacket;
import fr.frozerytb.draconiummod.network.ModNetworkHandler;
import fr.frozerytb.draconiummod.network.SpawnAlliesPacket;
import fr.frozerytb.draconiummod.network.TeleportPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(Side.CLIENT)
public class KeyBindings {

    public static final KeyBinding TELEPORT_KEY = new KeyBinding("key.teleport", Keyboard.KEY_T, "key.categories.draconiummod");
    public static final KeyBinding ENERGY_SHIELD_KEY = new KeyBinding("key.energy_shield", Keyboard.KEY_Y, "key.categories.draconiummod");
    public static final KeyBinding SPAWN_ALLIES_KEY = new KeyBinding("key.spawn_allies", Keyboard.KEY_U, "key.categories.draconiummod");

    public static void init() {
        ClientRegistry.registerKeyBinding(TELEPORT_KEY);
        ClientRegistry.registerKeyBinding(ENERGY_SHIELD_KEY);
        ClientRegistry.registerKeyBinding(SPAWN_ALLIES_KEY);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (TELEPORT_KEY.isPressed()) {
            ModNetworkHandler.INSTANCE.sendToServer(new TeleportPacket());
        }
        if (ENERGY_SHIELD_KEY.isPressed()) {
            ModNetworkHandler.INSTANCE.sendToServer(new EnergyShieldPacket());
        }
        if (SPAWN_ALLIES_KEY.isPressed()) {
            ModNetworkHandler.INSTANCE.sendToServer(new SpawnAlliesPacket());
        }

    }

    private static void performTeleport(EntityPlayer player) {
        if (player != null) {
            ArmorBase.teleportRandomly(player);
        }
    }

    private static void activateEnergyShield(EntityPlayer player) {
        if (player != null) {
            ArmorBase.applyEnergyShield(player);
        }
    }

    private static void spawnAllies(EntityPlayer player) {
        if (player != null) {
            ArmorBase.spawnAllies(player);
        }
    }
}
