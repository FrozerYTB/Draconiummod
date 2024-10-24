package fr.frozerytb.draconiummod.guis;

import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.objects.items.ItemRadar;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.time.Duration;

@SideOnly(Side.CLIENT)
public class GuiRadar extends Gui {
    public static int amountTiles = 0;
    private static Minecraft mc;
    final ResourceLocation te0_5 = new ResourceLocation(Reference.MODID, "textures/items/radar_0-5.png");
    final ResourceLocation te6_10 = new ResourceLocation(Reference.MODID, "textures/items/radar_6-10.png");
    final ResourceLocation te11_25 = new ResourceLocation(Reference.MODID, "textures/items/radar_11-25.png");
    final ResourceLocation te26_more = new ResourceLocation(Reference.MODID, "textures/items/radar_26+.png");
    FontRenderer fontRender;

    public GuiRadar() {
        mc = Minecraft.getMinecraft();
        this.fontRender = mc.fontRenderer;
    }

    public static String formatDuration() {
        ItemStack stack = mc.player.getHeldItem(EnumHand.MAIN_HAND);

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
            if (mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == ItemInit.RADAR /*&& ItemRadar.isUsed == 1*/) {
                amountTiles = mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX, mc.player.chunkCoordZ).getTileEntityMap().values().size();
                amountTiles += mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX, mc.player.chunkCoordZ + 1).getTileEntityMap().values().size();
                amountTiles += mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX, mc.player.chunkCoordZ - 1).getTileEntityMap().values().size();
                amountTiles += mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX + 1, mc.player.chunkCoordZ).getTileEntityMap().values().size();
                amountTiles += mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX - 1, mc.player.chunkCoordZ).getTileEntityMap().values().size();

                if (amountTiles <= 5) {
                    mc.getTextureManager().bindTexture(this.te0_5);

                    drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 32, 32, 32, 32);
                } else if (amountTiles <= 10) {
                    mc.getTextureManager().bindTexture(this.te6_10);

                    drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 32, 32, 32, 32);
                } else if (amountTiles <= 25) {
                    mc.getTextureManager().bindTexture(this.te11_25);

                    drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 32, 32, 32, 32);
                } else {
                    mc.getTextureManager().bindTexture(this.te26_more);

                    drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 32, 32, 32, 32);
                }
                drawCenteredString(mc.fontRenderer, amountTiles + "%", 23, 39, -1);
                drawCenteredString(mc.fontRenderer, formatDuration(), 23, 48, -1);
            } else {
                amountTiles = 0;
            }
        }
    }
}