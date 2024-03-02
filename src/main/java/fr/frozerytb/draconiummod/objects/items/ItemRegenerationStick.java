package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRegenerationStick extends Item implements IHasmodel {

    private static final int COOLDOWN_TICKS = 5 * 60 * 20;
    private static final int DURABILITY = 5;

    public ItemRegenerationStick(String name) {
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

            // Vérifie si le cooldown est écoulé
            if (!playerIn.getCooldownTracker().hasCooldown(this) && itemstack.getItemDamage() < itemstack.getMaxDamage()) {
                // Fait quelque chose lorsque l'item est utilisé
                // ...
                itemstack.damageItem(1, playerIn);

                // Si la durabilité atteint zéro, l'item est détruit
                if (itemstack.getItemDamage() == itemstack.getMaxDamage()) {
                    // Fais quelque chose lorsque la durabilité est épuisée
                    // ...
                    // Par exemple, tu peux détruire l'item ici ou lui attribuer un effet spécial.
                }

                // Applique le cooldown
                playerIn.getCooldownTracker().setCooldown(this, COOLDOWN_TICKS);
                playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 300, 2));
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            } else {
                return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
            }
        }
    }
}
