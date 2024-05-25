package fr.frozerytb.draconiummod.objects.blocks;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.objects.TilesEntity.TileEntityEnergyFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockEnergyFurnace extends Block {
    public BlockEnergyFurnace(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.DraconiummodTab);
        setHardness(8.0F);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true; // Indiquer que ce bloc a une TileEntity associée
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityEnergyFurnace(); // Créer et renvoyer une instance de la TileEntity associée à ce bloc
    }
}
