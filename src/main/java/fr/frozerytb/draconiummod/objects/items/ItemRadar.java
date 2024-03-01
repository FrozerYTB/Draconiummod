package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.guis.GuiRadar;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemRadar extends Item implements IHasmodel {
    public static final int maxUseTime = 90 * 20 * 60; // 90 minutes

    public ItemRadar(String name) {
        this.addPropertyOverride(new ResourceLocation("percent"), new IItemPropertyGetter() {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return Math.min(26, GuiRadar.amountTiles);
            }
        });

        // Utilisation d'un système de "durabilité" alternatif, car sinon on ne pourrait pas dépasser 54 minutes d'utilisation (65535 ticks)
        this.addPropertyOverride(new ResourceLocation("damage"), new IItemPropertyGetter() {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                int usedTime = getUsedTime(stack);
                return MathHelper.clamp((float) usedTime / (float) maxUseTime, 0.0F, 1.0F);
            }
        });
        this.addPropertyOverride(new ResourceLocation("damaged"), new IItemPropertyGetter() {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                int usedTime = getUsedTime(stack);
                return usedTime > 0 ? 1.0f : 0.0f;
            }
        });

        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setCreativeTab(Main.DraconiummodTab);
        ItemInit.ITEMS.add(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return maxUseTime;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return getUsedTime(stack) > 0;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        NBTTagCompound itemNBT = stack.getTagCompound();
        if (itemNBT == null) {
            stack.setTagCompound(itemNBT = new NBTTagCompound());
        }
        itemNBT.setInteger("usedTime", damage);
    }

    @Override
    public ItemRadar setMaxDamage(int maxDamageIn) {
        return this;
    }

    @Override
    public int getDamage(ItemStack stack) {
        return getUsedTime(stack);
    }

    @SubscribeEvent
    public void onTickPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (event.phase == TickEvent.Phase.START && event.side.isServer() && !player.isCreative()) {
            ItemStack stack = getUsableItemStack(player);
            NBTTagCompound itemNBT = stack.getTagCompound();
            if (itemNBT == null) {
                stack.setTagCompound(itemNBT = new NBTTagCompound());
            }
            int usedTime = itemNBT.getInteger("usedTime") + 1;
            if (usedTime > maxUseTime) {
                player.renderBrokenItemStack(stack);
                stack.shrink(1);
                player.addStat(StatList.getObjectBreakStats(stack.getItem()));
                if (stack.isEmpty()) {
                    return;
                }
                usedTime = 0;
            }
            itemNBT.setInteger("usedTime", usedTime);
        }
    }

    public static ItemStack getUsableItemStack(EntityPlayer player) {
        for (EnumHand hand : EnumHand.values()) {
            ItemStack stack = player.getHeldItem(hand);
            if (stack.getItem() == ItemInit.RADAR) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static int getUsedTime(ItemStack stack) {
        NBTTagCompound itemNBT = stack.getTagCompound();
        if (itemNBT == null) {
            return 0;
        }
        return itemNBT.getInteger("usedTime");
    }
}