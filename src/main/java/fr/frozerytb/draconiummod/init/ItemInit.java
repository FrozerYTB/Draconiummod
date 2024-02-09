package fr.frozerytb.draconiummod.init;

import java.util.ArrayList;
import java.util.List;

import fr.frozerytb.draconiummod.objects.items.ItemBase;
import fr.frozerytb.draconiummod.init.ArmorBase;
import fr.frozerytb.draconiummod.objects.items.tools.ToolAxe;
import fr.frozerytb.draconiummod.objects.items.tools.ToolPickaxe;
import fr.frozerytb.draconiummod.objects.items.tools.ToolSpade;
import fr.frozerytb.draconiummod.objects.items.tools.ToolSword;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.item.ItemArmor.ArmorMaterial;


public class ItemInit
{
    public static List<Item> ITEMS = new ArrayList<Item>();


    //materiaux
    public static final ArmorMaterial ARMOR_MATERIAL_AZURITE = EnumHelper.addArmorMaterial("armor_material_azurite", Reference.MODID + ":azurite", 14,
            new int[] {4, 7, 9, 4}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F);

    public static final ArmorMaterial ARMOR_MATERIAL_DRACONIUM = EnumHelper.addArmorMaterial("armor_material_draconium", Reference.MODID + ":draconium", 14,
            new int[] {6, 9, 11, 6}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);

    public static final Item.ToolMaterial TOOLS_MATERIAL_AZURITE = EnumHelper.addToolMaterial("tools_material_azurite", 4, 2890, 9.0F, 4.0F, 17);
    public static final Item.ToolMaterial TOOLS_MATERIAL_DRACONIUM = EnumHelper.addToolMaterial("tools_material_draconium", 5, 3890, 10.0F, 6.0F, 25);



    //LINGOT
    public static final Item AZURITE_INGOT = new ItemBase("azurite_ingot");
    public static final Item DRACONIUM_INGOT = new ItemBase("draconium_ingot");
    public static final Item FINDIUM_CRISTAL = new ItemBase("findium_cristal");

    //OUTILS
    public static final Item AZURITE_AXE = new ToolAxe("azurite_axe", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_PICKAXE = new ToolPickaxe("azurite_pickaxe", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_SHOVEL = new ToolSpade("azurite_shovel", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_SWORD = new ToolSword("azurite_sword", TOOLS_MATERIAL_AZURITE);

    //ARMURES

    public static final Item AZURITE_HELMET = new ArmorBase("azurite_helmet", ARMOR_MATERIAL_AZURITE, 1, EntityEquipmentSlot.HEAD);
    public static final Item AZURITE_CHESTPLATE= new ArmorBase("azurite_chestplate", ARMOR_MATERIAL_AZURITE, 1, EntityEquipmentSlot.CHEST);
    public static final Item AZURITE_LEGGINGS = new ArmorBase("azurite_leggings", ARMOR_MATERIAL_AZURITE, 2, EntityEquipmentSlot.LEGS);
    public static final Item AZURITE_BOOTS = new ArmorBase("azurite_boots", ARMOR_MATERIAL_AZURITE, 1, EntityEquipmentSlot.FEET);



}




