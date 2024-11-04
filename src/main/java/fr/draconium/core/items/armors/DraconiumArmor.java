package fr.draconium.core.items.armors;

import fr.draconium.core.DraconiumCore;
import fr.draconium.core.init.items.armors.ArmorsInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class DraconiumArmor extends ItemArmor
{

	private Item[] armors_draconique = {ArmorsInit.DRACONIUM_HELMET, ArmorsInit.DRACONIUM_CHESTPLATE, ArmorsInit.DRACONIUM_LEGGINGS, ArmorsInit.DRACONIUM_BOOTS};
	
	public DraconiumArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(DraconiumCore.DRACONIUM_TAB_ARMORS);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if (this.isAmrorsCompletDraconium(player) == true) this.handleDraconiumArmorEffects(player);
	}
	
	protected boolean isAmrorsCompletDraconium(EntityPlayer player)
	{
		return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem().equals(this.armors_draconique[0])
				&& player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().equals(this.armors_draconique[1])
				&& player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem().equals(this.armors_draconique[2])
				&& player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem().equals(this.armors_draconique[3]) 
				? true : false;
	}
	
	private void handleDraconiumArmorEffects(EntityPlayer player)
	{
		player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0, true, true)); 		// 300 ticks 	= 15 secondes
		player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 0, true, true)); 		// 20 ticks 	= 1 seconde
		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 0, true, true)); 				// 20 ticks 	= 1 seconde
		player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 20, 0, true, true)); 				// 20 ticks 	= 1 seconde
	}
}
