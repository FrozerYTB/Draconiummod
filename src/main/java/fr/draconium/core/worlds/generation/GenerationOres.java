package fr.draconium.core.worlds.generation;

import java.util.Random;

import fr.draconium.core.init.blocks.ores.BlocksOresInit;
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

public class GenerationOres implements IWorldGenerator
{
	private static Boolean isMinage;
	private final WorldGenerator azurite_ore, draconium_ore, explosive_ore, findium_ore;
	
	public GenerationOres()
	{
		this.azurite_ore 	= new WorldGenMinable(BlocksOresInit.AZURITE_ORE.getDefaultState(), 	7, BlockMatcher.forBlock(Blocks.STONE));
		this.draconium_ore 	= new WorldGenMinable(BlocksOresInit.DRACONIUM_ORE.getDefaultState(), 	5, BlockMatcher.forBlock(Blocks.STONE));
		this.explosive_ore 	= new WorldGenMinable(BlocksOresInit.EXPLOSIVE_ORE.getDefaultState(), 	4, BlockMatcher.forBlock(Blocks.STONE));
		this.findium_ore 	= new WorldGenMinable(BlocksOresInit.FINDIUM_ORE.getDefaultState(), 	2, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@SideOnly(Side.SERVER)
	private static boolean dedicatedServerIsMinage(World world)
	{
		return ((DedicatedServer) world.getMinecraftServer()).getBooleanProperty("minage-server", false);
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if (this.isMinage == null)
		{
			// Monde solo considéré en minage
			this.isMinage = FMLCommonHandler.instance().getSide().isClient() || this.dedicatedServerIsMinage(world);
		}

		if (this.isMinage && world.provider.getDimension() == 0)
		{
			this.runGenerator(this.azurite_ore, 	world, random, chunkX, chunkZ, 15, 0, 20);
			this.runGenerator(this.draconium_ore, 	world, random, chunkX, chunkZ, 9, 0, 12);
			this.runGenerator(this.explosive_ore, 	world, random, chunkX, chunkZ, 7, 0, 9);
			this.runGenerator(this.findium_ore, 	world, random, chunkX, chunkZ, 5, 0, 7);
		}
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)
	{
		if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore genrated out of bounds");

		int heightDiff = maxHeight - minHeight + 1;

		for (int i = 0; i < chance; i++)
		{
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight 	+ rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);

			gen.generate(world, rand, new BlockPos(x, y, z));
		}
	}
}
