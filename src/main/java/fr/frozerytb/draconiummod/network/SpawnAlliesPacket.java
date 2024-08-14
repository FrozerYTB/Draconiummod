package fr.frozerytb.draconiummod.network;

import fr.frozerytb.draconiummod.init.ArmorBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SpawnAlliesPacket implements IMessage {
    public SpawnAlliesPacket() {}

    @Override
    public void fromBytes(ByteBuf buf) {}

    @Override
    public void toBytes(ByteBuf buf) {}

    public static class Handler implements IMessageHandler<SpawnAlliesPacket, IMessage> {
        @Override
        public IMessage onMessage(SpawnAlliesPacket message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServerWorld().addScheduledTask(() -> {
                // Vérifie si le joueur porte l'armure complète de Draconium
                if (ArmorBase.isWearingFullDraconiqueArmor(player)) {
                    ArmorBase.spawnAllies(player);
                } else {
                    // Envoyer un message d'erreur au joueur
                    player.sendMessage(new TextComponentString("Vous devez porter une armure complète de Draconium pour invoquer des alliés."));
                }
            });
            return null;
        }
    }
}
