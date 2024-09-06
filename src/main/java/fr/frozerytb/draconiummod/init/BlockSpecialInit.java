package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.blocks.BlockCaveBlock;
import fr.frozerytb.draconiummod.objects.blocks.BlockElevator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockSpecialInit {
    public static final List<Block> SPECIAL_BLOCKS = new ArrayList<>();

    // Blocs spéciaux
    public static final Block CAVE_BLOCK = new BlockCaveBlock("cave_block", Material.GLASS);
    public static final Block ELEVATOR = new BlockElevator("elevator", Material.IRON);



    public static void init() {
        SPECIAL_BLOCKS.add(CAVE_BLOCK);
        SPECIAL_BLOCKS.add(ELEVATOR);
    }
}
