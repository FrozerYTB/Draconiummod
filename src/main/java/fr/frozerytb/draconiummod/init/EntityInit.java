package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow.EntitySwitchArrow;
import fr.frozerytb.draconiummod.util.Reference;
import fr.frozerytb.draconiummod.util.handlers.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {

        public static void registerEntities()
        {
            registerArrow("switch_arrow", EntitySwitchArrow.class, ConfigHandler.ENTITY_SWITCH_ARROW);
        }

        private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
        {
            EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
        }

        private static void registerArrow(String name, Class<? extends Entity> entity, int id)
        {
            EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, 64, 20, true);
        }

        private static void registerProjectile(String name, int id, Class<? extends Entity> entity, Item item)
        {
            EntityRegistry.registerModEntity(new ResourceLocation(name), entity, name, id, Main.instance, 64, 10, true);
        }
    }

