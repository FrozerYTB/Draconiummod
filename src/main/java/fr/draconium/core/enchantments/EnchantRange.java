package fr.draconium.core.enchantments;

import fr.draconium.core.init.enchants.EnchantmentsInit;
import fr.draconium.core.init.items.others.OthersInit;
import fr.draconium.core.messages.Console;
import fr.draconium.core.references.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantRange extends Enchantment
{

	public static final EnchantRange ENCHANT_RANGE = new EnchantRange();
	
	public EnchantRange()
	{
		super(Rarity.COMMON, EnumEnchantmentType.ALL, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		//this.setName("range_enchant");
		//this.setRegistryName(new ResourceLocation(Reference.MODID, "range_enchant"));
		this.setRegistryName("range_enchant");
	}
	
	@Override
	public boolean canApplyTogether(Enchantment enchant)
	{
	    return enchant == EnchantRange.ENCHANT_RANGE || enchant == Enchantments.UNBREAKING;
	}

	@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
        // Déterminez sur quel type d'objet l'enchantement peut être appliqué.
        return stack.getItem() == OthersInit.RADAR;
    }
	
	/**
	 * Probabitité
	 */
	@Override
	public int getMinEnchantability(int enchantmentLevel)
	{
		return enchantmentLevel * 11;
	}
	
	/**
	 * Probabitité
	 */
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return enchantmentLevel * 11;
	}
	
	@Override
	public int getMinLevel()
	{
		return 1;
	}
	
	@Override
	public int getMaxLevel()
	{
		return 3;
	}
}
