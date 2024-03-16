package fr.frozerytb.draconiummod.guis;

import fr.frozerytb.draconiummod.objects.items.ItemRadar;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.time.Duration;

@SideOnly(Side.CLIENT)
public class GuiRadar extends Gui {
    public static int amountTiles = 0;

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final ResourceLocation te0 = new ResourceLocation(Reference.MODID, "textures/items/radar_empty.png");
    private static final ResourceLocation te1_5 = new ResourceLocation(Reference.MODID, "textures/items/radar_0-5.png");
    private static final ResourceLocation te6_10 = new ResourceLocation(Reference.MODID, "textures/items/radar_6-10.png");
    private static final ResourceLocation te11_25 = new ResourceLocation(Reference.MODID, "textures/items/radar_11-25.png");
    private static final ResourceLocation te26_more = new ResourceLocation(Reference.MODID, "textures/items/radar_26+.png");

    public static String formatDuration(ItemStack stack) {
        int remainingTime = ItemRadar.maxUseTime - ItemRadar.getUsedTime(stack);
        Duration duration = Duration.ofSeconds(remainingTime / 20);
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }


    @SubscribeEvent
    public void onRenderPre(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
            ItemStack stack = ItemRadar.getUsableItemStack(mc.player);
            if (stack.isEmpty()) {
                amountTiles = 0;
                return;
            }
            amountTiles = mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX, mc.player.chunkCoordZ).getTileEntityMap().values().size();
            amountTiles += mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX, mc.player.chunkCoordZ + 1).getTileEntityMap().values().size();
            amountTiles += mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX, mc.player.chunkCoordZ - 1).getTileEntityMap().values().size();
            amountTiles += mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX + 1, mc.player.chunkCoordZ).getTileEntityMap().values().size();
            amountTiles += mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX - 1, mc.player.chunkCoordZ).getTileEntityMap().values().size();



            ResourceLocation texture;
            if (amountTiles > 25) {
                texture = te26_more;
            } else if (amountTiles > 10) {
                texture = te11_25;
            } else if (amountTiles > 5) {
                texture = te6_10;
            } else if (amountTiles > 0) {
                texture = te1_5;
            } else {
                texture = te0;
            }
            mc.getTextureManager().bindTexture(texture);
            drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 32, 32, 32, 32);
            drawCenteredString(mc.fontRenderer, amountTiles + "%", 23, 39, -1);
            drawCenteredString(mc.fontRenderer, formatDuration(stack), 23, 48, -1);
        }
    }
}
