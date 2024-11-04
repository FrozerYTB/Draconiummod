package fr.draconium.core.init.items.others;

import fr.draconium.core.DraconiumCore;
import fr.draconium.core.items.ItemBasic;
import fr.draconium.core.items.others.ItemGrenade;
import fr.draconium.core.items.others.ItemRadar;
import fr.draconium.core.items.others.ItemRegenerationStick;
import fr.draconium.core.items.others.ItemSpongeOnAStick;
import fr.draconium.core.items.others.ItemStickOfGod;
import fr.draconium.core.references.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class OthersInit
{
	
	public static Item STICK_OF_GOD;
	public static Item REGENERATION_STICK;
	public static Item RADAR;
	public static Item GRENADE;
	public static Item DEBRIS_GRENADE;
	public static Item SPONGE_ON_A_STICK;
	
	public static void init()
	{
		STICK_OF_GOD 		= new ItemStickOfGod("stick_of_god");
		REGENERATION_STICK 	= new ItemRegenerationStick("regeneration_stick");
		RADAR 				= new ItemRadar("radar");
		GRENADE 			= new ItemGrenade("grenade");
		DEBRIS_GRENADE 		= new ItemBasic("debris_grenade").setCreativeTab(DraconiumCore.DRACONIUM_TAB_OTHERS);
		SPONGE_ON_A_STICK 	= new ItemSpongeOnAStick("sponge_on_a_stick");
	}
	
	@SubscribeEvent
	protected static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				STICK_OF_GOD,
				REGENERATION_STICK,
				RADAR,
				GRENADE,
				DEBRIS_GRENADE,
				SPONGE_ON_A_STICK
		);
	}
	
	@SubscribeEvent
	protected static void registerRenders(ModelRegistryEvent event)
	{
		registerRender(STICK_OF_GOD);
		registerRender(REGENERATION_STICK);
		registerRender(RADAR);
		registerRender(GRENADE);
		registerRender(DEBRIS_GRENADE);
		registerRender(SPONGE_ON_A_STICK);
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}