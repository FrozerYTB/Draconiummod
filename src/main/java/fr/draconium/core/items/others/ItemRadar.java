package fr.draconium.core.items.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.draconium.core.DraconiumCore;
import fr.draconium.core.enchantments.EnchantRange;
import fr.draconium.core.init.items.others.OthersInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ItemRadar extends Item
{

	public static ItemRadar instance;

	private int time = 90;
	private int tickInterval = 30;
	private int secondsTimeLeft;

	private int BASE_RANGE = 1; // Plage de base du radar

	public ItemRadar(String name)
	{
		instance = this;

		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setMaxStackSize(1);
		this.setCreativeTab(DraconiumCore.DRACONIUM_TAB_OTHERS);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("");
		tooltip.add("§f§nDescription:");
		tooltip.add("§7- Permet de detecter les entitées se trouvant autour du joueur");
		tooltip.add("");
		tooltip.add("§f§nInformation:");
		tooltip.add("§7- Distance:§r §a" + this.getRadarRange(stack) + " §7Chunck");
		tooltip.add("");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

		if (world.isRemote)
		{
			NBTTagCompound tag = stack.getOrCreateSubCompound("timer");
			if (tag.getTag("enable") == null)
			{
				int startTime = this.time * this.tickInterval;
				tag.setBoolean("enable", true);
				tag.setInteger("time", startTime);
			}

			if (!tag.getBoolean("enable"))
			{
				int timeLeft = tag.getInteger("time");
				tag.setBoolean("enable", true);
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
	{
		return !ItemStack.areItemStackTagsEqual(oldStack, newStack);
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (player == null) return;

		// Vérifie si le joueur tient un ItemTimer dans la main principale
		ItemStack mainHandStack = player.getHeldItemMainhand();

		// Parcourir tous les items de l'inventaire pour mettre à jour chaque timer
		// individuellement
		for (ItemStack itemStack : player.inventory.mainInventory)
		{
			if (itemStack.getItem() instanceof ItemRadar)
			{
				NBTTagCompound tag = itemStack.getOrCreateSubCompound("timer");

				if (tag.getBoolean("enable"))
				{
					// Met en pause si l'item n'est pas dans la main principale
					if (itemStack != mainHandStack)
					{
						tag.setBoolean("enable", false);
						continue; // Passe à l'item suivant sans décrémenter le timer
					}

					// Si le timer n'est pas en pause, on décrémente le temps
					int timeLeft = tag.getInteger("time");
					if (timeLeft > 0)
					{
						timeLeft--;
						tag.setInteger("time", timeLeft);

						this.secondsTimeLeft = tag.getInteger("time");

						// Envoie un message à intervalle régulier
						if (timeLeft % tickInterval == 0)
						{

						}

						// Détruit l'item lorsque le timer atteint 0
						if (timeLeft <= 0)
						{
							tag.setBoolean("enable", false);
							player.sendMessage(new TextComponentString("Le timer est terminé pour " + this.getRegistryName() + ", va être détruit."));
							player.inventory.deleteStack(itemStack);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return true;
	}

	@Override
	public int getItemEnchantability()
	{
		return 1; // Valeur de base pour la probabilité d'enchantement (similaire au diamant)
	}
	/*
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
	{
		// Permet uniquement l'enchantement `Range`
		return enchantment == EnchantRange.ENCHANT_RANGE || enchantment == Enchantments.UNBREAKING;
	}
	*/
	/*
	@SubscribeEvent
	public void onItemEnchant(PlayerInteractEvent.RightClickBlock event)
	{
		EntityPlayer player = event.getEntityPlayer();
		ItemStack itemStack = player.getHeldItemMainhand();

		// Vérifie si l'item est un radar
		if (itemStack.getItem() instanceof ItemRadar)
		{
			// Crée un Map pour appliquer les enchantements
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
			enchantments.put(Enchantments.UNBREAKING, 1); // Applique Unbreaking
			enchantments.put(EnchantRange.ENCHANT_RANGE, 1); // Applique EnchantRange

			// Applique les enchantements à l'item
			EnchantmentHelper.setEnchantments(enchantments, itemStack);

			// Met à jour l'item dans l'inventaire
			player.inventory.markDirty();
		}
	}
	*/
	public int getRadarRange(ItemStack stack)
	{
		int level = EnchantmentHelper.getEnchantmentLevel(EnchantRange.ENCHANT_RANGE, stack);

		switch (level)
		{
			case 1:
				return BASE_RANGE * 9 * 2;
			case 2:
				return BASE_RANGE * 9 * 3;
			case 3:
				return BASE_RANGE * 9 * 4;
		}
		return BASE_RANGE;
	}

	private String formatTimeLeft(int seconds)
	{
		seconds = seconds / this.tickInterval;

		int hours = seconds / 3600;
		int minutes = seconds / 60;
		int secs = seconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, secs);
	}

	public String getTimeLeft()
	{
		int hours = this.secondsTimeLeft / 3600;
		int minutes = this.secondsTimeLeft / 60;
		int secs = this.secondsTimeLeft % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, secs);
	}

	/**
	 * @apiNote Récupère l'item actif (Radar) dans les mains du joueur
	 */
	public ItemStack getUsableItemStack(EntityPlayer player)
	{
		for (EnumHand hand : EnumHand.values())
		{
			ItemStack stack = player.getHeldItem(hand);
			if (stack.getItem() == OthersInit.RADAR) return stack;
		}
		return ItemStack.EMPTY;
	}
}
