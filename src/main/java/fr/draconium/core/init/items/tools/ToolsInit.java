package fr.draconium.core.init.items.tools;

import java.util.ArrayList;
import java.util.List;

import fr.draconium.core.items.tools.AxeBasic;
import fr.draconium.core.items.tools.HammerBasic;
import fr.draconium.core.items.tools.PickaxeBasic;
import fr.draconium.core.items.tools.ShovelBasic;
import fr.draconium.core.materials.ToolsMaterial;
import fr.draconium.core.messages.Console;
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
	
	private static List<Item> tools = new ArrayList<>();
	
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
		tools.add(AZURITE_AXE 			= new AxeBasic("azurite_axe", ToolsMaterial.TOOLS_MATERIAL_AZURITE));
		tools.add(AZURITE_PICKAXE 		= new PickaxeBasic("azurite_pickaxe", ToolsMaterial.TOOLS_MATERIAL_AZURITE));
		tools.add(AZURITE_SHOVEL 		= new ShovelBasic("azurite_shovel", ToolsMaterial.TOOLS_MATERIAL_AZURITE));
		tools.add(AZURITE_HAMMER 		= new HammerBasic("azurite_hammer", ToolsMaterial.HAMMER_MATERIAL_AZURITE));
		
		tools.add(DRACONIUM_AXE 		= new AxeBasic("draconium_axe", ToolsMaterial.TOOLS_MATERIAL_DRACONIUM));
		tools.add(DRACONIUM_PICKAXE 	= new PickaxeBasic("draconium_pickaxe", ToolsMaterial.TOOLS_MATERIAL_DRACONIUM));
		tools.add(DRACONIUM_SHOVEL 		= new ShovelBasic("draconium_shovel", ToolsMaterial.TOOLS_MATERIAL_DRACONIUM));
		tools.add(DRACONIUM_HAMMER 		= new HammerBasic("draconium_hammer", ToolsMaterial.HAMMER_MATERIAL_DRACONIUM));
	}
	
	@SubscribeEvent
	protected static void registerItems(RegistryEvent.Register<Item> event)
	{
		for (Item tool : tools)
		{
			event.getRegistry().registerAll(tool);
			Console.debug("Enregistrement de l'outil: #6FF7D0" + tool.getRegistryName());
		}
	}
	
	@SubscribeEvent
	protected static void registerRenders(ModelRegistryEvent event)
	{
		for (Item tool : tools)
		{
			registerRender(tool);
			Console.debug("Enregistrement du rendu de l'outil: #6FF794" + tool.getRegistryName());
		}
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
