package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.objects.blocks.fluids.BlockFakeWaterFluid;
import fr.frozerytb.draconiummod.objects.blocks.fluids.FluidFakeWater;
import fr.frozerytb.draconiummod.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit {
    public static final Fluid FAKE_WATER_FLUID = new FluidFakeWater("fake_water",
            new ResourceLocation(Reference.MODID + ":blocks/fake_water_still"),
            new ResourceLocation(Reference.MODID + ":blocks/fake_water_flow"),
            new ResourceLocation(Reference.MODID + ":blocks/fake_water_overlay")) {

        public void onEntityCollision(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos, net.minecraft.entity.Entity entity, float damage) {
            // Vérifie si l'entité nage dans le fluide
            if (!entity.isImmuneToFire() && !entity.isInWater() && !entity.isWet()) {
                // Inflige des dégâts à l'entité
                entity.attackEntityFrom(DamageSource.DROWN, 2.0f);
            }
        }
    };

    public static void registerFluids() {
        registerFluid(FAKE_WATER_FLUID);
    }

    public static void registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}