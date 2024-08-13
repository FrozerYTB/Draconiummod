package fr.frozerytb.draconiummod.objects.blocks.fluids;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidFakeWater extends Fluid {
    public FluidFakeWater(String name, ResourceLocation still, ResourceLocation flow, ResourceLocation overlay) {
        super(name, still, flow, overlay);
        // Optionnel : définir le nom non localisé et la rareté
        this.setUnlocalizedName(name);
        this.setRarity(EnumRarity.RARE);
    }
}
