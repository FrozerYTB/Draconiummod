package fr.frozerytb.draconiummod.util.handlers;

import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHandler {

    public static void registerCustomMeshesAndStates() {
        if (BlockInit.FAKE_WATER_FLUID != null) {
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockInit.FAKE_WATER_FLUID),
                    new ItemMeshDefinition() {
                        @Override
                        public ModelResourceLocation getModelLocation(ItemStack stack) {
                            return new ModelResourceLocation(Reference.MODID + ":fake_water", "fluid");
                        }
                    });

            ModelLoader.setCustomStateMapper(BlockInit.FAKE_WATER_FLUID, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(Reference.MODID + ":fake_water", "fluid");
                }
            });
        } else {
            System.err.println("Error: BlockInit.FAKE_WATER_FLUID is null. Make sure it is initialized before calling registerCustomMeshesAndStates.");
        }
    }
}
