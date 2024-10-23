package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.blocks.BlockBasic;
import fr.frozerytb.draconiummod.objects.blocks.BlockCaveBlock;
import fr.frozerytb.draconiummod.objects.blocks.BlockElevator;
import fr.frozerytb.draconiummod.objects.blocks.BlockExplosiveOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block AZURITE_BLOCK = new BlockBasic("azurite_block", Material.IRON);
    public static final Block DRACONIUM_BLOCK = new BlockBasic("draconium_block", Material.IRON);

    //BLOCS DE MINERAIS
    public static final Block AZURITE_ORE = new BlockBasic("azurite_ore", Material.ROCK);
    public static final Block DRACONIUM_ORE = new BlockBasic("draconium_ore", Material.ROCK);
    public static final Block FINDIUM_ORE = new BlockBasic("findium_ore", Material.ROCK);
    public static final Block EXPLOSIVE_ORE = new BlockExplosiveOre("explosive_ore", Material.ROCK);

    // Blocs spéciaux
    public static final Block CAVE_BLOCK = new BlockCaveBlock("cave_block", Material.GLASS);
    public static final Block ELEVATOR = new BlockElevator("elevator", Material.IRON);









}