package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.objects.items.*;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ItemSpecialInit {
    public static final List<Item> SPECIAL_ITEMS = new ArrayList<>();

    // Items spéciaux
    public static final Item STICK_OF_GOD = new ItemStickOfGod("stick_of_god");
    public static final Item REGENERATION_STICK = new ItemRegenerationStick("regeneration_stick");
    public static final Item RADAR = new ItemRadar("radar");
    public static final Item GRENADE = new ItemGrenade("grenade");
    public static final Item DEBRIS_GRENADE = new ItemBase("debris_grenade");
    public static final Item SPONGE_ON_A_STICK = new ItemSpongeOnAStick("sponge_on_a_stick");
    public static final Item BUCKET_FAKE_WATER = new ItemBucketFakeWater(BlockInit.FAKE_WATER_BLOCK).setCreativeTab(Main.DraconiummodTab);

    public static void init() {
        SPECIAL_ITEMS.add(STICK_OF_GOD);
        SPECIAL_ITEMS.add(REGENERATION_STICK);
        SPECIAL_ITEMS.add(RADAR);
        SPECIAL_ITEMS.add(GRENADE);
        SPECIAL_ITEMS.add(DEBRIS_GRENADE);
        SPECIAL_ITEMS.add(SPONGE_ON_A_STICK);
        SPECIAL_ITEMS.add(BUCKET_FAKE_WATER);
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        for (Item item : SPECIAL_ITEMS) {
            item.setRegistryName(item.getUnlocalizedName().substring(5));
            registry.register(item);
            System.out.println("Registered special item: " + item.getRegistryName());
        }
    }
}
