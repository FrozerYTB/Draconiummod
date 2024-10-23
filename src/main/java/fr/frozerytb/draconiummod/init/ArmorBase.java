package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.capabilities.ExtendedPlayerData;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class ArmorBase extends ItemArmor implements IHasmodel {
    private static final int TELEPORT_INTERVAL_TICKS = 84000; // 70 minutes en ticks
    private static final int TELEPORT_DISTANCE = 9;

    public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.DRACONIUMMOD_TAB);
        ItemInit.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (isWearingFullDraconiqueArmor(player)) {
            handleDraconiqueArmorEffects(player);
        } else if (isWearingFullDraconiumArmor(player)) {
            handleDraconiumArmorEffects(player);
        } else if (isWearingFullAquatiqueArmor(player)) {
            handleAquatiqueArmorEffects(player);
        }
    }

    public static boolean isWearingFullDraconiqueArmor(EntityPlayer player) {
        return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemInit.DRACONIQUE_HELMET
                && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemInit.DRACONIQUE_CHESTPLATE
                && player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemInit.DRACONIQUE_LEGGINGS
                && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemInit.DRACONIQUE_BOOTS;
    }

    public static boolean isWearingFullDraconiumArmor(EntityPlayer player) {
        return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemInit.DRACONIUM_HELMET
                || player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemInit.DRACONIUM_CHESTPLATE
                || player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemInit.DRACONIUM_LEGGINGS
                || player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemInit.DRACONIUM_BOOTS;
    }

    public static boolean isWearingFullAquatiqueArmor(EntityPlayer player) {
        return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemInit.AQUATIQUE_HELMET;
    }

    private void handleDraconiqueArmorEffects(EntityPlayer player) {
        if (this == ItemInit.DRACONIQUE_HELMET) {
            applyNightVision(player);
        }
        if (this == ItemInit.DRACONIQUE_CHESTPLATE) {
            applyResistance(player);
        }
        if (this == ItemInit.DRACONIQUE_LEGGINGS) {
            applySpeed(player);
        }
        if (this == ItemInit.DRACONIQUE_BOOTS) {
            applyHaste(player);
        }
    }

    private void handleDraconiumArmorEffects(EntityPlayer player) {
        if (this != ItemInit.DRACONIUM_HELMET) {
        } else {
            applyNightVision(player);
        }
        if (this == ItemInit.DRACONIUM_CHESTPLATE) {
            applyResistance(player);
        }
        if (this == ItemInit.DRACONIUM_LEGGINGS) {
            applySpeed(player);
        }
        if (this == ItemInit.DRACONIUM_BOOTS) {
            applyHaste(player);
        }
    }

    private void handleAquatiqueArmorEffects(EntityPlayer player) {
        if (this == ItemInit.AQUATIQUE_HELMET) {
            applyWaterBreathing(player);
        }
    }

    private void applyNightVision(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0, true, true)); // 300 ticks = 15 secondes
    }

    private void applyResistance(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20, 0, true, true)); // 20 ticks = 1 seconde
    }

    private void applySpeed(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 0, true, true)); // 20 ticks = 1 seconde
    }

    private void applyHaste(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 20, 0, true, true)); // 20 ticks = 1 seconde
    }

    private void applyWaterBreathing(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 300, 0, true, true)); // 300 ticks = 15 secondes
    }

    public static void applyEnergyShield(EntityPlayer player) {
        if (player.getRNG().nextDouble() < 0.6) { // 60% de chance d'activer le bouclier
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 1)); // Résistance
        }
    }

    public static void spawnAllies(EntityPlayer player) {
        if (!player.world.isRemote) {
            World world = player.world;
            Random random = player.getRNG();

            // Déterminer une distance aléatoire entre 1 et 10 blocs
            double distance = 1.0D + random.nextDouble() * 9.0D;

            double x = player.posX + (random.nextDouble() - 0.5D) * 2.0D * distance;
            double y = player.posY;
            double z = player.posZ + (random.nextDouble() - 0.5D) * 2.0D * distance;

            // Assurez-vous que la position est valide avant de faire apparaître l'allié
            if (world.getCollisionBoxes(player, player.getEntityBoundingBox().offset(x - player.posX, y - player.posY, z - player.posZ)).isEmpty()) {
                EntityWolf ally = new EntityWolf(world);
                ally.setPosition(x, y, z);
                ally.setTamedBy(player);
                world.spawnEntity(ally);
            }
        }
    }

    public static void teleportRandomly(EntityPlayer player) {
        if (player.world.isRemote) {
            return;
        }
        if (!isWearingFullDraconiqueArmor(player)) {
            // Envoyer un message d'erreur au joueur
            player.sendMessage(new TextComponentString("Vous devez porter une armure complète de Draconium pour vous téléporter."));
            return;
        }

        ExtendedPlayerData.DraconiumArmorAbilities draconiumArmorAbilities = ExtendedPlayerData.get(player).draconiumArmorAbilities;
        if (draconiumArmorAbilities.getTeleportCooldown() > 0) {
            player.sendMessage(new TextComponentString("Cette abilité est en cooldown."));
            return;
        }
        draconiumArmorAbilities.setTeleportCooldown(TELEPORT_INTERVAL_TICKS);

        // Code de la téléportation extrait du chorus fruit
        World world = player.world;
        Random random = player.getRNG();

        if (player.isRiding()) {
            player.dismountRidingEntity();
        }

        for (int i = 0; i < 16; i++) {
            double x = player.posX + (random.nextDouble() - 0.5D) * TELEPORT_DISTANCE;
            double y = MathHelper.clamp(player.posY + (random.nextInt(TELEPORT_DISTANCE) - (double) TELEPORT_DISTANCE / 2), 0.0D, world.getActualHeight() - 1);
            double z = player.posZ + (random.nextDouble() - 0.5D) * TELEPORT_DISTANCE;

            if (player.attemptTeleport(x, y, z)) {
                break;
            }
        }
    }
}
