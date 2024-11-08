package fr.draconium.core.handlers;

import fr.draconium.core.init.blocks.BlocksInit;
import fr.draconium.core.references.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class RenderHandler
{
	public static void registerCustomMeshesAndStates()
	{
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlocksInit.FAKE_WATER_FLUID_BLOCK), new ItemMeshDefinition()
		{
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				return new ModelResourceLocation(Reference.MODID + ":fake_water_fluid", "fluid");
			}
		});
		
		ModelLoader.setCustomStateMapper(BlocksInit.FAKE_WATER_FLUID_BLOCK, new StateMapperBase()
		{
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				return new ModelResourceLocation(Reference.MODID + ":fake_water_fluid", "fluid");
			}
		});
	}
}
