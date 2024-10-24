package fr.frozerytb.draconiummod.proxy;

import fr.frozerytb.draconiummod.guis.GuiRadar;
import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.init.KeyBindings;
import fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow.EntitySwitchArrow;
import fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow.RenderSwitchArrow;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Reference.MODID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();
        // Register GUI elements
        this.registerGuis();
        // Register models and custom states
        registerModels();
    }

    @Override
    public void init() {
        KeyBindings.init();
        registerEntityRenderers();
    }

    public void registerModels() {
        // Enregistre les modèles pour tous les items
        for (Item item : ItemInit.ITEMS) {
            registerItemRenderer(item, 0);
        }

        // Enregistre les modèles pour tous les blocs
        for (Block block : BlockInit.BLOCKS) {
            registerItemRenderer(Item.getItemFromBlock(block), 0);
        }
    }

    @Override
    public void registerItemRenderer(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        super.registerVariantRenderer(item, meta, filename, id);
    }

    public void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySwitchArrow.class, RenderSwitchArrow::new);
    }

    public void registerGuis() {
        MinecraftForge.EVENT_BUS.register(new GuiRadar());
    }
}
