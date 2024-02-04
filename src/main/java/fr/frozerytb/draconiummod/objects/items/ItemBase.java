package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasmodel
{
	public ItemBase(String name)
	{
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
