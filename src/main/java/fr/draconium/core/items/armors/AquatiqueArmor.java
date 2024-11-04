package fr.draconium.core.items.armors;

import fr.draconium.core.DraconiumCore;
import fr.draconium.core.init.items.armors.ArmorsInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class AquatiqueArmor extends ItemArmor
{
	
	private Item[] armors_aquatique = {ArmorsInit.AQUATIQUE_HELMET/*, ArmorsInit.AQUATIQUE_CHESTPLATE, ArmorsInit.AQUATIQUE_LEGGINGS, ArmorsInit.AQUATIQUE_BOOTS*/};
	
	public AquatiqueArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(DraconiumCore.DRACONIUM_TAB_ARMORS);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if (this.isAmrorsCompletAquatique(player) == true) this.handleAquatiqueArmorEffects(player);
	}
	
	
	protected boolean isAmrorsCompletAquatique(EntityPlayer player)
	{
		return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem().equals(this.armors_aquatique[0])
				//&& player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().equals(this.armors_aquatique[1])
				//&& player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem().equals(this.armors_aquatique[2])
				//&& player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem().equals(this.armors_aquatique[3]) 
				? true : false;
	}
	
	private void handleAquatiqueArmorEffects(EntityPlayer player)
	{
		player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 300, 0, true, true)); 	// 300 ticks 	= 15 secondes
	}
	
	
}
