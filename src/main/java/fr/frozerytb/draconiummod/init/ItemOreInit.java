package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.items.ItemBase;
import fr.frozerytb.draconiummod.objects.items.ItemCustomFood;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemOreInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item AZURITE_INGOT = new ItemBase("azurite_ingot");
    public static final Item DRACONIUM_INGOT = new ItemBase("draconium_ingot");
    public static final Item FINDIUM_CRISTAL = new ItemBase("findium_cristal");



    public static void init() {
        ITEMS.add(AZURITE_INGOT);
        ITEMS.add(DRACONIUM_INGOT);
        ITEMS.add(FINDIUM_CRISTAL);
    }
}
