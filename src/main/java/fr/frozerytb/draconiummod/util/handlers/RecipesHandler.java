package fr.frozerytb.draconiummod.util.handlers;

import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesHandler
{
    public static void registerRecipies()
    {
        //Smelt ORE
        GameRegistry.addSmelting(BlockInit.AZURITE_ORE, new ItemStack(ItemInit.AZURITE_INGOT, 1), 5.0F);
        GameRegistry.addSmelting(BlockInit.DRACONIUM_ORE, new ItemStack(ItemInit.DRACONIUM_INGOT, 1), 5.0F);

        //UNCRAFT ARMOR

        //AZURITE
        GameRegistry.addSmelting(ItemInit.AZURITE_HELMET, new ItemStack(ItemInit.AZURITE_INGOT, 5), 5.0F);
        GameRegistry.addSmelting(ItemInit.AZURITE_CHESTPLATE, new ItemStack(ItemInit.AZURITE_INGOT, 8), 5.0F);
        GameRegistry.addSmelting(ItemInit.AZURITE_LEGGINGS, new ItemStack(ItemInit.AZURITE_INGOT, 7), 5.0F);
        GameRegistry.addSmelting(ItemInit.AZURITE_BOOTS, new ItemStack(ItemInit.AZURITE_INGOT, 4), 5.0F);

        //DRACONIUM
        GameRegistry.addSmelting(ItemInit.DRACONIUM_HELMET, new ItemStack(ItemInit.DRACONIUM_INGOT, 3), 5.0F);
        GameRegistry.addSmelting(ItemInit.DRACONIUM_CHESTPLATE, new ItemStack(ItemInit.DRACONIUM_INGOT, 3), 5.0F);
        GameRegistry.addSmelting(ItemInit.DRACONIUM_SHOVEL, new ItemStack(ItemInit.DRACONIUM_INGOT, 1), 5.0F);
        GameRegistry.addSmelting(ItemInit.DRACONIUM_SWORD, new ItemStack(ItemInit.DRACONIUM_INGOT, 2), 5.0F);


        //UNCRAFT TOOLS

        //AZURITE
        GameRegistry.addSmelting(ItemInit.AZURITE_AXE, new ItemStack(ItemInit.AZURITE_INGOT, 3), 5.0F);
        GameRegistry.addSmelting(ItemInit.AZURITE_PICKAXE, new ItemStack(ItemInit.AZURITE_INGOT, 3), 5.0F);
        GameRegistry.addSmelting(ItemInit.AZURITE_SHOVEL, new ItemStack(ItemInit.AZURITE_INGOT, 1), 5.0F);
        GameRegistry.addSmelting(ItemInit.AZURITE_SWORD, new ItemStack(ItemInit.AZURITE_INGOT, 2), 5.0F);

        //DRACONIUM
        GameRegistry.addSmelting(ItemInit.DRACONIUM_AXE, new ItemStack(ItemInit.DRACONIUM_INGOT, 3), 5.0F);
        GameRegistry.addSmelting(ItemInit.DRACONIUM_PICKAXE, new ItemStack(ItemInit.DRACONIUM_INGOT, 3), 5.0F);
        GameRegistry.addSmelting(ItemInit.DRACONIUM_SHOVEL, new ItemStack(ItemInit.DRACONIUM_INGOT, 1), 5.0F);
        GameRegistry.addSmelting(ItemInit.DRACONIUM_SWORD, new ItemStack(ItemInit.DRACONIUM_INGOT, 2), 5.0F);
    }

}
