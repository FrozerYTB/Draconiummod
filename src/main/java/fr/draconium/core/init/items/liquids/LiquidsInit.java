package fr.draconium.core.init.items.liquids;

import fr.draconium.core.blocks.BlockFluid;
import fr.draconium.core.blocks.FluidBasic;
import fr.draconium.core.references.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class LiquidsInit
{
	public static Block FLUID_BLOCK;
	public static Fluid FLUID_TEXTURE;
	
	public static void init()
	{
		FLUID_TEXTURE 	= 	new FluidBasic("fluid", 
							new ResourceLocation(Reference.MODID, "blocks/fluids/fluid_texture"), 
							new ResourceLocation(Reference.MODID, "blocks/fluids/fluid_texture"));
		FLUID_BLOCK 	= 	new BlockFluid("fluid", FLUID_TEXTURE, Material.WATER);
	}
	
	@SubscribeEvent
	protected static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(
				FLUID_BLOCK
		);
	}
	
	@SubscribeEvent
	protected static void registerItemBlocks(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				new ItemBlock(FLUID_BLOCK).setRegistryName(FLUID_BLOCK.getRegistryName())
		);
	}
	
	/**
	 * @apiNote Get json model
	 */
	@SubscribeEvent
	protected static void regsiterRenders(ModelRegistryEvent event)
	{
		registerRender(Item.getItemFromBlock(FLUID_BLOCK));
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
