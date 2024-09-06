package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.tabs.DraconiummodTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static void init() {
        // Initialisation des catégories d'items
        ItemFoodInit.init();
        ItemArmorInit.init();
        ItemToolInit.init();
        ItemSpecialInit.init();

        // Ajout des items à la liste globale
        ITEMS.addAll(ItemFoodInit.FOODS);
        ITEMS.addAll(ItemArmorInit.ARMORS);
        ITEMS.addAll(ItemToolInit.TOOLS);
        ITEMS.addAll(ItemSpecialInit.SPECIAL_ITEMS);

        // Enregistrement des items
        for (Item item : ITEMS) {
            item.setRegistryName(item.getUnlocalizedName().substring(5)); // Enregistre le nom de l'item (sans "item.")
            item.setCreativeTab(Main.DraconiummodTab); // Exemple d'ajout à un onglet créatif
        }
    }
}
