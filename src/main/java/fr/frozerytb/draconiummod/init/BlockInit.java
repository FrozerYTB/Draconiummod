package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.blocks.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
<<<<<<< HEAD
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
=======
>>>>>>> origin/master

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block AZURITE_BLOCK = new BlockBasic("azurite_block", Material.IRON);
    public static final Block DRACONIUM_BLOCK = new BlockBasic("draconium_block", Material.IRON);

<<<<<<< HEAD
    public static void init() {
        // Assurez-vous que les fluides sont enregistrés d'abord
        FluidInit.initFluids();

        // Ajoutez les blocs après l'enregistrement des fluides
        BLOCKS.add(FAKE_WATER_BLOCK);

        BlockOreInit.init();
        BlockBasicInit.init();
        BlockSpecialInit.init();
        BLOCKS.addAll(BlockOreInit.ORES);
        BLOCKS.addAll(BlockBasicInit.BLOCKS);
        BLOCKS.addAll(BlockSpecialInit.SPECIAL_BLOCKS);

        registerBlocks();
    }
=======
    //BLOCS DE MINERAIS
    public static final Block AZURITE_ORE = new BlockBasic("azurite_ore", Material.ROCK);
    public static final Block DRACONIUM_ORE = new BlockBasic("draconium_ore", Material.ROCK);
    public static final Block FINDIUM_ORE = new BlockBasic("findium_ore", Material.ROCK);
    public static final Block EXPLOSIVE_ORE = new BlockExplosiveOre("explosive_ore", Material.ROCK);
    public static final Block RANDOM_ORE = new BlockRandomOre("random_ore", Material.ROCK);

    // Blocs spéciaux
    public static final Block CAVE_BLOCK = new BlockCaveBlock("cave_block", Material.GLASS);
    public static final Block ELEVATOR = new BlockElevator("elevator", Material.IRON);


>>>>>>> origin/master












}