package fr.frozerytb.draconiummod.objects.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class EntityGrenade extends EntitySnowball {

    public EntityGrenade(World worldIn) {
        super(worldIn);
    }

    public EntityGrenade(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @Override
    protected void onImpact(net.minecraft.util.math.RayTraceResult result) {
        super.onImpact(result);

        // Ajouter ici la logique de l'explosion de la grenade
        if (!this.world.isRemote) {
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, 4.0F, true);
            this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 1, 0, 0, 0, 0);
            this.setDead();
        }
    }
}