package fr.frozerytb.draconiummod.objects.blocks.fluids;

import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.ItemInit;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFakeWaterFluid extends BlockFluidClassic{
    public BlockFakeWaterFluid(String name, Fluid fluid, Material material)
    {
        super(fluid, material);
        setUnlocalizedName(name);
        setRegistryName(name);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}

