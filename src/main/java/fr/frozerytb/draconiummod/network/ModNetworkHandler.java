package fr.frozerytb.draconiummod.network;

import fr.frozerytb.draconiummod.util.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworkHandler {

    private static int packetId = 0;

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    public static void registerMessages() {
        registerMessage(TeleportPacket.Handler.class, TeleportPacket.class, Side.SERVER);
        registerMessage(EnergyShieldPacket.Handler.class, EnergyShieldPacket.class, Side.SERVER);
        registerMessage(SpawnAlliesPacket.Handler.class, SpawnAlliesPacket.class, Side.SERVER);
    }

    private static <T extends IMessage> void registerMessage(Class<? extends IMessageHandler<T, IMessage>> handler, Class<T> message, Side side) {
        INSTANCE.registerMessage(handler, message, packetId++, side);
    }
}
