package fr.frozerytb.draconiummod.world.generation;

import fr.frozerytb.draconiummod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.dispenser.IPosition;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator {

    private WorldGenerator azurite_ore, draconium_ore, findium_ore;

    public WorldGenCustomOres()
    {
        azurite_ore = new WorldGenMinable(BlockInit.AZURITE_ORE.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE));
        draconium_ore = new WorldGenMinable(BlockInit.DRACONIUM_ORE.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.STONE));
        findium_ore = new WorldGenMinable(BlockInit.FINDIUM_ORE.getDefaultState(), 2, BlockMatcher.forBlock(Blocks.STONE));
    }



    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimension())
        {
            case -1:
                break;
            case 0:
                runGenerator(azurite_ore, world, random, chunkX, chunkZ, 35, 0, 30);
                runGenerator(draconium_ore, world, random, chunkX, chunkZ, 10, 0, 12);
                runGenerator(findium_ore, world, random, chunkX, chunkZ, 5, 0, 8);
                break;
            case 1:
                break;
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)
    {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore genrated out of bounds");

        int heightDiff = maxHeight - minHeight +1;

        for(int i = 0; i < chance; i++)
        {
            int x = chunkX * 16 +rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}