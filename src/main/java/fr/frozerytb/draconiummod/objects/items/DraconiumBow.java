package fr.frozerytb.draconiummod.objects.items;

import javax.annotation.Nullable;


import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.objects.entity.arrows.switchArrow.EntitySwitchArrow;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DraconiumBow extends ItemBow implements IHasmodel
{
    public DraconiumBow(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.DRACONIUMMOD_TAB);
        setMaxDamage(800);
        setMaxStackSize(1);

        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    return entityIn.getActiveItemStack().getItem() != ItemInit.DRACONIUM_BOW ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
                }
            }
        });

        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });

        ItemInit.ITEMS.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public int getItemEnchantability()
    {
        return 10;
    }

    @Override
    protected boolean isArrow(ItemStack stack) {
        return stack.getItem() == ItemInit.SWITCH_ARROW; // Ne permet que la flèche modée
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            ItemStack arrowStack = this.findAmmo(player);

            // Vérifie que l'item est une flèche modée
            if (!arrowStack.isEmpty() && arrowStack.getItem() instanceof ItemSwitchArrow) {
                // Logique pour tirer la flèche
                if (!world.isRemote) {
                    EntitySwitchArrow arrow = new EntitySwitchArrow(world, player);
                    arrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
                    world.spawnEntity(arrow);
                }

                // Décrémente la flèche de l'inventaire
                arrowStack.shrink(1);
                player.getCooldownTracker().setCooldown(this, 20);
            } else {
                // Message d'erreur pour l'utilisateur
                player.sendStatusMessage(new TextComponentString("Seules les flèches modées peuvent être utilisées !"), true);
            }
        }
    }
}  