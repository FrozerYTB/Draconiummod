package fr.draconium.core.init.items.liquids;

import fr.draconium.core.fluids.FluidLiquid;
import fr.draconium.core.references.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit
{
	public static Fluid DRACONIUM_FLUID = new FluidLiquid("draconium_fluid",
			new ResourceLocation(Reference.MODID, "fluids/draconium_fluid"),
			new ResourceLocation(Reference.MODID, "fluids/draconium_fluid"));
	
	public static void registerFluids()
	{
		registerFluid(DRACONIUM_FLUID);
	}
	
	public static void registerFluid(Fluid fluid)
	{
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
	}
}
