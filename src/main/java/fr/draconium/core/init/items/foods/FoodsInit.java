package fr.draconium.core.init.items.foods;

import fr.draconium.core.DraconiumCore;
import fr.draconium.core.items.ItemBasic;
import fr.draconium.core.items.foods.FoodEffect;
import fr.draconium.core.references.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class FoodsInit
{
	
	public static Item DRACONIUM_APPLE;
	
	public static void init()
	{
		DRACONIUM_APPLE = new FoodEffect("draconium_apple", 8);
	}
	
	@SubscribeEvent
	protected static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				DRACONIUM_APPLE	
		);
	}
	
	@SubscribeEvent
	protected static void registerRenders(ModelRegistryEvent event)
	{
		registerRender(DRACONIUM_APPLE);
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
