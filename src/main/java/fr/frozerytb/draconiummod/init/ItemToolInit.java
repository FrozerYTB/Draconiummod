package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.items.tools.*;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemToolInit {
    public static final List<Item> TOOLS = new ArrayList<Item>();

    // Matériaux des outils
    public static final Item.ToolMaterial TOOLS_MATERIAL_AZURITE = EnumHelper.addToolMaterial("tools_material_azurite", 4, 2890, 9.0F, 4.0F, 17);
    public static final Item.ToolMaterial HAMMER_MATERIAL_AZURITE = EnumHelper.addToolMaterial("hammer_material_azurite", 4, 2890, 1.0F, 2.0F, 17);
    public static final Item.ToolMaterial TOOLS_MATERIAL_DRACONIUM = EnumHelper.addToolMaterial("tools_material_draconium", 5, 3890, 10.0F, 6.0F, 25);
    public static final Item.ToolMaterial HAMMER_MATERIAL_DRACONIUM = EnumHelper.addToolMaterial("hammer_material_draconium", 5, 3890, 3.0F, 3.0F, 25);

    // Outils
    public static final Item AZURITE_AXE = new ItemToolAxe("azurite_axe", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_PICKAXE = new ItemToolPickaxe("azurite_pickaxe", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_SHOVEL = new ItemToolSpade("azurite_shovel", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_SWORD = new ItemToolSword("azurite_sword", TOOLS_MATERIAL_AZURITE);
    public static final Item AZURITE_HAMMER = new ItemToolHammer("azurite_hammer", HAMMER_MATERIAL_AZURITE);

    public static final Item DRACONIUM_AXE = new ItemToolAxe("draconium_axe", TOOLS_MATERIAL_DRACONIUM);
    public static final Item DRACONIUM_PICKAXE = new ItemToolPickaxe("draconium_pickaxe", TOOLS_MATERIAL_DRACONIUM);
    public static final Item DRACONIUM_SHOVEL = new ItemToolSpade("draconium_shovel", TOOLS_MATERIAL_DRACONIUM);
    public static final Item DRACONIUM_SWORD = new ItemToolSword("draconium_sword", TOOLS_MATERIAL_DRACONIUM);
    public static final Item DRACONIUM_HAMMER = new ItemToolHammer("draconium_hammer", HAMMER_MATERIAL_DRACONIUM);



    public static void init() {
        TOOLS.add(AZURITE_AXE);
        TOOLS.add(AZURITE_PICKAXE);
        TOOLS.add(AZURITE_SHOVEL);
        TOOLS.add(AZURITE_SWORD);
        TOOLS.add(AZURITE_HAMMER);
        TOOLS.add(DRACONIUM_AXE);
        TOOLS.add(DRACONIUM_PICKAXE);
        TOOLS.add(DRACONIUM_SHOVEL);
        TOOLS.add(DRACONIUM_SWORD);
        TOOLS.add(DRACONIUM_HAMMER);
    }
}
