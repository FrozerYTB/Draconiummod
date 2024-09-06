package fr.frozerytb.draconiummod.util.handlers;

import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.FluidInit;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.block.state.IBlockState;
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
        Item fluidBucket = Item.getItemFromBlock(BlockInit.FAKE_WATER_BLOCK);
        if (fluidBucket != null) {
            ModelLoader.setCustomModelResourceLocation(fluidBucket, 0,
                    new ModelResourceLocation(Reference.MODID + ":fake_water", "inventory"));
        } else {
            System.err.println("Error: Fluid bucket item is null. Make sure it is initialized before calling registerCustomMeshesAndStates.");
        }

        ModelLoader.setCustomStateMapper(BlockInit.FAKE_WATER_BLOCK, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(Reference.MODID + ":fake_water", "fluid");
            }
        });
    }
}
