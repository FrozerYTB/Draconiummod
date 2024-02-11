package fr.frozerytb.draconiummod.guis;

import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiRadar extends Gui {
    private static Minecraft mc;

    public static int amountTiles = 0;

    private static FontRenderer fontRenderer;

    final ResourceLocation te0_5 = new ResourceLocation(Reference.MODID + ":textures/gui/radar_0-5.png");
    final ResourceLocation te6_10 = new ResourceLocation(Reference.MODID + ":textures/gui/radar_6_10.png");
    final ResourceLocation te11_25 = new ResourceLocation(Reference.MODID + ":textures/gui/radar_11_25.png");
    final ResourceLocation te26_more = new ResourceLocation(Reference.MODID + ":textures/gui/radar_26+.png");

    public GuiRadar()
    {
        this.mc = Minecraft.getMinecraft();
        this.fontRenderer =this.mc.fontRenderer;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRendererPre(RenderGameOverlayEvent.Pre event)
    {
        if(event.getType() == RenderGameOverlayEvent.ElementType.HELMET && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == ItemInit.RADAR)
        {
            amountTiles = mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX + 0, mc.player.chunkCoordZ + 0).getTileEntityMap().values().size();
            amountTiles = amountTiles+ mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX + 0, mc.player.chunkCoordZ + 1).getTileEntityMap().values().size();
            amountTiles = amountTiles + mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX + 0, mc.player.chunkCoordZ + -1).getTileEntityMap().values().size();
            amountTiles = amountTiles+ mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX + 1, mc.player.chunkCoordZ + 1).getTileEntityMap().values().size();
            amountTiles = amountTiles+ mc.world.getChunkFromChunkCoords(mc.player.chunkCoordX + -1, mc.player.chunkCoordZ + 1).getTileEntityMap().values().size();

            if (amountTiles <= 5)
            {
                mc.getTextureManager().bindTexture(this.te0_5);

                drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 32, 32, 32, 32);
            }

           else if (amountTiles <= 10 && amountTiles >= 6)
            {
                mc.getTextureManager().bindTexture(this.te6_10);

                drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 32, 32, 32, 32);
            }

            else if (amountTiles <= 25 && amountTiles >= 11)
            {
                mc.getTextureManager().bindTexture(this.te11_25);

                drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 32, 32, 32, 32);
            }

            else if (amountTiles >= 26)
            {
                mc.getTextureManager().bindTexture(this.te26_more);

                drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 32, 32, 32, 32);
            }

            drawCenteredString(this.fontRenderer, this.amountTiles + "%", 23, 39, -1);
            drawCenteredString(this.fontRenderer, this.amountTiles + "", 23, 48, -1);

            event.setCanceled(true);
        }
    }

}
