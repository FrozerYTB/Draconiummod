package fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow;

import java.util.Random;

import fr.frozerytb.draconiummod.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySwitchArrow extends EntityArrow {

    public EntitySwitchArrow(World worldIn) {
        super(worldIn);
    }

    public EntitySwitchArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntitySwitchArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemInit.SWITCH_ARROW);
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        // Logique de particules, etc., peut être laissée ici si nécessaire
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote) {
            if (this.inGround) {
                if (this.timeInGround % 5 == 0) {
                    this.spawnParticles(1);
                }
            } else {
                this.spawnParticles(2);
            }
        }
    }

    private void spawnParticles(int particleCount) {
        Random rand = new Random();
        int i = rand.nextInt(15);
        double d0 = (double)(i >> 16 & 255) / 255.0D;
        double d1 = (double)(i >> 8 & 255) / 255.0D;
        double d2 = (double)(i >> 0 & 255) / 255.0D;
        for (int j = 0; j < particleCount; ++j) {
            this.world.spawnParticle(EnumParticleTypes.NOTE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, d0, d1, d2);
        }
    }

    // Ajout de la logique pour échanger la place avec l'entité touchée
    @Override
    protected void onHit(RayTraceResult result) {
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
}
