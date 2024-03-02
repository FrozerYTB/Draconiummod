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

    private static final int COOLDOWN_TICKS = 90 * 20;
    private static final int MAX_DURABILITY = 11;

    public ItemRegenerationStick(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxDamage(MAX_DURABILITY - 1); // Adjusted to start at 0 and go up to MAX_DURABILITY - 1
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
                itemstack.damageItem(1, playerIn);
                playerIn.getCooldownTracker().setCooldown(this, COOLDOWN_TICKS);
                playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 300, 3));

                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
            } else {
                return new ActionResult<>(EnumActionResult.PASS, itemstack);
            }
        }

        return new ActionResult<>(EnumActionResult.PASS, itemstack);
    }
}