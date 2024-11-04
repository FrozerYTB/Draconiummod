package fr.draconium.core.inventorys;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import fr.draconium.core.items.others.ItemRadar;
import fr.draconium.core.references.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Radar extends Gui
{
	public static int amountTiles 				= 0;

	private final Minecraft minecraft 			= Minecraft.getMinecraft();
	private final ResourceLocation te0 			= new ResourceLocation(Reference.MODID, "textures/items/radar_empty.png");
	private final ResourceLocation te1_5 		= new ResourceLocation(Reference.MODID, "textures/items/radar_0-5.png");
	private final ResourceLocation te6_10 		= new ResourceLocation(Reference.MODID, "textures/items/radar_6-10.png");
	private final ResourceLocation te11_25 		= new ResourceLocation(Reference.MODID, "textures/items/radar_11-25.png");
	private final ResourceLocation te26_more 	= new ResourceLocation(Reference.MODID, "textures/items/radar_26+.png");
	
	public static String formatDuration(ItemStack stack)
	{
		int remainingTime = ItemRadar.getMaxUseTime(stack) - ItemRadar.getUsedTime(stack);
		Duration duration = Duration.ofSeconds(remainingTime / 20);
		long seconds = duration.getSeconds();
		long absSeconds = Math.abs(seconds);
		String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60, absSeconds % 60);
		return seconds < 0 ? "-" + positive : positive;
	}

	@SubscribeEvent
	public void onRenderPre(RenderGameOverlayEvent.Pre event)
	{
		if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET)
		{
			ItemStack stack = ItemRadar.getUsableItemStack(this.minecraft.player);
			if (stack.isEmpty())
			{
				this.amountTiles = 0;
				return;
			}

			int chunksRadius 			= (int) ItemRadar.getRadarRange(stack);

			Set<String> chunksVisited 	= new HashSet<>();
			this.amountTiles 			= 0;

			for (int dx = -chunksRadius; dx <= chunksRadius; dx++)
			{
				for (int dz = -chunksRadius; dz <= chunksRadius; dz++)
				{
					if (Math.abs(dx) + Math.abs(dz) <= chunksRadius)
					{
						int chunkX 		= this.minecraft.player.chunkCoordX + dx;
						int chunkZ 		= this.minecraft.player.chunkCoordZ + dz;
						String chunkKey = chunkX + "," + chunkZ;

						if (!chunksVisited.contains(chunkKey))
						{
							chunksVisited.add(chunkKey);
							this.amountTiles += this.minecraft.world.getChunkFromChunkCoords(chunkX, chunkZ).getTileEntityMap().values().size();
						}
					}
				}
			}

			ResourceLocation texture = this.amountTiles > 25 ? this.te26_more : this.amountTiles > 10 ? this.te11_25 : this.amountTiles > 5 ? this.te6_10 : this.amountTiles > 0 ? this.te1_5 : this.te0;
			
			this.minecraft.getTextureManager().bindTexture(texture);
			drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 32, 32, 32, 32);
			drawCenteredString(this.minecraft.fontRenderer, this.amountTiles + "%", 23, 39, -1);
			drawCenteredString(this.minecraft.fontRenderer, Radar.formatDuration(stack), 23, 48, -1);
		}
	}
}