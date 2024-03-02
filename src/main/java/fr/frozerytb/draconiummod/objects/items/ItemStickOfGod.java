package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemStickOfGod extends Item implements IHasmodel {

    private static final int COOLDOWN_TICKS = 5 * 60 * 20;
    private static final int DURABILITY = 5;
    public ItemStickOfGod(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxDamage(15);
        setMaxStackSize(1);
        setCreativeTab(Main.DraconiummodTab);
        ItemInit.ITEMS.add(this);
    }



    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            if (!playerIn.getCooldownTracker().hasCooldown(this) && itemstack.getItemDamage() < itemstack.getMaxDamage()) {

                playerIn.getCooldownTracker().setCooldown(this, COOLDOWN_TICKS);

                itemstack.damageItem(1, playerIn);

                // Si la durabilité atteint zéro, l'item est détruit
                if (itemstack.getItemDamage() == itemstack.getMaxDamage()) {
                    // Fais quelque chose lorsque la durabilité est épuisée
                    // ...
                    // Par exemple, tu peux détruire l'item ici ou lui attribuer un effet spécial.
                }

                playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4440, 1));
                playerIn.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 4440, 1));
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            } else {
                return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
            }
        }
    }
}


