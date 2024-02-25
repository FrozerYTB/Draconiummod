package fr.frozerytb.draconiummod.util.interfaces;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IHasmodel {
    @SideOnly(Side.CLIENT)
    void registerModels();
}
