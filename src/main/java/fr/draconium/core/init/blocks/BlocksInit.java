package fr.draconium.core.init.blocks;

import fr.draconium.core.DraconiumCore;
import fr.draconium.core.blocks.BlockBasic;
import fr.draconium.core.blocks.BlockCaveBlock;
import fr.draconium.core.blocks.BlockElevator;
import fr.draconium.core.blocks.BlockExplosiveOre;
import fr.draconium.core.blocks.BlockFluid;
import fr.draconium.core.init.items.liquids.FluidInit;
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
	//Blocks
	public static Block AZURITE_BLOCK;
	public static Block DRACONIUM_BLOCK;
	
	//Others
	public static Block CAVE_BLOCK;
	public static Block ELEVATOR;
	
	public static Block DRACONIUM_FLUID_BLOCK;
	
	public static void init()
	{
		AZURITE_BLOCK 			= new BlockBasic("azurite_block", Material.IRON);
		DRACONIUM_BLOCK 		= new BlockBasic("draconium_block", Material.IRON);
		
		CAVE_BLOCK 				= new BlockCaveBlock("cave_block", Material.GLASS);
		ELEVATOR 				= new BlockElevator("elevator", Material.IRON);
		
		DRACONIUM_FLUID_BLOCK 	= new BlockFluid("draconium_fluid", FluidInit.DRACONIUM_FLUID, Material.LAVA);
	}
	
	@SubscribeEvent
	protected static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(
				AZURITE_BLOCK,
				DRACONIUM_BLOCK,
				
				CAVE_BLOCK,
				ELEVATOR,
				
				DRACONIUM_FLUID_BLOCK
		);
	}
	
	@SubscribeEvent
	protected static void registerItemBlocks(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				new ItemBlock(AZURITE_BLOCK).setRegistryName(AZURITE_BLOCK.getRegistryName()),
				new ItemBlock(DRACONIUM_BLOCK).setRegistryName(DRACONIUM_BLOCK.getRegistryName()),
				
				new ItemBlock(CAVE_BLOCK).setRegistryName(CAVE_BLOCK.getRegistryName()),
				new ItemBlock(ELEVATOR).setRegistryName(ELEVATOR.getRegistryName()),
				
				new ItemBlock(DRACONIUM_FLUID_BLOCK).setRegistryName(DRACONIUM_FLUID_BLOCK.getRegistryName())
		);
	}
	
	/**
	 * @apiNote Get json model
	 */
	@SubscribeEvent
	protected static void regsiterRenders(ModelRegistryEvent event)
	{
		registerRender(Item.getItemFromBlock(AZURITE_BLOCK));
		registerRender(Item.getItemFromBlock(DRACONIUM_BLOCK));
		
		registerRender(Item.getItemFromBlock(CAVE_BLOCK));
		registerRender(Item.getItemFromBlock(ELEVATOR));
		
		registerRender(Item.getItemFromBlock(DRACONIUM_FLUID_BLOCK));
		//...
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
