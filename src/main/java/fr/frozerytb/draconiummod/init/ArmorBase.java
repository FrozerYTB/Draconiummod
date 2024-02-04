package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase  extends ItemArmor implements IHasmodel {

    public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.DraconiummodTab);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }
}

