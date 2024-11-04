package fr.draconium.core.init.items.armors;

import fr.draconium.core.items.armors.AquatiqueArmor;
import fr.draconium.core.items.armors.ArmorBasic;
import fr.draconium.core.items.armors.DraconiqueArmor;
import fr.draconium.core.items.armors.DraconiumArmor;
import fr.draconium.core.materials.ArmorsMaterial;
import fr.draconium.core.references.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ArmorsInit
{

	public static Item AZURITE_HELMET;
	public static Item AZURITE_CHESTPLATE;
	public static Item AZURITE_LEGGINGS;
	public static Item AZURITE_BOOTS;
	
	public static Item DRACONIUM_HELMET;
	public static Item DRACONIUM_CHESTPLATE;
	public static Item DRACONIUM_LEGGINGS;
	public static Item DRACONIUM_BOOTS;
	
	public static Item DRACONIQUE_HELMET;
	public static Item DRACONIQUE_CHESTPLATE;
	public static Item DRACONIQUE_LEGGINGS;
	public static Item DRACONIQUE_BOOTS;
	
	public static Item AQUATIQUE_HELMET;
	
	public static void init()
	{
		AZURITE_HELMET 			= new ArmorBasic("azurite_helmet", ArmorsMaterial.MATERIAL_AZURITE, 1, EntityEquipmentSlot.HEAD);
		AZURITE_CHESTPLATE 		= new ArmorBasic("azurite_chestplate", ArmorsMaterial.MATERIAL_AZURITE, 1, EntityEquipmentSlot.CHEST);
		AZURITE_LEGGINGS 		= new ArmorBasic("azurite_leggings", ArmorsMaterial.MATERIAL_AZURITE, 2, EntityEquipmentSlot.LEGS);
		AZURITE_BOOTS 			= new ArmorBasic("azurite_boots", ArmorsMaterial.MATERIAL_AZURITE, 1, EntityEquipmentSlot.FEET);
		
		DRACONIUM_HELMET 		= new DraconiumArmor("draconium_helmet", ArmorsMaterial.MATERIAL_DRACONIUM, 1, EntityEquipmentSlot.HEAD);
		DRACONIUM_CHESTPLATE 	= new DraconiumArmor("draconium_chestplate",ArmorsMaterial.MATERIAL_DRACONIUM, 1, EntityEquipmentSlot.CHEST);
		DRACONIUM_LEGGINGS 		= new DraconiumArmor("draconium_leggings", ArmorsMaterial.MATERIAL_DRACONIUM, 2, EntityEquipmentSlot.LEGS);
		DRACONIUM_BOOTS 		= new DraconiumArmor("draconium_boots", ArmorsMaterial.MATERIAL_DRACONIUM, 1, EntityEquipmentSlot.FEET);
		
		DRACONIQUE_HELMET 		= new DraconiqueArmor("draconique_helmet", ArmorsMaterial.MATERIAL_DRACONIQUE, 1, EntityEquipmentSlot.HEAD);
		DRACONIQUE_CHESTPLATE 	= new DraconiqueArmor("draconique_chestplate", ArmorsMaterial.MATERIAL_DRACONIQUE, 1, EntityEquipmentSlot.CHEST);
		DRACONIQUE_LEGGINGS 	= new DraconiqueArmor("draconique_leggings", ArmorsMaterial.MATERIAL_DRACONIQUE, 2, EntityEquipmentSlot.LEGS);
		DRACONIQUE_BOOTS 		= new DraconiqueArmor("draconique_boots", ArmorsMaterial.MATERIAL_DRACONIQUE, 1, EntityEquipmentSlot.FEET);
		
		AQUATIQUE_HELMET 		= new AquatiqueArmor("aquatique_helmet", ArmorsMaterial.MATERIAL_AQUATIQUE, 1, EntityEquipmentSlot.HEAD);
	}
	
	@SubscribeEvent
	protected static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				AZURITE_HELMET,
				AZURITE_CHESTPLATE,
				AZURITE_LEGGINGS,
				AZURITE_BOOTS,
				
				DRACONIUM_HELMET,
				DRACONIUM_CHESTPLATE,
				DRACONIUM_LEGGINGS,
				DRACONIUM_BOOTS,
				
				DRACONIQUE_HELMET,
				DRACONIQUE_CHESTPLATE,
				DRACONIQUE_LEGGINGS,
				DRACONIQUE_BOOTS,
				
				AQUATIQUE_HELMET
		);
	}
	
	@SubscribeEvent
	protected static void registerRenders(ModelRegistryEvent event)
	{
		registerRender(AZURITE_HELMET);
		registerRender(AZURITE_CHESTPLATE);
		registerRender(AZURITE_LEGGINGS);
		registerRender(AZURITE_BOOTS);
		
		registerRender(DRACONIUM_HELMET);
		registerRender(DRACONIUM_CHESTPLATE);
		registerRender(DRACONIUM_LEGGINGS);
		registerRender(DRACONIUM_BOOTS);
		
		registerRender(DRACONIQUE_HELMET);
		registerRender(DRACONIQUE_CHESTPLATE);
		registerRender(DRACONIQUE_LEGGINGS);
		registerRender(DRACONIQUE_BOOTS);
		
		registerRender(AQUATIQUE_HELMET);
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
