package fr.frozerytb.draconiummod.objects.blocks;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.NonNullList;
import java.util.Random;

public class BlockRandomOre extends Block {

    public BlockRandomOre(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(5.0f);
        setCreativeTab(Main.DRACONIUMMOD_TAB);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        Random random = new Random();
        EntityPlayer player = world instanceof World ? ((World) world).getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 16, false) : null;

        if (player != null && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand()) > 0) {
            drops.add(new ItemStack(this));
        } else {
            drops.add(new ItemStack(getRandomOre(random)));
        }
    }

    private Item getRandomOre(Random random) {
        int chance = random.nextInt(101);

        if (chance < 52) { // 52% pour le charbon
            return Item.getByNameOrId("minecraft:coal_ore");
        } else if (chance < 72) { // 20% pour le lapis-lazuli (de 45 à 64)
            return Item.getByNameOrId("minecraft:lapis_ore");
        } else if (chance < 82) { // 10% pour le fer (de 65 à 74)
            return Item.getByNameOrId("minecraft:iron_ore");
        } else if (chance < 87) { // 5% pour l'or (de 75 à 79)
            return Item.getByNameOrId("minecraft:gold_ore");
        } else if (chance < 92) { // 5% pour le diamant (de 80 à 84)
            return Item.getByNameOrId("minecraft:diamond_ore");
        } else if (chance < 95) { // 3% pour l'azurite (de 85 à 87)
            return Item.getByNameOrId("draconiummod:azurite_ore");
        } else if (chance < 97) { // 2% pour l'émeraude (de 88 à 89)
            return Item.getByNameOrId("minecraft:emerald_ore");
        } else if (chance < 99) { // 2% pour le draconium (de 90 à 91)
            return Item.getByNameOrId("draconiummod:draconium_ore");
        } else { // 1% pour le findium (de 92 à 100)
            return Item.getByNameOrId("draconiummod:findium_ore");
        }
    }
}

