package fr.draconium.core.init.items.swords;

import java.util.ArrayList;
import java.util.List;

import fr.draconium.core.items.swords.SwordBasic;
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
public class SwordsInit
{
	
	private static List<Item> swords = new ArrayList<>();
	
	public static Item AZURITE_SWORD;
	public static Item DRACONIUM_SWORD;
	
	public static void init()
	{
		swords.add(AZURITE_SWORD 	= new SwordBasic("azurite_sword", ToolsMaterial.TOOLS_MATERIAL_AZURITE));
		swords.add(DRACONIUM_SWORD 	= new SwordBasic("draconium_sword", ToolsMaterial.TOOLS_MATERIAL_DRACONIUM));
	}
	
	@SubscribeEvent
	protected static void registerItems(RegistryEvent.Register<Item> event)
	{
		for (Item sword : swords)
		{
			event.getRegistry().registerAll(sword);
			Console.debug("Enregistrement de l'éppée: #6FF7D0" + sword.getRegistryName());
		}
	}
	
	@SubscribeEvent
	protected static void registerRenders(ModelRegistryEvent event)
	{
		for (Item sword : swords)
		{
			registerRender(sword);
			Console.debug("Enregistrement du rendu de l'éppée: #6FF794" + sword.getRegistryName());
		}
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
