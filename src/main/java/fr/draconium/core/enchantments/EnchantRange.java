package fr.draconium.core.enchantments;

import fr.draconium.core.items.others.ItemRadar;
import fr.draconium.core.references.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantRange extends Enchantment
{

	public static final EnchantRange ENCHANT_RANGE = new EnchantRange(Rarity.VERY_RARE, EnumEnchantmentType.DIGGER, EntityEquipmentSlot.MAINHAND);
	
	protected EnchantRange(Rarity rarityIn,EnumEnchantmentType typeIn,EntityEquipmentSlot... slots)
	{
		super(rarityIn, typeIn, slots);
		this.setRegistryName(new ResourceLocation(Reference.MODID, "range_enchant"));
	}
	
	// Permettre l'application de l'enchantement sur des livres
	@Override
	public boolean isAllowedOnBooks()
	{
		return true;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return stack.getItem() instanceof ItemRadar || stack.getItem() instanceof ItemEnchantedBook;
	}

	@Override
	public boolean canApply(ItemStack stack)
	{
		return stack.getItem() instanceof ItemRadar || stack.getItem() instanceof ItemEnchantedBook;
	}

	// Définir le niveau maximum de l'enchantement
	@Override
	public int getMaxLevel()
	{
		return 3;
	}
}
