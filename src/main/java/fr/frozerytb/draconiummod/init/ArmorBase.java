package fr.frozerytb.draconiummod.init;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorBase extends ItemArmor implements IHasmodel {

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

    private void applyNightVision(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 300, 0, true, true)); // 300 ticks = 15 secondes
    }

    private void applyResistance(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 20, 0, true, true)); // 300 ticks = 15 secondes
    }

    private void applySpeed(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 20, 0, true, true)); // 300 ticks = 15 secondes
    }

    private void applyHaste(EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(Potion.getPotionById(3), 20, 0, true, true)); // 300 ticks = 15 secondes
    }
    @SubscribeEvent
    public void onUnequipped(PlayerEvent.ItemCraftedEvent event) {
        // Vérifie si l'item qui a été enlevé est une pièce d'armure draconium
        if (event.crafting.getItem() == ItemInit.DRACONIUM_HELMET
                || event.crafting.getItem() == ItemInit.DRACONIUM_CHESTPLATE
                || event.crafting.getItem() == ItemInit.DRACONIUM_LEGGINGS
                || event.crafting.getItem() == ItemInit.DRACONIUM_BOOTS) {
            // Retire les effets de potion
            removePotionEffects(event.player);
        }
    }

    private void removePotionEffects(EntityLivingBase entity) {
        entity.removePotionEffect(Potion.getPotionById(16)); // Night Vision
        entity.removePotionEffect(Potion.getPotionById(11)); // Strength
        entity.removePotionEffect(Potion.getPotionById(1));  // Speed
        entity.removePotionEffect(Potion.getPotionById(3));  // Haste
    }
}