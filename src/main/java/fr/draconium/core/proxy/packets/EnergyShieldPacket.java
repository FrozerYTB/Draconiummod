package fr.draconium.core.proxy.packets;

import fr.draconium.core.items.armors.DraconiqueArmor;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EnergyShieldPacket implements IMessage
{
	@Override
	public void fromBytes(ByteBuf buf)
	{
		
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		
	}

	public static class Handler implements IMessageHandler<EnergyShieldPacket, IMessage>
	{
		@Override
		public IMessage onMessage(EnergyShieldPacket message, MessageContext ctx)
		{
			EntityPlayerMP player = ctx.getServerHandler().player;
			player.getServerWorld().addScheduledTask(() ->
			{
				// Vérifie si le joueur porte l'armure complète de Draconium
				if (DraconiqueArmor.isAmrorsCompletDraconique(player))
				{
					DraconiqueArmor.applyEnergyShield(player);
				}
				else
				{
					// Envoyer un message d'erreur au joueur
					player.sendMessage(new TextComponentString("Vous devez porter une armure complète de Draconium pour activer le bouclier énergétique."));
				}
			});
			return null;
		}
	}
}
