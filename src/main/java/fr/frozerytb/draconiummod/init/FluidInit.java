package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.blocks.fluids.BlockFakeWaterFluid;
import fr.frozerytb.draconiummod.objects.blocks.fluids.FluidFakeWater;
import fr.frozerytb.draconiummod.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit
{
    public static final Fluid FAKE_WATER_FLUID = new FluidFakeWater("fake_water", new ResourceLocation(Reference.MODID + ":blocks/white_water_still"), new ResourceLocation(Reference.MODID + ":blocks/white_water_flow"), new ResourceLocation(Reference.MODID + ":blocks/white_water_overlay"));

    public static void registerFluids()
    {
        registerFluid(FAKE_WATER_FLUID);
    }

    public static void registerFluid(Fluid fluid)
    {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}
