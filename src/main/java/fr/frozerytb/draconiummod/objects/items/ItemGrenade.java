package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemGrenade extends Item {

    public ItemGrenade(String name) {
        super();
        this.setUnlocalizedName("grenade");
        this.setCreativeTab(Main.DraconiummodTab);
        this.setMaxStackSize(16);
        ItemInit.ITEMS.add(this);
    }

    // Action lorsqu'un joueur utilise l'objet grenade
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        // Joue un son d'explosion
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.PLAYERS, 1.0F, 1.0F);
        // Explose la grenade
        if (!worldIn.isRemote) {
            worldIn.createExplosion(null, playerIn.posX, playerIn.posY, playerIn.posZ, 6.0F, true);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}