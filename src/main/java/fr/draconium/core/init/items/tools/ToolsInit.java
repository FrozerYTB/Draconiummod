package fr.draconium.core.init.items.tools;

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
public class ToolsInit
{
	
	public static Item AZURITE_AXE;
	public static Item AZURITE_PICKAXE;
	public static Item AZURITE_SHOVEL;
	public static Item AZURITE_HAMMER;

	public static Item DRACONIUM_AXE;
	public static Item DRACONIUM_PICKAXE;
	public static Item DRACONIUM_SHOVEL;
	public static Item DRACONIUM_HAMMER;
	
	public static void init()
	{
		AZURITE_AXE 		= new AxeBasic("azurite_axe", ToolsMaterial.TOOLS_MATERIAL_AZURITE);
		AZURITE_PICKAXE 	= new PickaxeBasic("azurite_pickaxe", ToolsMaterial.TOOLS_MATERIAL_AZURITE);
		AZURITE_SHOVEL 		= new ShovelBasic("azurite_shovel", ToolsMaterial.TOOLS_MATERIAL_AZURITE);
		AZURITE_HAMMER 		= new HammerBasic("azurite_hammer", ToolsMaterial.HAMMER_MATERIAL_AZURITE);
		
		DRACONIUM_AXE 		= new AxeBasic("draconium_axe", ToolsMaterial.TOOLS_MATERIAL_DRACONIUM);
		DRACONIUM_PICKAXE 	= new PickaxeBasic("draconium_pickaxe", ToolsMaterial.TOOLS_MATERIAL_DRACONIUM);
		DRACONIUM_SHOVEL 	= new ShovelBasic("draconium_shovel", ToolsMaterial.TOOLS_MATERIAL_DRACONIUM);
		DRACONIUM_HAMMER 	= new HammerBasic("draconium_hammer", ToolsMaterial.HAMMER_MATERIAL_DRACONIUM);
	}
	
	@SubscribeEvent
	protected static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				AZURITE_AXE,
				AZURITE_PICKAXE,
				AZURITE_SHOVEL,
				AZURITE_HAMMER,
				
				DRACONIUM_AXE,
				DRACONIUM_PICKAXE,
				DRACONIUM_SHOVEL,
				DRACONIUM_HAMMER
		);
	}
	
	@SubscribeEvent
	protected static void registerRenders(ModelRegistryEvent event)
	{
		registerRender(AZURITE_AXE);
		registerRender(AZURITE_PICKAXE);
		registerRender(AZURITE_SHOVEL);
		registerRender(AZURITE_HAMMER);
		
		registerRender(DRACONIUM_AXE);
		registerRender(DRACONIUM_PICKAXE);
		registerRender(DRACONIUM_SHOVEL);
		registerRender(DRACONIUM_HAMMER);
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
