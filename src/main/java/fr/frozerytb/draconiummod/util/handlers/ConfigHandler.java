package fr.frozerytb.draconiummod.util.handlers;

import java.io.File;


import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler
{
    public static Configuration config;
    public static int ENTITY_SWITCH_ARROW = 251;

    public static void init(File file)
    {
        config = new Configuration(file);
        String category;


        category = "Entity IDs";
        config.addCustomCategoryComment(category, "Set IDs for each Entity.");
        ENTITY_SWITCH_ARROW = config.getInt("Entity Diamond Arrow", category, 251, 250, 500, "Entity Diamond Arrow ID");





        config.save();
    }

    public static void registerConfig(FMLPreInitializationEvent event)
    {
        Main.config = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
        Main.config.mkdirs();
        init(new File(Main.config.getPath(), Reference.MODID + ".cfg"));
    }
}