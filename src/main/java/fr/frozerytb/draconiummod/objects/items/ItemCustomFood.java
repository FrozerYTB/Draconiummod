package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCustomFood extends ItemFood implements IHasmodel {
    public ItemCustomFood(String name, int amout, boolean isWolfFood) {
        super(amout, isWolfFood);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(Main.DRACONIUMMOD_TAB);
        this.setAlwaysEdible();
        ItemInit.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 2));
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 1));
        }

        super.onFoodEaten(stack, world, player);
    }
}
