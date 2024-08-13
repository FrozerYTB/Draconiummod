package fr.frozerytb.draconiummod.network;

import fr.frozerytb.draconiummod.init.ArmorBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TeleportPacket implements IMessage {
    public TeleportPacket() {}

    @Override
    public void toBytes(ByteBuf buf) {}

    @Override
    public void fromBytes(ByteBuf buf) {}

    public static class Handler implements IMessageHandler<TeleportPacket, IMessage> {
        @Override
        public IMessage onMessage(TeleportPacket message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;

            player.getServerWorld().addScheduledTask(() -> {
                ArmorBase.teleportRandomly(player);
            });
            return null;
        }
    }
}
