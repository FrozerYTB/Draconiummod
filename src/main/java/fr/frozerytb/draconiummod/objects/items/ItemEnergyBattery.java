package fr.frozerytb.draconiummod.objects.items;


import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEnergyBattery extends Item implements IHasmodel {
    public ItemEnergyBattery(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(Main.DraconiummodTab);
        this.setMaxStackSize(8);
        ItemInit.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }

    public int getEnergy(ItemStack batteryStack) {
    }
}

