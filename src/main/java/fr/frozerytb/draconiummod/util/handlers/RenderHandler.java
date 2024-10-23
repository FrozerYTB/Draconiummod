package fr.frozerytb.draconiummod.util.handlers;

import fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow.RenderSwitchArrow;
import fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow.SwitchArrowEntity;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHandler {

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(SwitchArrowEntity.class, new IRenderFactory<SwitchArrowEntity>()
        {
            @Override
            public Render<? super SwitchArrowEntity> createRenderFor(RenderManager manager)
            {
                return new RenderSwitchArrow(manager);
            }
        });
    }
}