package fr.draconium.core.overlays;

import java.util.HashSet;
import java.util.Set;

import fr.draconium.core.items.others.ItemRadar;
import fr.draconium.core.references.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class RadarOverlay extends Gui
{
	public static int amountTiles 				= 0;

	private final Minecraft minecraft 			= Minecraft.getMinecraft();
	private final ResourceLocation te0 			= new ResourceLocation(Reference.MODID, "textures/items/radars/radar_empty.png");
	private final ResourceLocation te1_5 		= new ResourceLocation(Reference.MODID, "textures/items/radars/radar_0-5.png");
	private final ResourceLocation te6_10 		= new ResourceLocation(Reference.MODID, "textures/items/radars/radar_6-10.png");
	private final ResourceLocation te11_25 		= new ResourceLocation(Reference.MODID, "textures/items/radars/radar_11-25.png");
	private final ResourceLocation te26_more 	= new ResourceLocation(Reference.MODID, "textures/items/radars/radar_26+.png");

	@SubscribeEvent
	public void onRenderPre(RenderGameOverlayEvent.Pre event)
	{
		if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET)
		{
			ItemStack stack = ItemRadar.instance.getUsableItemStack(this.minecraft.player);
			if (stack.isEmpty())
			{
				this.amountTiles = 0;
				return;
			}

			int chunksRadius 			= ItemRadar.instance.getRadarRange(stack);

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
			drawCenteredString(this.minecraft.fontRenderer, ItemRadar.instance.getTimeLeft(), 23, 48, -1);
		}
	}
}