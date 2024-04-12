package fr.frozerytb.draconiummod.objects.blocks.fluids;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import javax.swing.text.html.parser.Entity;

public class FluidFakeWater extends Fluid
{
    public FluidFakeWater(String name, ResourceLocation still, ResourceLocation flow, ResourceLocation overlay)
    {
        super(name, still, flow, overlay);
        this.setUnlocalizedName(name);
        this.rarity(Enchantment.Rarity.RARE);

    }


    private void rarity(Enchantment.Rarity rarity) {
    }
}