package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow.SwitchArrowEntity;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemSwitchArrow extends ItemArrow {

    public ItemSwitchArrow(String name) {
        super();
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.DRACONIUMMOD_TAB);
        setMaxStackSize(64);

        ItemInit.ITEMS.add(this);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            SwitchArrowEntity arrow = new SwitchArrowEntity(world, player);
            arrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
            world.spawnEntity(arrow);
            player.getCooldownTracker().setCooldown(this, 20); // cooldown de 20 ticks
            return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
        }
        return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
    }
}
