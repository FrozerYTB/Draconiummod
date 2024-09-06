package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.objects.blocks.BlockBasic;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockBasicInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //BLOCS
    public static final Block AZURITE_BLOCK = new BlockBasic("azurite_block", Material.IRON);
    public static final Block DRACONIUM_BLOCK = new BlockBasic("draconium_block", Material.IRON);


    public static void init() {
        BLOCKS.add(AZURITE_BLOCK);
        BLOCKS.add(DRACONIUM_BLOCK);
    }
}
