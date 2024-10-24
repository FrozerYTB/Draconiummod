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

        // Vérifie que l'impact est avec une entité
        if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
            Entity hitEntity = result.entityHit;

            if (hitEntity != null && this.shootingEntity instanceof EntityPlayer) {
                EntityPlayer shooter = (EntityPlayer) this.shootingEntity;


                double playerPosX = shooter.posX;
                double playerPosY = shooter.posY;
                double playerPosZ = shooter.posZ;

                // Téléporte le joueur et l'entité à leurs nouvelles positions
                shooter.setPositionAndUpdate(hitEntity.posX, hitEntity.posY, hitEntity.posZ);
                hitEntity.setPositionAndUpdate(playerPosX, playerPosY, playerPosZ);

                // Enlève la flèche après l'impact
                this.setDead();
            }
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }
}
