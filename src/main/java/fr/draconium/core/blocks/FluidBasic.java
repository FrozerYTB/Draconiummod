package fr.draconium.core.blocks;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidBasic extends Fluid
{
	public FluidBasic(String fluidName, ResourceLocation still, ResourceLocation flowing) 
	{
		super(fluidName, still, flowing);
		this.registerFluid();
	}
	
	public void registerFluid()
	{
		FluidRegistry.registerFluid(this);
		FluidRegistry.addBucketForFluid(this);
	}
}