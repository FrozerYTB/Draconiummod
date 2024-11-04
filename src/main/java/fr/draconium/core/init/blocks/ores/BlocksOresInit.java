package fr.draconium.core.init.blocks.ores;

import fr.draconium.core.blocks.BlockBasic;
import fr.draconium.core.blocks.BlockExplosiveOre;
import fr.draconium.core.references.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class BlocksOresInit
{
	public static Block AZURITE_ORE;
	public static Block DRACONIUM_ORE;
	public static Block FINDIUM_ORE;
	public static Block EXPLOSIVE_ORE;
	
	public static void init()
	{
		AZURITE_ORE 		= new BlockBasic("azurite_ore", Material.ROCK);
		DRACONIUM_ORE 		= new BlockBasic("draconium_ore", Material.ROCK);
		FINDIUM_ORE 		= new BlockBasic("findium_ore", Material.ROCK);
		EXPLOSIVE_ORE 		= new BlockExplosiveOre("explosive_ore", Material.ROCK);
	}
	
	@SubscribeEvent
	protected static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(
				AZURITE_ORE,
				DRACONIUM_ORE,
				FINDIUM_ORE,
				EXPLOSIVE_ORE
		);
	}
	
	@SubscribeEvent
	protected static void registerItemBlocks(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				new ItemBlock(AZURITE_ORE).setRegistryName(AZURITE_ORE.getRegistryName()),
				new ItemBlock(DRACONIUM_ORE).setRegistryName(DRACONIUM_ORE.getRegistryName()),
				new ItemBlock(FINDIUM_ORE).setRegistryName(FINDIUM_ORE.getRegistryName()),
				new ItemBlock(EXPLOSIVE_ORE).setRegistryName(EXPLOSIVE_ORE.getRegistryName())
		);
	}
	
	/**
	 * @apiNote Get json model
	 */
	@SubscribeEvent
	protected static void regsiterRenders(ModelRegistryEvent event)
	{
		registerRender(Item.getItemFromBlock(AZURITE_ORE));
		registerRender(Item.getItemFromBlock(DRACONIUM_ORE));
		registerRender(Item.getItemFromBlock(FINDIUM_ORE));
		registerRender(Item.getItemFromBlock(EXPLOSIVE_ORE));
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
