package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.blocks.fluids.FluidFakeWater;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;

public class FluidInit {

    public static final List<Fluid> FLUIDS = new ArrayList<>();

    // Déclaration des fluides
    public static final Fluid FAKE_WATER_FLUID = new FluidFakeWater("fake_water",
            new ResourceLocation(Reference.MODID, "blocks/fake_water_still"),
            new ResourceLocation(Reference.MODID, "blocks/fake_water_flow"),
            new ResourceLocation(Reference.MODID, "blocks/fake_water_overlay"));

    public static void registerFluids() {
        registerFluid(FAKE_WATER_FLUID);
    }

    public static void initFluids() {
        registerFluids();
    }

    private static void registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        FLUIDS.add(fluid);
        System.out.println("Registered fluid: " + fluid.getName());
    }
}

