package fr.draconium.core.init.enchants;

import java.util.ArrayList;
import java.util.List;

import fr.draconium.core.enchantments.EnchantRange;
import fr.draconium.core.messages.Console;
import fr.draconium.core.references.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class EnchantmentsInit
{
	public static EnchantmentsInit instance;

	private static List<Enchantment> enchants = new ArrayList<>();

	public static Enchantment ENCHANTMENT_RANGE = new EnchantRange();

	static
	{
		enchants.add(ENCHANTMENT_RANGE);
	}

	@SubscribeEvent
	public static void enchantRange(LivingUpdateEvent event)
	{
		EntityLivingBase living = event.getEntityLiving();
		int level = EnchantmentHelper.getMaxEnchantmentLevel(ENCHANTMENT_RANGE, living);
	}

	@SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event)
	{
		Console.debug("- Enregistrement des enchantements:");
		IForgeRegistry<Enchantment> registry = event.getRegistry();

		// Enregistrement de chaque enchantement dans le registre Forge
		for (Enchantment enchant : enchants)
		{
			registry.register(enchant);
			Console.debug("  - #fbff00: " + enchant.getRegistryName());
		}
	}
}
