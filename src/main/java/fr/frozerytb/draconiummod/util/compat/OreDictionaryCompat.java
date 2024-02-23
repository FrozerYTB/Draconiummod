package fr.frozerytb.draconiummod.util.compat;

import fr.frozerytb.draconiummod.init.BlockInit;
import fr.frozerytb.draconiummod.init.ItemInit;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompat
{
    public static void registerOres()
    {
        OreDictionary.registerOre("oreAzurite", BlockInit.AZURITE_ORE);
        OreDictionary.registerOre("ingotAzurite", ItemInit.AZURITE_INGOT);
        OreDictionary.registerOre("oreDraconium", BlockInit.DRACONIUM_ORE);
        OreDictionary.registerOre("ingotDraconium", ItemInit.DRACONIUM_INGOT);
        OreDictionary.registerOre("oreFindium", BlockInit.FINDIUM_ORE);
        OreDictionary.registerOre("ingotFindium", ItemInit.FINDIUM_CRISTAL);

    }
}
