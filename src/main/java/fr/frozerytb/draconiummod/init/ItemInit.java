package fr.frozerytb.draconiummod.init;

import java.util.ArrayList;
import java.util.List;

import fr.frozerytb.draconiummod.objects.items.ItemBase;
import net.minecraft.item.Item;

public class ItemInit 
{
	public static List<Item> ITEMS = new ArrayList<Item>();
	
	//LINGOT
	public static final Item AZURITE_INGOT = new ItemBase("azurite_ingot");
	public static final Item DRACONIUM_INGOT = new ItemBase("draconium_ingot");
	public static final Item FINDIUM_CRYSTAL = new ItemBase("findium_cristal");
}
