package fr.frozerytb.draconiummod.network;

import fr.frozerytb.draconiummod.init.ArmorBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EnergyShieldPacket implements IMessage {

    // Constructor vide pour la désérialisation
    public EnergyShieldPacket() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        // Lire les données du buffer si nécessaire
    }

    @Override
    public void toBytes(ByteBuf buf) {
        // Ecrire les données dans le buffer si nécessaire
    }

    public static class Handler implements IMessageHandler<EnergyShieldPacket, IMessage> {

        @Override
        public IMessage onMessage(EnergyShieldPacket message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;

            // Exécuter l'action sur le fil du serveur
            player.getServerWorld().addScheduledTask(() -> {
                ArmorBase.applyEnergyShield(player);
            });

            // Pas de réponse au client nécessaire, donc on retourne null
            return null;
        }
    }
}

