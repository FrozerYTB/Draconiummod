package fr.frozerytb.draconiummod.objects.entity;

import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
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

        // Ajoutez ici la logique de l'explosion de la grenade
        if (!this.world.isRemote) {
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, 4.0F, true);
            this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 1, 0, 0, 0, 0);

            // Détruire l'obsidienne à proximité de l'explosion
            destroyObsidianNearby();

            this.setDead();
        }
    }

    private void destroyObsidianNearby() {
        BlockPos explosionPos = new BlockPos(this.posX, this.posY, this.posZ);
        int explosionRadius = 2; // Rayon de l'explosion

        // Parcourt les blocs à proximité de l'explosion
        for (int x = explosionPos.getX() - explosionRadius; x <= explosionPos.getX() + explosionRadius; x++) {
            for (int y = explosionPos.getY() - explosionRadius; y <= explosionPos.getY() + explosionRadius; y++) {
                for (int z = explosionPos.getZ() - explosionRadius; z <= explosionPos.getZ() + explosionRadius; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    if (this.world.getBlockState(currentPos).getBlock() instanceof BlockObsidian && this.world.rand.nextFloat() < 0.4F) {
                        // Détruit aléatoirement des blocs d'obsidienne avec une probabilité de 40%
                        this.world.destroyBlock(currentPos, true);
                    }
                }
            }
        }
    }
}