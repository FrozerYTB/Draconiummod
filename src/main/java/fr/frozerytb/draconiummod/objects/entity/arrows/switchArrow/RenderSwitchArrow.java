package fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow;

import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSwitchArrow extends RenderArrow<SwitchArrowEntity> {
    public RenderSwitchArrow(RenderManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(SwitchArrowEntity entity) {
        return new ResourceLocation(Reference.MODID + ":textures/entity/arrows/switch_arrow.png");
    }
}
