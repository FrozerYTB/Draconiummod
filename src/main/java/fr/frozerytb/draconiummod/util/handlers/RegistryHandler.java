package fr.frozerytb.draconiummod.util.handlers;

import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.FluidInit;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import fr.frozerytb.draconiummod.world.generation.WorldGenCustomOres;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }


    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            if (item instanceof IHasmodel) {
                ((IHasmodel) item).registerModels();
            }
        }


        for (Block block : BlockInit.BLOCKS) {
            if (block instanceof IHasmodel) {
                ((IHasmodel) block).registerModels();
            }
        }
    }

    public static void preInitRegistries()
    {
        FluidInit.registerFluids();
        RenderHandler.registerCustomMeshesAndStates();
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
    }

    public static void initRegistries() {
        RecipesHandler.registerRecipies();
        preInitRegistries();
    }
}
