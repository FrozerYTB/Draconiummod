package fr.frozerytb.draconiummod.optimisations;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

public class OptimizedWaterBlock extends BlockFluidClassic {

    // Constructor pour initialiser le bloc d'eau
    public OptimizedWaterBlock(Fluid fluid, Material material) {
        super(fluid, material);
    }

    // Override de la méthode de mise à jour pour optimiser la propagation
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        // Limitation de la fréquence de propagation - 25% de chance de se propager chaque tick
        if (rand.nextInt(4) != 0) {
            return; // Arrête la propagation pour ce tick
        }
    }

    // Méthode personnalisée pour gérer la propagation d'eau (standard et FakeWater)
    public void propagateWater(World world, BlockPos pos) {
        // Ajoutez ici les conditions spécifiques pour FakeWater si nécessaire

        // Si la propagation est valide, mise à jour du bloc d'eau
        if (canFlowInto(world, pos)) {
            world.setBlockState(pos, getDefaultState(), 2);
        }
    }

    // Vérification si l'eau peut se propager dans le bloc donné
    public boolean canFlowInto(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getMaterial().isReplaceable();
    }

}


