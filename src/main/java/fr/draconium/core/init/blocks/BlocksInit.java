package fr.draconium.core.init.blocks;

import java.util.ArrayList;
import java.util.List;

import fr.draconium.core.blocks.BlockBasic;
import fr.draconium.core.blocks.BlockCaveBlock;
import fr.draconium.core.blocks.BlockElevator;
import fr.draconium.core.blocks.BlockFluid;
import fr.draconium.core.init.items.liquids.FluidInit;
import fr.draconium.core.messages.Console;
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
public class BlocksInit
{
	private static List<Block> blocks = new ArrayList<>();
	
	//Blocks
	public static Block AZURITE_BLOCK;
	public static Block DRACONIUM_BLOCK;
	
	//Others
	public static Block CAVE_BLOCK;
	public static Block ELEVATOR;
	
	public static Block FAKE_WATER_FLUID_BLOCK;
	
	public static void init()
	{
		blocks.add(AZURITE_BLOCK 			= new BlockBasic("azurite_block", Material.IRON));
		blocks.add(DRACONIUM_BLOCK 			= new BlockBasic("draconium_block", Material.IRON));
		
		blocks.add(CAVE_BLOCK 				= new BlockCaveBlock("cave_block", Material.GLASS));
		blocks.add(ELEVATOR 				= new BlockElevator("elevator", Material.IRON));
		
		blocks.add(FAKE_WATER_FLUID_BLOCK 	= new BlockFluid("fake_water_fluid", FluidInit.FAKE_WATER_FLUID, Material.WATER));
	}
	
	
	
	@SubscribeEvent
	protected static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		Console.debug("- Enregistrement des blocks:");
		for (Block block : blocks)
		{
			event.getRegistry().registerAll(block);
			Console.debug("  - #6FF7D0" + block.getRegistryName());
		}
	}
	
	@SubscribeEvent
	protected static void registerItemBlocks(RegistryEvent.Register<Item> event)
	{
		for (Block block : blocks)
		{
			event.getRegistry().registerAll(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}
	}
	
	/**
	 * @apiNote Get json model
	 */
	@SubscribeEvent
	protected static void regsiterRenders(ModelRegistryEvent event)
	{
		for (Block block : blocks)
		{
			registerRender(Item.getItemFromBlock(block));
		}
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
