package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ItemSpongeOnAStick extends Item {

    public static final int SPONGE_RANGE = 5;
    public static final int SPONGE_MAX_DAMAGE = 64;
    public static final double WATER_FLOW_PROBABILITY = 0.6;

    public ItemSpongeOnAStick(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setMaxDamage(SPONGE_MAX_DAMAGE);
        setCreativeTab(Main.DraconiummodTab);

        // Enregistrement de l'item
        ItemInit.ITEMS.add(this);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        boolean result = absorbWater(world, pos, player, stack);
        return result ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        boolean result = absorbWater(world, player.getPosition(), player, stack);
        return new ActionResult<>(result ? EnumActionResult.SUCCESS : EnumActionResult.FAIL, stack);
    }

    private static boolean absorbWater(World world, BlockPos pos, EntityPlayer player, ItemStack stack) {
        boolean absorbedAnything = false;
        Random random = new Random();
        Set<BlockPos> updatedBlocks = new HashSet<>();

        // Rayon d'absorption simplifié pour les performances
        int rangeSquared = SPONGE_RANGE * SPONGE_RANGE;

        // Boucle sur les coordonnées dans le rayon spécifié
        for (int x = -SPONGE_RANGE; x <= SPONGE_RANGE; x++) {
            for (int y = -SPONGE_RANGE; y <= SPONGE_RANGE; y++) {
                for (int z = -SPONGE_RANGE; z <= SPONGE_RANGE; z++) {
                    if (x * x + y * y + z * z <= rangeSquared) { // Vérifie si le point est dans le rayon
                        BlockPos targetPos = pos.add(x, y, z);
                        Block block = world.getBlockState(targetPos).getBlock();

                        // Vérifiez si le bloc est de l'eau
                        if (block == Blocks.WATER || block == Blocks.FLOWING_WATER) {
                            absorbedAnything = true;

                            // Remplacer l'eau par de l'air
                            world.setBlockState(targetPos, Blocks.AIR.getDefaultState(), 2);
                            updatedBlocks.add(targetPos);

                            // Gérer l'écoulement de l'eau environnante avec une probabilité
                            if (random.nextDouble() < WATER_FLOW_PROBABILITY) {
                                for (EnumFacing facing : EnumFacing.values()) {
                                    BlockPos adjacentPos = targetPos.offset(facing);
                                    Block adjacentBlock = world.getBlockState(adjacentPos).getBlock();
                                    if (adjacentBlock == Blocks.WATER || adjacentBlock == Blocks.FLOWING_WATER) {
                                        updatedBlocks.add(adjacentPos);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Assurer la mise à jour de l'eau au-dessus du joueur
        BlockPos abovePos = pos.up();
        if (world.getBlockState(abovePos).getBlock() == Blocks.WATER || world.getBlockState(abovePos).getBlock() == Blocks.FLOWING_WATER) {
            world.setBlockState(abovePos, Blocks.AIR.getDefaultState(), 2);
            updatedBlocks.add(abovePos);

            // Notifier le monde que l'eau au-dessus a changé
            for (EnumFacing facing : EnumFacing.values()) {
                BlockPos adjacentPos = abovePos.offset(facing);
                world.notifyBlockUpdate(adjacentPos, world.getBlockState(adjacentPos), world.getBlockState(adjacentPos), 3);
            }
        }

        // Mettre à jour tous les blocs modifiés
        for (BlockPos blockPos : updatedBlocks) {
            world.notifyBlockUpdate(blockPos, world.getBlockState(blockPos), Blocks.AIR.getDefaultState(), 3);
        }

        // Réduire la durabilité de l'item si de l'eau a été absorbée
        if (absorbedAnything) {
            stack.damageItem(1, player);
            return true;
        }

        return false;
    }
}
