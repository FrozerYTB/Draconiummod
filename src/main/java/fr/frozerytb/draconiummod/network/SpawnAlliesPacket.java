package fr.frozerytb.draconiummod.network;

import fr.frozerytb.draconiummod.init.ArmorBase;
import fr.frozerytb.draconiummod.init.ItemInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SpawnAlliesPacket implements IMessage {

    // Constructor vide pour la désérialisation
    public SpawnAlliesPacket() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        // Lire les données du buffer si nécessaire
    }

    @Override
    public void toBytes(ByteBuf buf) {
        // Ecrire les données dans le buffer si nécessaire
    }

    public static class Handler implements IMessageHandler<SpawnAlliesPacket, IMessage> {

        @Override
        public IMessage onMessage(SpawnAlliesPacket message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;

            // Vérifie si le joueur porte l'armure complète de Draconium
            if (hasFullDraconiqueArmor(player)) {
                player.getServerWorld().addScheduledTask(() -> {
                    ArmorBase.spawnAllies(player);
                });
            } else {
                // Envoyer un message d'erreur au joueur
                player.sendMessage(new TextComponentString("Vous devez porter une armure complète de Draconium pour invoquer des alliés."));
            }

            return null;
        }

        // Méthode pour vérifier l'armure Draconium
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
