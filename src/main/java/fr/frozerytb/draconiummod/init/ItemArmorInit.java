package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.init.ArmorBase;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemArmorInit {
    public static final List<Item> ARMORS = new ArrayList<Item>();

    // Matériaux des armures
    public static final ArmorMaterial ARMOR_MATERIAL_AZURITE = EnumHelper.addArmorMaterial("armor_material_azurite", Reference.MODID + ":azurite", 175, new int[]{4, 7, 9, 4}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);
    public static final ArmorMaterial ARMOR_MATERIAL_DRACONIUM = EnumHelper.addArmorMaterial("armor_material_draconium", Reference.MODID + ":draconium", 220, new int[]{5, 8, 10, 5}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static final ArmorMaterial ARMOR_MATERIAL_AQUATIQUE = EnumHelper.addArmorMaterial("armor_material_aquatique", Reference.MODID + ":aquatique", 100, new int[]{2, 5, 7, 2}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
    public static final ArmorMaterial ARMOR_MATERIAL_DRACONIQUE = EnumHelper.addArmorMaterial("armor_material_draconique", Reference.MODID + ":draconique", 340, new int[]{7, 10, 12, 7}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F);

    // Armures
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

    static {
        ARMORS.add(AZURITE_HELMET);
        ARMORS.add(AZURITE_CHESTPLATE);
        ARMORS.add(AZURITE_LEGGINGS);
        ARMORS.add(AZURITE_BOOTS);
        ARMORS.add(DRACONIUM_HELMET);
        ARMORS.add(DRACONIUM_CHESTPLATE);
        ARMORS.add(DRACONIUM_LEGGINGS);
        ARMORS.add(DRACONIUM_BOOTS);
        ARMORS.add(DRACONIQUE_HELMET);
        ARMORS.add(DRACONIQUE_CHESTPLATE);
        ARMORS.add(DRACONIQUE_LEGGINGS);
        ARMORS.add(DRACONIQUE_BOOTS);
        ARMORS.add(AQUATIQUE_HELMET);
    }

    public static void init() {
    }
}
