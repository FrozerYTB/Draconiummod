package fr.frozerytb.draconiummod.util.handlers;

import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import fr.frozerytb.draconiummod.world.generation.WorldGenCustomOres;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler
{

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ItemInit.ITEMS)
        {
            if(item instanceof IHasmodel)
            {
                ((IHasmodel)item).registerModels();
            }
        }



        for(Block block : BlockInit.BLOCKS)
        {
            if(block instanceof IHasmodel)
            {
                ((IHasmodel)block).registerModels();
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent e)
    {
        EntityPlayer player = e.player;
        if(e.player.world.getBlockState(new BlockPos(player.posX, player.posY - 1, player.posZ)).getBlock() == BlockInit.ELEVATOR)
        {
            if(player.isSneaking())
            {
                for(int i = (int)(player.posY - 2); i > 0; i--)
                {
                    if(e.player.world.getBlockState(new BlockPos(player.posX, i, player.posZ)).getBlock() == BlockInit.ELEVATOR)
                    {
                        BlockPos pos = new BlockPos(player.posX, i, player.posZ);

                        player.setPosition(player.posX, pos.getY() + 1, player.posZ);

                    }
                }
            }
        }
    }

    public static double getElevatorJump(EntityPlayer player)
    {
        int u = 0;

        for(int i = (int)player.posY + 1; i < 256; i++)
        {

            BlockPos pos = new BlockPos(player.posX, i, player.posZ);

            if(player.world.getBlockState(pos).getBlock() == BlockInit.ELEVATOR)
            {
                u = i;
                return i;
            }
        }
        return u;
    }

    @SubscribeEvent
    public static void playerJumpingEvent(LivingEvent.LivingJumpEvent e)
    {
        if(e.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)e.getEntityLiving();

            if(player.world.getBlockState(new BlockPos(player.posX, player.posY - 1, player.posZ)).getBlock() == BlockInit.ELEVATOR)
            {
                player.setPosition(player.posX, getElevatorJump(player) + 1, player.posZ);
            }
        }
    }
    
    public static void preInitRegistries()
    {
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
    }
    public static void initRegistries()
    {
        RecipesHandler.registerRecipies();
    }
}
