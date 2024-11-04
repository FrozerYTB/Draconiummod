package fr.draconium.core.proxy.packets;

import fr.draconium.core.items.armors.DraconiqueArmor;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TeleportPacket implements IMessage
{
	@Override
	public void toBytes(ByteBuf buf)
	{
		
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		
	}

	public static class Handler implements IMessageHandler<TeleportPacket, IMessage>
	{
		@Override
		public IMessage onMessage(TeleportPacket message, MessageContext ctx)
		{
			EntityPlayerMP player = ctx.getServerHandler().player;

			player.getServerWorld().addScheduledTask(() ->
			{
				DraconiqueArmor.teleportRandomly(player);
			});
			return null;
		}
	}
}
