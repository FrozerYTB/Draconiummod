package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.items.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemSpecialInit {
    public static final List<Item> SPECIAL_ITEMS = new ArrayList<Item>();

    // Items spéciaux
    public static final Item STICK_OF_GOD = new ItemStickOfGod("stick_of_god");
    public static final Item REGENERATION_STICK = new ItemRegenerationStick("regeneration_stick");
    public static final Item RADAR = new ItemRadar("radar");
    public static final Item GRENADE = new ItemGrenade("grenade");
    public static final Item DEBRIS_GRENADE = new ItemBase("debris_grenade");
    public static final Item SPONGE_ON_A_STICK = new ItemSpongeOnAStick("sponge_on_a_stick");

    static {
        SPECIAL_ITEMS.add(STICK_OF_GOD);
    }

    public static void init() {
    }
}
