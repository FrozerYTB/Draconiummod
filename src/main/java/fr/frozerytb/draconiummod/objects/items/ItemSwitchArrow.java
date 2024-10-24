package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;


import fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow.SwitchArrowEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
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
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        SwitchArrowEntity entitySwitchArrow = new SwitchArrowEntity(worldIn, shooter);
        return entitySwitchArrow;
    }
}
