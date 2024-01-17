package fr.frozerytb.draconiummod.init;

import java.util.ArrayList;
import java.util.List;

import fr.frozerytb.draconiummod.objects.items.ItemBase;
import fr.frozerytb.draconiummod.objects.items.armor.ArmorMod;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;


public class ItemInit 
{
	public static List<Item> ITEMS = new ArrayList<Item>();

	
	//LINGOT
	public static final Item AZURITE_INGOT = new ItemBase("azurite_ingot");
	public static final Item DRACONIUM_INGOT = new ItemBase("draconium_ingot");
	public static final Item FINDIUM_CRISTAL = new ItemBase("findium_cristal");

	//OUTILS
	public static final Item AZURITE_PICKAXE = new ItemPickaxe("azurite_pickaxe");
	public static final Item AZURITE_SWORD = new ItemSword("azurite_sword");
	public static final Item AZURITE_AXE = new ItemAxe("azurite_axe");

	//ARMURES
	public static final Item AZURITE_HELMET = new ArmorMod("azurite_helmet",  AZURITE_ARMOR, 1,
			EntityEquipmentSlot.HEAD);
	public static final Item AZURITE_CHESTPLATE = new ArmorMod("azurite_chestplate", AZURITE_ARMOR, 1, EntityEquipmentSlot.CHEST);
	public static final Item AZURITE_LEGGINGS = new ArmorMod("azurite_leggings",  AZURITE_ARMOR, 2, EntityEquipmentSlot.LEGS);
	public static final Item AZURITE_BOOTS = new ArmorMod("azurite_boots",  AZURITE_ARMOR, 1, EntityEquipmentSlot.FEET);

	//materiaux D'ARMURES
	public static final ItemArmor.ArmorMaterial AZURITE_ARMOR = EnumHelper.addArmorMaterial("armor_azurite", Reference.MODID + ":azurite", 75, new int[] {3, 5, 4, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0f);
}

public void main() {
}


