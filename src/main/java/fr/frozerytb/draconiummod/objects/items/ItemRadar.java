package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.init.ItemSpecialInit;
import fr.frozerytb.draconiummod.objects.enchantments.EnchantRange;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ItemRadar extends Item implements IHasmodel {

    private static final int BASE_USE_TIME = 90 * 20 * 60; // 90 minutes en ticks
    private static final double BASE_RANGE = 50.0; // Plage de base du radar
    private static final double RANGE_BONUS_PER_LEVEL = 16.0; // Bonus par niveau d'enchantement

    public ItemRadar(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(Main.DRACONIUMMOD_TAB);
        this.setMaxStackSize(1);
        ItemInit.ITEMS.add(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return getMaxUseTime(stack);
    }

    // Détermine le temps d'utilisation maximum en fonction de l'enchantement Unbreaking
    public static int getMaxUseTime(ItemStack stack) {
        int unbreakingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack);
        return BASE_USE_TIME + unbreakingLevel * 30 * 20 * 60; // Ajoute 30 minutes par niveau
    }

    @Override
    public int getDamage(ItemStack stack) {
        return getUsedTime(stack);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        NBTTagCompound tag = stack.getOrCreateSubCompound("radarData");
        tag.setInteger("usedTime", damage);
    }

    // Gère l'incrément du temps utilisé à chaque tick
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && event.side.isServer()) {
            EntityPlayer player = event.player;
            if (!player.isCreative()) {
                ItemStack stack = getUsableItemStack(player);
                if (!stack.isEmpty()) {
                    NBTTagCompound tag = stack.getOrCreateSubCompound("radarData");
                    int usedTime = tag.getInteger("usedTime") + 1;

                    if (usedTime > getMaxUseTime(stack)) {
                        player.renderBrokenItemStack(stack);
                        stack.shrink(1);
                        player.addStat(StatList.getObjectBreakStats(stack.getItem()));
                    } else {
                        tag.setInteger("usedTime", usedTime);
                    }
                }
            }
        }
}

    // Récupère l'item actif (Radar) dans les mains du joueur
    public static ItemStack getUsableItemStack(EntityPlayer player) {
        for (EnumHand hand : EnumHand.values()) {
            ItemStack stack = player.getHeldItem(hand);
            if (stack.getItem() == ItemSpecialInit.RADAR) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static double getRadarRange(ItemStack stack) {
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantRange.ENCHANT_RANGE, stack);
        double baseRange = 50.0;
        double rangeBonus = level * 16.0;
        return baseRange + rangeBonus;
    }

    // Récupère le temps utilisé à partir du NBT
    public static int getUsedTime(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        return tag != null ? tag.getInteger("usedTime") : 0;
    }

    // Affiche la portée du radar lorsque l'item est utilisé
    public void onItemUseCustom(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            double range = getRadarRange(stack);
            player.sendMessage(new TextComponentString("Radar range: " + range + " chunks"));
        }
    }
}
