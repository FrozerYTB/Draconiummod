package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class ArmorBase extends ItemArmor implements IHasmodel {

    private static final int TELEPORT_INTERVAL_TICKS = 84000; // 7 minutes en ticks
    private int teleportCooldown = TELEPORT_INTERVAL_TICKS; // Compteur de cooldown de téléportation

    public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.DraconiummodTab);
        ItemInit.ITEMS.add(this);
        MinecraftForge.EVENT_BUS.register(this); // Enregistre l'objet pour écouter les événements
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

    private boolean isWearingFullDraconiqueArmor(EntityPlayer player) {
        return player.inventory.armorInventory.get(3).getItem() == ItemInit.DRACONIQUE_HELMET
                && player.inventory.armorInventory.get(2).getItem() == ItemInit.DRACONIQUE_CHESTPLATE
                && player.inventory.armorInventory.get(1).getItem() == ItemInit.DRACONIQUE_LEGGINGS
                && player.inventory.armorInventory.get(0).getItem() == ItemInit.DRACONIQUE_BOOTS;
    }

    private boolean isWearingFullDraconiumArmor(EntityPlayer player) {
        return player.inventory.armorInventory.get(3).getItem() == ItemInit.DRACONIUM_HELMET
                && player.inventory.armorInventory.get(2).getItem() == ItemInit.DRACONIUM_CHESTPLATE
                && player.inventory.armorInventory.get(1).getItem() == ItemInit.DRACONIUM_LEGGINGS
                && player.inventory.armorInventory.get(0).getItem() == ItemInit.DRACONIUM_BOOTS;
    }

    private boolean isWearingFullAquatiqueArmor(EntityPlayer player) {
        return player.inventory.armorInventory.get(3).getItem() == ItemInit.AQUATIQUE_HELMET;
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
            teleportCooldown--; // Décrémente le cooldown de téléportation
        }
    }

    private void handleDraconiumArmorEffects(EntityPlayer player) {
        if (this == ItemInit.DRACONIUM_HELMET) {
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
        entity.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 300, 0, true, true)); // 300 ticks = 15 secondes
    }

    private void applyResistance(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 20, 0, true, true)); // 20 ticks = 1 seconde
    }

    private void applySpeed(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 20, 0, true, true)); // 20 ticks = 1 seconde
    }

    private void applyHaste(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(Potion.getPotionById(3), 20, 0, true, true)); // 20 ticks = 1 seconde
    }

    private void applyWaterBreathing(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(Potion.getPotionById(13), 300, 0, true, true)); // 300 ticks = 15 secondes
    }

    public static void applyEnergyShield(EntityPlayer player) {
        if (player.getRNG().nextDouble() < 0.3) { // 30% de chance d'activer le bouclier
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 100, 1)); // Résistance
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

            BlockPos pos = new BlockPos(x, y, z);

            // Assurez-vous que la position est valide avant de faire apparaître l'allié
            if (world.getCollisionBoxes(player, player.getEntityBoundingBox().offset(x - player.posX, y - player.posY, z - player.posZ)).isEmpty()) {
                EntityWolf ally = new EntityWolf(world);
                ally.setPosition(x, y, z);
                world.spawnEntity(ally);
            }
        }
    }


    public static void teleportRandomly(EntityPlayer player) {
        if (!player.world.isRemote) {
            World world = player.world;
            Random random = player.getRNG();

            double distance = 1.0D + random.nextDouble() * 9.0D;
            double x = player.posX + (random.nextDouble() - 0.5D) * 2.0D * distance;
            double y = player.posY;
            double z = player.posZ + (random.nextDouble() - 0.5D) * 2.0D * distance;

            BlockPos pos = new BlockPos(x, y, z);

            if (world.getCollisionBoxes(player, player.getEntityBoundingBox().offset(x - player.posX, y - player.posY, z - player.posZ)).isEmpty()) {
                player.setPositionAndUpdate(x, y, z);
            }
        }
    }

    private void removePotionEffects(EntityLivingBase entity) {
        entity.removePotionEffect(Potion.getPotionById(16)); // Night Vision
        entity.removePotionEffect(Potion.getPotionById(11)); // Resistance
        entity.removePotionEffect(Potion.getPotionById(1));  // Speed
        entity.removePotionEffect(Potion.getPotionById(3));  // Haste
        entity.removePotionEffect(Potion.getPotionById(13)); // Water Breathing
    }

    @SubscribeEvent
    public void onUnequipped(PlayerEvent.ItemCraftedEvent event) {
        // Vérifie si l'item qui a été enlevé est une pièce d'armure
        if (event.crafting.getItem() == ItemInit.DRACONIQUE_HELMET
                || event.crafting.getItem() == ItemInit.DRACONIQUE_CHESTPLATE
                || event.crafting.getItem() == ItemInit.DRACONIQUE_LEGGINGS
                || event.crafting.getItem() == ItemInit.DRACONIQUE_BOOTS
                || event.crafting.getItem() == ItemInit.DRACONIUM_HELMET
                || event.crafting.getItem() == ItemInit.DRACONIUM_CHESTPLATE
                || event.crafting.getItem() == ItemInit.DRACONIUM_LEGGINGS
                || event.crafting.getItem() == ItemInit.DRACONIUM_BOOTS
                || event.crafting.getItem() == ItemInit.AQUATIQUE_HELMET) {
            // Retire les effets de potion
            removePotionEffects(event.player);
        }
    }
}
