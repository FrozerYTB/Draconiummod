package fr.frozerytb.draconiummod.proxy;

import fr.frozerytb.draconiummod.guis.GuiRadar;
import fr.frozerytb.draconiummod.init.KeyBindings;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        MinecraftForge.EVENT_BUS.register(new GuiRadar());
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        super.registerVariantRenderer(item, meta, filename, id);
    }

    @Override
    public void registerEntityRenderers() {
        super.registerEntityRenderers();
    }

    @Override
    public void preInit() {
        super.preInit();
        this.registerGuis();
    }

    @Override
    public void init() {
        KeyBindings.init();
    }

    public void registerGuis() {

    }
}
