package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.objects.blocks.BlockBasic;
import fr.frozerytb.draconiummod.objects.blocks.BlockExplosiveOre;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockOreInit {
    public static final List<Block> ORES = new ArrayList<Block>();

    //BLOCS DE MINERAIS
    public static final Block AZURITE_ORE = new BlockBasic("azurite_ore", Material.ROCK);
    public static final Block DRACONIUM_ORE = new BlockBasic("draconium_ore", Material.ROCK);
    public static final Block FINDIUM_ORE = new BlockBasic("findium_ore", Material.ROCK);
    public static final Block EXPLOSIVE_ORE = new BlockExplosiveOre("explosive_ore", Material.ROCK);



    public static void init() {
        ORES.add(AZURITE_ORE);
        ORES.add(DRACONIUM_ORE);
        ORES.add(FINDIUM_ORE);
        ORES.add(EXPLOSIVE_ORE);
    }
}

