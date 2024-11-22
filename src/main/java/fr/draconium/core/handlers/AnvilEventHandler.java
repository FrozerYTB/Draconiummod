package fr.draconium.core.handlers;

import java.util.Map;

import fr.draconium.core.items.others.ItemRadar;
import fr.draconium.core.references.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MODID)
public class AnvilEventHandler
{
	@SubscribeEvent
	public void onAnvilUpdate(AnvilUpdateEvent event)
	{
		ItemStack leftSlot = event.getLeft(); // Radar dans le 1er slot
		ItemStack rightSlot = event.getRight(); // Livre d'enchantement dans le 2ème slot

		if (leftSlot.getItem() instanceof ItemRadar && rightSlot.getItem() instanceof ItemEnchantedBook)
		{
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(rightSlot);
			ItemStack output = leftSlot.copy();

			for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet())
			{
				Enchantment enchantment = entry.getKey();
				int level = entry.getValue();

				// Vérifie si l'enchantement peut être appliqué
				if (enchantment.canApply(output))
				{
					EnchantmentHelper.setEnchantments(enchantments, output);
					event.setOutput(output);
					event.setCost(level * 2); // Coût en niveaux d'expérience
					return;
				}
			}
		}
	}
}
