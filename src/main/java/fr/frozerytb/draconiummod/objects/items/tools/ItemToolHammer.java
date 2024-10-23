package fr.frozerytb.draconiummod.objects.items.tools;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemToolHammer extends ItemPickaxe implements IHasmodel {
    public ItemToolHammer(String name, ToolMaterial material) {
        super(material);
        setUnlocalizedName(name);
        setMaxStackSize(1);
        setRegistryName(name);
        setCreativeTab(Main.DRACONIUMMOD_TAB);
        ItemInit.ITEMS.add(this);
    }

    protected ItemToolHammer(ToolMaterial material) {
        super(material);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment) {
        if (enchantment == net.minecraft.init.Enchantments.MENDING) {
            return false;
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    public RayTraceResult rayTrace(double blockReachDistance, float partialTicks, World world, EntityLivingBase e) {
        Vec3d vec3d = e.getPositionEyes(partialTicks);
        Vec3d vec3d1 = e.getLook(partialTicks);
        Vec3d vec3d2 = vec3d.addVector(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
        return world.rayTraceBlocks(vec3d, vec3d2, false, false, true);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack breaker, World w, IBlockState state, BlockPos pos, EntityLivingBase e) {
        if (e instanceof EntityPlayer && !w.isRemote) {
            // Vérifie si le bloc est un minerai, si oui, retourne false sans miner
            if (state.getBlock() == net.minecraft.init.Blocks.COAL_ORE ||
                    state.getBlock() == net.minecraft.init.Blocks.IRON_ORE ||
                    state.getBlock() == net.minecraft.init.Blocks.GOLD_ORE ||
                    state.getBlock() == net.minecraft.init.Blocks.DIAMOND_ORE ||
                    state.getBlock() == net.minecraft.init.Blocks.REDSTONE_ORE ||
                    state.getBlock() == net.minecraft.init.Blocks.LAPIS_ORE ||
                    state.getBlock() == net.minecraft.init.Blocks.EMERALD_ORE ||
                    state.getBlock() == net.minecraft.init.Blocks.QUARTZ_ORE ||
                    state.getBlock() == BlockInit.AZURITE_ORE ||
                    state.getBlock() == BlockInit.DRACONIUM_ORE ||
                    state.getBlock() == BlockInit.FINDIUM_ORE) {
                return false; // Ne pas miner les minerais
            }

            EntityPlayer p = (EntityPlayer) e;
            RayTraceResult r = this.rayTrace(5.0D, 0.0f, w, e);

            if (r.typeOfHit == RayTraceResult.Type.BLOCK) {
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();

                EnumFacing side = r.sideHit;

                if (side == EnumFacing.DOWN || side == EnumFacing.UP) {
                    this.destroyAndDropBlock(w, p, breaker, x + 1, y, z - 1);
                    this.destroyAndDropBlock(w, p, breaker, x + 1, y, z);
                    this.destroyAndDropBlock(w, p, breaker, x + 1, y, z + 1);
                    this.destroyAndDropBlock(w, p, breaker, x, y, z - 1);
                    // Middle block
                    this.destroyAndDropBlock(w, p, breaker, x, y, z + 1);
                    this.destroyAndDropBlock(w, p, breaker, x - 1, y, z - 1);
                    this.destroyAndDropBlock(w, p, breaker, x - 1, y, z);
                    this.destroyAndDropBlock(w, p, breaker, x - 1, y, z + 1);
                }
                // Z
                // NORTH - SOUTH
                else if (side == EnumFacing.NORTH || side == EnumFacing.SOUTH) {
                    this.destroyAndDropBlock(w, p, breaker, x + 1, y + 1, z);
                    this.destroyAndDropBlock(w, p, breaker, x, y + 1, z);
                    this.destroyAndDropBlock(w, p, breaker, x - 1, y + 1, z);
                    this.destroyAndDropBlock(w, p, breaker, x + 1, y, z);
                    // Middle block
                    this.destroyAndDropBlock(w, p, breaker, x - 1, y, z);
                    this.destroyAndDropBlock(w, p, breaker, x + 1, y - 1, z);
                    this.destroyAndDropBlock(w, p, breaker, x, y - 1, z);
                    this.destroyAndDropBlock(w, p, breaker, x - 1, y - 1, z);
                }
                // X
                // EAST - WEST
                else if (side == EnumFacing.EAST || side == EnumFacing.WEST) {
                    this.destroyAndDropBlock(w, p, breaker, x, y + 1, z + 1);
                    this.destroyAndDropBlock(w, p, breaker, x, y + 1, z);
                    this.destroyAndDropBlock(w, p, breaker, x, y + 1, z - 1);
                    this.destroyAndDropBlock(w, p, breaker, x, y, z + 1);
                    // Middle block
                    this.destroyAndDropBlock(w, p, breaker, x, y, z - 1);
                    this.destroyAndDropBlock(w, p, breaker, x, y - 1, z + 1);
                    this.destroyAndDropBlock(w, p, breaker, x, y - 1, z);
                    this.destroyAndDropBlock(w, p, breaker, x, y - 1, z - 1);
                }
                return true;
            }
        }

        breaker.damageItem(1, e);
        return super.onBlockDestroyed(breaker, w, state, pos, e);
    }

    private void destroyAndDropBlock(World w, EntityPlayer p, ItemStack breaker, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);

        if (w.getBlockState(pos).getBlockHardness(w, pos) >= 0 && w.getBlockState(pos).getBlock().getMaterial(w.getBlockState(pos).getBlock().getDefaultState()) == Material.ROCK) {
            w.getBlockState(pos).getBlock().harvestBlock(w, p, pos, w.getBlockState(pos), w.getTileEntity(pos), breaker);
            w.setBlockToAir(pos);
        }
    }
}
