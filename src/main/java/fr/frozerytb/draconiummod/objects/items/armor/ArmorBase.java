package fr.frozerytb.draconiummod.objects.items.armor;

import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase  extends ItemArmor implements IHasmodel {

    public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.COMBAT);
        ItemInit.ITEMS.add(this);
    }
}
