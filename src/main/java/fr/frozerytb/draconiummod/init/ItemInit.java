package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.items.*;
import fr.frozerytb.draconiummod.objects.items.tools.*;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;


public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //materiaux
    public static final ArmorMaterial ARMOR_MATERIAL_AZURITE = EnumHelper.addArmorMaterial("armor_material_azurite", Reference.MODID + ":azurite", 175, new int[]{4, 7, 9, 4}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);
    public static final ArmorMaterial ARMOR_MATERIAL_DRACONIUM = EnumHelper.addArmorMaterial("armor_material_draconium", Reference.MODID + ":draconium", 220, new int[]{5, 8, 10, 5}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static final ArmorMaterial ARMOR_MATERIAL_AQUATIQUE = EnumHelper.addArmorMaterial("armor_material_aquatique", Reference.MODID + ":aquatique", 100, new int[]{2, 5, 7, 2}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
    public static final ArmorMaterial ARMOR_MATERIAL_DRACONIQUE  = EnumHelper.addArmorMaterial("armor_material_draconique", Reference.MODID + ":draconique", 340, new int[]{7, 10, 12, 7}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F);
    public static final Item.ToolMaterial TOOLS_MATERIAL_AZURITE = EnumHelper.addToolMaterial("tools_material_azurite", 4, 2890, 9.0F, 4.0F, 17);
    public static final Item.ToolMaterial HAMMER_MATERIAL_AZURITE = EnumHelper.addToolMaterial("hammer_material_azurite", 4, 2890, 1.0F, 2.0F, 17);
    public static final Item.ToolMaterial TOOLS_MATERIAL_DRACONIUM = EnumHelper.addToolMaterial("tools_material_draconium", 5, 3890, 10.0F, 6.0F, 25);
    public static final Item.ToolMaterial HAMMER_MATERIAL_DRACONIUM = EnumHelper.addToolMaterial("hammer_material_azurite", 5, 3890, 3.0F, 3.0F, 25);

    //LINGOTS
    public static final Item AZURITE_INGOT = new ItemBase("azurite_ingot");
    public static final Item DRACONIUM_INGOT = new ItemBase("draconium_ingot");
    public static final Item FINDIUM_CRISTAL = new ItemBase("findium_cristal");

    //OUTILS
    public static final Item AZURITE_AXE = new ItemToolAxe("azurite_axe", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_PICKAXE = new ItemToolPickaxe("azurite_pickaxe", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_SHOVEL = new ItemToolSpade("azurite_shovel", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_SWORD = new ItemToolSword("azurite_sword", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_HAMMER = new ItemToolHammer("azurite_hammer", HAMMER_MATERIAL_AZURITE);

    public static final Item DRACONIUM_AXE = new ItemToolAxe("draconium_axe", TOOLS_MATERIAL_DRACONIUM);
    public static final Item DRACONIUM_PICKAXE = new ItemToolPickaxe("draconium_pickaxe", TOOLS_MATERIAL_DRACONIUM);
    public static final Item DRACONIUM_SHOVEL = new ItemToolSpade("draconium_shovel", TOOLS_MATERIAL_DRACONIUM);
    public static final Item DRACONIUM_SWORD = new ItemToolSword("draconium_sword", TOOLS_MATERIAL_DRACONIUM);
    public static final Item DRACONIUM_HAMMER = new ItemToolHammer("draconium_hammer", HAMMER_MATERIAL_DRACONIUM);

    //ARMURES
    public static final Item AZURITE_HELMET = new ArmorBase("azurite_helmet", ARMOR_MATERIAL_AZURITE, 1, EntityEquipmentSlot.HEAD);
    public static final Item AZURITE_CHESTPLATE = new ArmorBase("azurite_chestplate", ARMOR_MATERIAL_AZURITE, 1, EntityEquipmentSlot.CHEST);
    public static final Item AZURITE_LEGGINGS = new ArmorBase("azurite_leggings", ARMOR_MATERIAL_AZURITE, 2, EntityEquipmentSlot.LEGS);
    public static final Item AZURITE_BOOTS = new ArmorBase("azurite_boots", ARMOR_MATERIAL_AZURITE, 1, EntityEquipmentSlot.FEET);

    public static final Item DRACONIUM_HELMET = new ArmorBase("draconium_helmet", ARMOR_MATERIAL_DRACONIUM, 1, EntityEquipmentSlot.HEAD);
    public static final Item DRACONIUM_CHESTPLATE = new ArmorBase("draconium_chestplate", ARMOR_MATERIAL_DRACONIUM, 1, EntityEquipmentSlot.CHEST);
    public static final Item DRACONIUM_LEGGINGS = new ArmorBase("draconium_leggings", ARMOR_MATERIAL_DRACONIUM, 2, EntityEquipmentSlot.LEGS);
    public static final Item DRACONIUM_BOOTS = new ArmorBase("draconium_boots", ARMOR_MATERIAL_DRACONIUM, 1, EntityEquipmentSlot.FEET);

    public static final Item DRACONIQUE_HELMET = new ArmorBase("draconique_helmet", ARMOR_MATERIAL_DRACONIQUE, 1, EntityEquipmentSlot.HEAD);
    public static final Item DRACONIQUE_CHESTPLATE = new ArmorBase("draconique_chestplate", ARMOR_MATERIAL_DRACONIQUE, 1, EntityEquipmentSlot.CHEST);
    public static final Item DRACONIQUE_LEGGINGS = new ArmorBase("draconique_leggings", ARMOR_MATERIAL_DRACONIQUE, 2, EntityEquipmentSlot.LEGS);
    public static final Item DRACONIQUE_BOOTS = new ArmorBase("draconique_boots", ARMOR_MATERIAL_DRACONIQUE, 1, EntityEquipmentSlot.FEET);

    public static final Item AQUATIQUE_HELMET = new ArmorBase("aquatique_helmet", ARMOR_MATERIAL_AQUATIQUE, 1, EntityEquipmentSlot.HEAD);

    //ITEMS SPECIAUX
    public static final Item STICK_OF_GOD = new ItemStickOfGod("stick_of_god");
    public static final Item REGENERATION_STICK = new ItemRegenerationStick("regeneration_stick");
    public static final Item RADAR = new ItemRadar("radar");
    public static final Item GRENADE = new ItemGrenade("grenade");
    public static final Item DEBRIS_GRENADE = new ItemBase("debris_grenade");

    //NOURRITURRES
    public static final Item DRACONIUM_APPLE = new ItemCustomFood("draconium_apple", 8, false);
}