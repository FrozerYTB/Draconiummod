package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.objects.blocks.fluids.BlockFakeWaterFluid;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {

    public static final List<Block> BLOCKS = new ArrayList<>();

    // Déclaration des blocs
    public static final Block FAKE_WATER_BLOCK = new BlockFakeWaterFluid("fake_water", FluidInit.FAKE_WATER_FLUID, Material.WATER);

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

    private static void registerBlocks() {
        IForgeRegistry<Block> registry = GameRegistry.findRegistry(Block.class);
        for (Block block : BLOCKS) {
            block.setRegistryName(block.getUnlocalizedName().substring(5));
            block.setCreativeTab(Main.DRACONIUMMOD_TAB);
            registry.register(block);
            System.out.println("Registered block: " + block.getRegistryName());
        }
    }
}
