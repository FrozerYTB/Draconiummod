package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.blocks.BlockBasic;
import fr.frozerytb.draconiummod.objects.blocks.CaveBlock;
import fr.frozerytb.draconiummod.objects.blocks.Elevator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static List<Block> BLOCKS = new ArrayList<Block>();


    //BLOCS DE MINERAIS

    public static final Block AZURITE_ORE = new BlockBasic("azurite_ore", Material.ROCK);
    public static final Block DRACONIUM_ORE = new BlockBasic("draconium_ore", Material.ROCK);
    public static final Block FINDIUM_ORE = new BlockBasic("findium_ore", Material.ROCK);

    //BLOCS
    public static final Block AZURITE_BLOCK = new BlockBasic("azurite_block", Material.IRON);
    public static final Block DRACONIUM_BLOCK = new BlockBasic("draconium_block", Material.IRON);

    //AUTRES

    public static final Block CAVE_BLOCK = new CaveBlock("cave_block", Material.GLASS);

    public static final Block ELEVATOR = new Elevator("elevator", Material.IRON);
}
