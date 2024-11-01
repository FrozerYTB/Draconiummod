package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;


import fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow.EntitySwitchArrow;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwitchArrow extends ItemArrow implements IHasmodel {

    public ItemSwitchArrow(String name) {
        super();
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.DRACONIUMMOD_TAB);
        setMaxStackSize(64);

        ItemInit.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        EntitySwitchArrow entitySwitchArrow = new EntitySwitchArrow(worldIn, shooter);
        return entitySwitchArrow;
    }
}
