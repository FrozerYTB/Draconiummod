package fr.frozerytb.draconiummod.objects.blocks;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
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

public class BlockRandomOre extends Block implements IHasmodel {

    public BlockRandomOre(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(8.0f);
        setCreativeTab(Main.DRACONIUMMOD_TAB);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }

    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        Random random = new Random();
        EntityPlayer player = world instanceof World ? ((World) world).getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 16, false) : null;

        if (player != null && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand()) > 0) {
            drops.add(new ItemStack(this));
        } else {
            drops.add(new ItemStack(getRandomOre(random, fortune)));
        }
    }

    private Item getRandomOre(Random random, int fortune) {

        int chance = random.nextInt(101) + (fortune * 3);

        if (chance < 48) {
            return Item.getByNameOrId("minecraft:coal_ore");
        } else if (chance < 68) {
            return Item.getByNameOrId("minecraft:lapis_ore");
        } else if (chance < 83) {
            return Item.getByNameOrId("minecraft:iron_ore");
        } else if (chance < 88) {
            return Item.getByNameOrId("minecraft:gold_ore");
        } else if (chance < 91) {
            return Item.getByNameOrId("minecraft:diamond_ore");
        } else if (chance < 94) {
            return Item.getByNameOrId("draconiummod:azurite_ore");
        } else if (chance < 96) {
            return Item.getByNameOrId("minecraft:emerald_ore");
        } else if (chance < 98) {
            return Item.getByNameOrId("draconiummod:draconium_ore");
        } else if (chance < 100) {
            return Item.getByNameOrId("draconiummod:explosive_ore");
        } else {
            return Item.getByNameOrId("draconiummod:findium_ore");
        }
    }
}

