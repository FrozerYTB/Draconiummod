package fr.frozerytb.draconiummod.util.handlers;

import fr.frozerytb.draconiummod.init.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesHandler {
    public static void registerRecipes() {
        //Smelt ORE
        GameRegistry.addSmelting(BlockOreInit.AZURITE_ORE, new ItemStack(ItemOreInit.AZURITE_INGOT, 1), 5.0F);
        GameRegistry.addSmelting(BlockOreInit.DRACONIUM_ORE, new ItemStack(ItemOreInit.DRACONIUM_INGOT, 1), 5.0F);

        //UNCRAFT ARMOR

        //AZURITE
        GameRegistry.addSmelting(ItemArmorInit.AZURITE_HELMET, new ItemStack(ItemOreInit.AZURITE_INGOT, 3), 5.0F);
        GameRegistry.addSmelting(ItemArmorInit.AZURITE_CHESTPLATE, new ItemStack(ItemOreInit.AZURITE_INGOT, 6), 5.0F);
        GameRegistry.addSmelting(ItemArmorInit.AZURITE_LEGGINGS, new ItemStack(ItemOreInit.AZURITE_INGOT, 5), 5.0F);
        GameRegistry.addSmelting(ItemArmorInit.AZURITE_BOOTS, new ItemStack(ItemOreInit.AZURITE_INGOT, 2), 5.0F);

        //DRACONIUM
        GameRegistry.addSmelting(ItemArmorInit.DRACONIUM_HELMET, new ItemStack(ItemOreInit.DRACONIUM_INGOT, 3), 5.0F);
        GameRegistry.addSmelting(ItemArmorInit.DRACONIUM_CHESTPLATE, new ItemStack(ItemOreInit.DRACONIUM_INGOT, 6), 5.0F);
        GameRegistry.addSmelting(ItemArmorInit.DRACONIUM_LEGGINGS, new ItemStack(ItemOreInit.DRACONIUM_INGOT, 5), 5.0F);
        GameRegistry.addSmelting(ItemArmorInit.DRACONIUM_BOOTS, new ItemStack(ItemOreInit.DRACONIUM_INGOT, 2), 5.0F);


        //UNCRAFT TOOLS

        //AZURITE
        GameRegistry.addSmelting(ItemToolInit.AZURITE_AXE, new ItemStack(ItemOreInit.AZURITE_INGOT, 2), 5.0F);
        GameRegistry.addSmelting(ItemToolInit.AZURITE_PICKAXE, new ItemStack(ItemOreInit.AZURITE_INGOT, 2), 5.0F);
        GameRegistry.addSmelting(ItemToolInit.AZURITE_SHOVEL, new ItemStack(ItemOreInit.AZURITE_INGOT, 1), 5.0F);
        GameRegistry.addSmelting(ItemToolInit.AZURITE_SWORD, new ItemStack(ItemOreInit.AZURITE_INGOT, 2), 5.0F);

        //DRACONIUM
        GameRegistry.addSmelting(ItemToolInit.DRACONIUM_AXE, new ItemStack(ItemOreInit.DRACONIUM_INGOT, 2), 5.0F);
        GameRegistry.addSmelting(ItemToolInit.DRACONIUM_PICKAXE, new ItemStack(ItemOreInit.DRACONIUM_INGOT, 2), 5.0F);
        GameRegistry.addSmelting(ItemToolInit.DRACONIUM_SHOVEL, new ItemStack(ItemOreInit.DRACONIUM_INGOT, 1), 5.0F);
        GameRegistry.addSmelting(ItemToolInit.DRACONIUM_SWORD, new ItemStack(ItemOreInit.DRACONIUM_INGOT, 2), 5.0F);
    }
}
