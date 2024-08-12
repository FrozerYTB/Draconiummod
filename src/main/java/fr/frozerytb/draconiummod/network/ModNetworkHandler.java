package fr.frozerytb.draconiummod.network;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworkHandler {

    private static int packetId = 0;

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    public static void registerMessages() {
        INSTANCE.registerMessage(TeleportPacket.Handler.class, TeleportPacket.class, packetId++, Side.SERVER);
        INSTANCE.registerMessage(EnergyShieldPacket.Handler.class, EnergyShieldPacket.class, packetId++, Side.SERVER);
        INSTANCE.registerMessage(SpawnAlliesPacket.Handler.class, SpawnAlliesPacket.class, packetId++, Side.SERVER);

    }
}
