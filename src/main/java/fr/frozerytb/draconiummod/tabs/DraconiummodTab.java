package fr.frozerytb.draconiummod.tabs;

import fr.frozerytb.draconiummod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class DraconiummodTab extends CreativeTabs {
    public DraconiummodTab(String label) {
        super(label);
        this.setBackgroundImageName(label + ".png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemInit.DRACONIUM_INGOT);
    }
}

