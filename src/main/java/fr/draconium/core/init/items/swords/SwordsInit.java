package fr.draconium.core.init.items.swords;

import fr.draconium.core.items.swords.SwordBasic;
import fr.draconium.core.items.tools.AxeBasic;
import fr.draconium.core.items.tools.HammerBasic;
import fr.draconium.core.items.tools.PickaxeBasic;
import fr.draconium.core.items.tools.ShovelBasic;
import fr.draconium.core.materials.ToolsMaterial;
import fr.draconium.core.references.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class SwordsInit
{
	public static Item AZURITE_SWORD;
	public static Item DRACONIUM_SWORD;
	
	public static void init()
	{
		AZURITE_SWORD 		= new SwordBasic("azurite_sword", ToolsMaterial.TOOLS_MATERIAL_AZURITE);
		DRACONIUM_SWORD 	= new SwordBasic("draconium_sword", ToolsMaterial.TOOLS_MATERIAL_DRACONIUM);
	}
	
	@SubscribeEvent
	protected static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				AZURITE_SWORD,
				DRACONIUM_SWORD
		);
	}
	
	@SubscribeEvent
	protected static void registerRenders(ModelRegistryEvent event)
	{
		registerRender(AZURITE_SWORD);
		registerRender(DRACONIUM_SWORD);
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
