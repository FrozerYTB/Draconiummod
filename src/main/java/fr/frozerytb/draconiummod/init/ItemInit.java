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

        ItemFoodInit.init();
        ItemArmorInit.init();
        ItemToolInit.init();
        ItemSpecialInit.init();


        ITEMS.addAll(ItemFoodInit.FOODS);
        ITEMS.addAll(ItemArmorInit.ARMORS);
        ITEMS.addAll(ItemToolInit.TOOLS);
        ITEMS.addAll(ItemSpecialInit.SPECIAL_ITEMS);


        for (Item item : ITEMS) {
            item.setRegistryName(item.getUnlocalizedName().substring(5));
            item.setCreativeTab(Main.DRACONIUMMOD_TAB);
        }
    }
}
