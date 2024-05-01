package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import fr.frozerytb.draconiummod.objects.entity.EntityGrenade;

public class ItemGrenade extends Item {

    public ItemGrenade(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(Main.DraconiummodTab);
        this.setMaxStackSize(16);
        ItemInit.ITEMS.add(this);
    }

    // Action lorsqu'un joueur utilise l'objet grenade
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        // Créer et lancer une entité de grenade
        if (!worldIn.isRemote) {
            EntityGrenade grenade = new EntityGrenade(worldIn, playerIn);
            grenade.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(grenade);
        }

        // Jouer un son de lancer de grenade
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);

        // Retirer une grenade de l'inventaire du joueur
        itemstack.shrink(1);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}