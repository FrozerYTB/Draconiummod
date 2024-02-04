package fr.frozerytb.draconiummod.objects.items.tools;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;

public class ToolPickaxe extends ItemPickaxe implements IHasmodel {
    public ToolPickaxe(String name, Item.ToolMaterial material)
    {
        super(material);
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
