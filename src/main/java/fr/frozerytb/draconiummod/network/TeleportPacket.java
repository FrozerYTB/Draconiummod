package fr.frozerytb.draconiummod.network;

import fr.frozerytb.draconiummod.init.ItemInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import fr.frozerytb.draconiummod.init.ArmorBase;

public class TeleportPacket implements IMessage {

    public TeleportPacket() {}

    @Override
    public void toBytes(ByteBuf buf) {
        // Pas besoin de données supplémentaires pour cette action
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        // Pas besoin de données supplémentaires pour cette action
    }

    public static class Handler implements IMessageHandler<TeleportPacket, IMessage> {

        @Override
        public IMessage onMessage(TeleportPacket message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;

            // Vérifie si le joueur porte l'armure complète de Draconium
            if (hasFullDraconiqueArmor(player)) {
                player.getServerWorld().addScheduledTask(() -> ArmorBase.teleportRandomly(player));
            } else {
                // Envoyer un message d'erreur au joueur
                player.sendMessage(new TextComponentString("Vous devez porter une armure complète de Draconium pour vous téléporter."));
            }

            return null;
        }

        private boolean hasFullDraconiqueArmor(EntityPlayerMP player) {
            ItemStack helmet = player.inventory.armorInventory.get(0);
            ItemStack chestplate = player.inventory.armorInventory.get(1);
            ItemStack leggings = player.inventory.armorInventory.get(2);
            ItemStack boots = player.inventory.armorInventory.get(3);

            return helmet != null && helmet.getItem() == ItemInit.DRACONIQUE_HELMET &&
                    chestplate != null && chestplate.getItem() == ItemInit.DRACONIQUE_CHESTPLATE &&
                    leggings != null && leggings.getItem() == ItemInit.DRACONIQUE_LEGGINGS &&
                    boots != null && boots.getItem() == ItemInit.DRACONIQUE_BOOTS;
        }
    }
}
