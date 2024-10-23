package fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SwitchArrowEntity extends EntityTippedArrow {

    public SwitchArrowEntity(World worldIn) {
        super(worldIn);
    }

    public SwitchArrowEntity(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    public void onHit(RayTraceResult result) {
        super.onHit(result);

        if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
            Entity hitEntity = result.entityHit;
            EntityPlayer shooter = (EntityPlayer) this.shootingEntity;

            if (hitEntity != null && shooter != null) {
                // Échanger les positions
                double playerPosX = shooter.posX;
                double playerPosY = shooter.posY;
                double playerPosZ = shooter.posZ;

                // Échanger les positions
                shooter.setPositionAndUpdate(hitEntity.posX, hitEntity.posY, hitEntity.posZ);
                hitEntity.setPositionAndUpdate(playerPosX, playerPosY, playerPosZ);

                // Enlever la flèche après l'impact
                this.setDead();
            }
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }
}
