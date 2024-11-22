package fr.draconium.core.items.others;

import fr.draconium.core.DraconiumCore;
import fr.draconium.core.references.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ItemTimer extends Item
{
	public static ItemTimer instance;
	
	private int secondsTimeLeft;
	private int time 			= 1800;
	private int tickInterval 	= 30;

	public ItemTimer(String name)
	{
		instance = this;
		
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setMaxStackSize(1);
		this.setCreativeTab(DraconiumCore.DRACONIUM_TAB_OTHERS);
		MinecraftForge.EVENT_BUS.register(this);
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
				player.sendMessage(new TextComponentString("Timer de " + this.formatTimeLeft(startTime) + " secondes lancé !"));
			}
			
			if (!tag.getBoolean("enable"))
			{
				int timeLeft = tag.getInteger("time");
				tag.setBoolean("enable", true);
				player.sendMessage(new TextComponentString("Temps restant : " + this.formatTimeLeft(timeLeft) + " secondes"));
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (player == null) return;

		// Vérifie si le joueur tient un ItemTimer dans la main principale
		ItemStack mainHandStack = player.getHeldItemMainhand();

		// Parcourir tous les items de l'inventaire pour mettre à jour chaque timer individuellement
		for (ItemStack itemStack : player.inventory.mainInventory)
		{
			if (itemStack.getItem() instanceof ItemTimer)
			{
				NBTTagCompound tag = itemStack.getOrCreateSubCompound("timer");

				if (tag.getBoolean("enable"))
				{
					// Met en pause si l'item n'est pas dans la main principale
					if (itemStack != mainHandStack)
					{
						tag.setBoolean("enable", false);
						player.sendMessage(new TextComponentString("Le timer est en pause car l'objet n'est plus dans la main."));
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
							player.sendMessage(new TextComponentString("Temps restant : " + this.formatTimeLeft(timeLeft) + " secondes"));
						}

						// Détruit l'item lorsque le timer atteint 0
						if (timeLeft <= 0)
						{
							tag.setBoolean("enable", false);
							player.sendMessage(new TextComponentString("Le timer de " + this.formatTimeLeft(this.time) + " secondes est terminé pour " + this.getRegistryName() + " va être détruit."));
							player.inventory.deleteStack(itemStack);
						}
					}
				}
			}
		}
	}

	private String formatTimeLeft(int seconds)
	{
		seconds = seconds / this.tickInterval;

		int hours 	= seconds / 3600;
		int minutes = seconds / 60;
		int secs 	= seconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, secs);
	}
	
	public String getTimeLeft()
	{
		int hours 	= this.secondsTimeLeft / 3600;
		int minutes = this.secondsTimeLeft / 60;
		int secs 	= this.secondsTimeLeft % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, secs);
	}
}
