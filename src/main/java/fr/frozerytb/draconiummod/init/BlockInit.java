package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.blocks.fluids.BlockFakeWaterFluid;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {

    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block FAKE_WATER_BLOCK = new BlockFakeWaterFluid("fake_water", FluidInit.FAKE_WATER_FLUID, Material.WATER);

    public static void init() {
        System.out.println("Initializing blocks...");
        BlockOreInit.init();
        BlockBasicInit.init();
        BlockSpecialInit.init();
        FluidInit.initFluids();

        BLOCKS.add(FAKE_WATER_BLOCK);
        BLOCKS.addAll(BlockOreInit.ORES);
        BLOCKS.addAll(BlockBasicInit.BLOCKS);
        BLOCKS.addAll(BlockSpecialInit.SPECIAL_BLOCKS);
    }
}
