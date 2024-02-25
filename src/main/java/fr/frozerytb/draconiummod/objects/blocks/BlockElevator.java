package fr.frozerytb.draconiummod.objects.blocks;


import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;

public class BlockElevator extends Block implements IHasmodel {
    private final HashSet<EntityPlayer> playersSneaking = new HashSet<>();

    public BlockElevator(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.DraconiummodTab);
        setHardness(8.0F);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        EntityPlayer player = e.player;
        World world = player.world;
        if (!world.isRemote && e.phase == TickEvent.Phase.END) {
            if (player.isSneaking()) {
                if (playersSneaking.add(player)) {
                    BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(MathHelper.floor(player.posX), MathHelper.floor(player.posY) - 1, MathHelper.floor(player.posZ));
                    if (world.getBlockState(pos).getBlock() == BlockInit.ELEVATOR) {
                        for (int y = (int) player.posY - 2; y > -1; y--) {
                            pos.setY(y);
                            if (world.getBlockState(pos).getBlock() == BlockInit.ELEVATOR) {
                                player.attemptTeleport(player.posX, y + 1, player.posZ);
                                break;
                            }
                        }
                    }
                }
            } else {
                playersSneaking.remove(player);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        playersSneaking.remove(event.player);
    }

    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent e) {
        EntityLivingBase entity = e.getEntityLiving();
        World world = entity.world;
        if (!world.isRemote && entity instanceof EntityPlayer) {
            BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(MathHelper.floor(entity.posX), MathHelper.floor(entity.posY) - 1, MathHelper.floor(entity.posZ));
            if (world.getBlockState(pos).getBlock() == BlockInit.ELEVATOR) {
                for (int y = (int) entity.posY + 1; y < 256; y++) {
                    pos.setY(y);
                    if (world.getBlockState(pos).getBlock() == BlockInit.ELEVATOR && entity.attemptTeleport(entity.posX, y + 1, entity.posZ)) {
                        entity.motionY = 0;
                        entity.velocityChanged = true;
                        break;
                    }
                }
            }
        }
    }
}