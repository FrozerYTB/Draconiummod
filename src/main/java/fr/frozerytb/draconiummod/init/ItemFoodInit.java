package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.items.ItemCustomFood;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemFoodInit {
    public static final List<Item> FOODS = new ArrayList<>();

    // Création et ajout d'un item de nourriture
    public static final Item DRACONIUM_APPLE = new ItemCustomFood("draconium_apple", 8, false);



    public static void init() {
        FOODS.add(DRACONIUM_APPLE);
    }
}
