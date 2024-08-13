package fr.frozerytb.draconiummod.optimisations;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class OptimizedRedstoneWire extends BlockRedstoneWire {

    // Constructor pour initialiser le bloc Redstone optimisé
    public OptimizedRedstoneWire() {
        super();
    }

    // Override de la méthode de mise à jour pour optimiser les calculs de la redstone
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        // Réduction de la fréquence des mises à jour de la redstone
        if (rand.nextInt(3) != 0) {
            return; // 66% de chance de ne pas effectuer de mise à jour ce tick
        }

        // Appel de la méthode de mise à jour standard
        super.updateTick(world, pos, state, rand);
    }

    // Optimisation des calculs de redstone pour réduire la charge serveur
    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        // Ajout d'une condition pour vérifier si le bloc voisin est pertinent pour la redstone
        if (blockIn == this || blockIn instanceof BlockRedstoneWire) {
            super.neighborChanged(state, world, pos, blockIn, fromPos);
        }
    }

    // Méthode pour réduire les mises à jour répétées
    private void optimizeRedstoneUpdates(World world, BlockPos pos) {
        // Éviter les calculs redondants en vérifiant si une mise à jour est réellement nécessaire
        if (!world.isRemote && world.isBlockPowered(pos)) {
            world.scheduleUpdate(pos, this, this.tickRate(world));
        }
    }
}
