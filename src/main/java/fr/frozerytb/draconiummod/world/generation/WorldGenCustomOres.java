package fr.frozerytb.draconiummod.world.generation;

import fr.frozerytb.draconiummod.init.BlockInit;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator {
    private static Boolean isMinage;
    private final WorldGenerator azurite_ore, draconium_ore, explosive_ore, findium_ore, random_ore;

    public WorldGenCustomOres() {
        azurite_ore = new WorldGenMinable(BlockInit.AZURITE_ORE.getDefaultState(), 7, BlockMatcher.forBlock(Blocks.STONE));
        draconium_ore = new WorldGenMinable(BlockInit.DRACONIUM_ORE.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.STONE));
        explosive_ore = new WorldGenMinable(BlockInit.EXPLOSIVE_ORE.getDefaultState(), 3, BlockMatcher.forBlock(Blocks.STONE));
        findium_ore = new WorldGenMinable(BlockInit.FINDIUM_ORE.getDefaultState(), 2, BlockMatcher.forBlock(Blocks.STONE));
        random_ore = new WorldGenMinable(BlockInit.RANDOM_ORE.getDefaultState(), 2, BlockMatcher.forBlock(Blocks.STONE));

    }

    @SideOnly(Side.SERVER)
    private static boolean dedicatedServerIsMinage(World world) {
        DedicatedServer server = (DedicatedServer) world.getMinecraftServer();
        return server.getBooleanProperty("minage-server", false);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (isMinage == null) {
            isMinage = FMLCommonHandler.instance().getSide().isClient() || dedicatedServerIsMinage(world);
        }

        if (isMinage && world.provider.getDimension() == 0) {
            runGenerator(azurite_ore, world, random, chunkX, chunkZ, 15, 0, 20);
            runGenerator(draconium_ore, world, random, chunkX, chunkZ, 9, 0, 12);
            runGenerator(explosive_ore, world, random, chunkX, chunkZ, 7, 0, 9);
            runGenerator(findium_ore, world, random, chunkX, chunkZ, 5, 0, 7);
            runGenerator(random_ore, world, random, chunkX, chunkZ, 3, 0, 8);
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore genrated out of bounds");

        int heightDiff = maxHeight - minHeight + 1;

        for (int i = 0; i < chance; i++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
