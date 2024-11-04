package fr.draconium.core.init.items.ores;

import fr.draconium.core.DraconiumCore;
import fr.draconium.core.items.ItemBasic;
import fr.draconium.core.references.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class OresInit
{

	public static Item AZURITE_INGOT;
	public static Item DRACONIUM_INGOT;
	public static Item FINDIUM_CRISTAL;
	
	public static void init()
	{
		AZURITE_INGOT 	= new ItemBasic("azurite_ingot").setCreativeTab(DraconiumCore.DRACONIUM_TAB_BLOCK);
		DRACONIUM_INGOT = new ItemBasic("draconium_ingot").setCreativeTab(DraconiumCore.DRACONIUM_TAB_BLOCK);
		FINDIUM_CRISTAL = new ItemBasic("findium_cristal").setCreativeTab(DraconiumCore.DRACONIUM_TAB_BLOCK);
	}
	
	@SubscribeEvent
	protected static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				AZURITE_INGOT,
				DRACONIUM_INGOT,
				FINDIUM_CRISTAL
		);
	}
	
	@SubscribeEvent
	protected static void registerRenders(ModelRegistryEvent event)
	{
		registerRender(AZURITE_INGOT);
		registerRender(DRACONIUM_INGOT);
		registerRender(FINDIUM_CRISTAL);
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
