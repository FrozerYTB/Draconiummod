package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.blocks.BlockBasic;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block AZURITE_ORE = new BlockBasic("azurite_ore", Material.ROCK);
    public static final Block DRACONIUM_ORE = new BlockBasic("draconium_ore", Material.ROCK);
    public static final Block FINDIUM_ORE = new BlockBasic("findium_ore", Material.ROCK);
}
