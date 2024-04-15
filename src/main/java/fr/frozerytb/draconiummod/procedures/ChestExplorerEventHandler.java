package fr.frozerytb.draconiummod.procedures;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "ton_mod_id")
public class ChestExplorerEventHandler {

    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        if (event.getWorld().isRemote) // On vérifie si c'est côté client
            return;

        if (event.getItemStack().getItem() instanceof YourChestExplorerItem) { // Remplace "YourChestExplorerItem" par le nom de ton item Chest Explorer
            if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockChest) { // Vérifie si le bloc cliqué est un coffre
                EntityPlayer player = event.getEntityPlayer();
                EnumHand hand = event.getHand();

                // Code pour vérifier la zone claim et afficher l'hologramme
                // Ajoute tes appels de méthode ici pour vérifier la zone claim
                // et afficher l'hologramme du contenu du coffre
            }
        }
    }
}
