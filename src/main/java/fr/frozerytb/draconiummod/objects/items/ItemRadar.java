package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.Main;
import fr.frozerytb.draconiummod.guis.GuiRadar;
import fr.frozerytb.draconiummod.init.ItemInit;
import fr.frozerytb.draconiummod.util.interfaces.IHasmodel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemRadar extends Item implements IHasmodel
{
	public static int dura = 90*20*60;
	public static int percents = 0;
	private static EntityPlayer player;
	public ItemRadar(String name)
	{
		this.addPropertyOverride(new ResourceLocation("percent"), new IItemPropertyGetter()
		{
			@SideOnly(Side.CLIENT)
			@Override
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			{
				return percents;
			}
		});

		this.setMaxDamage(this.dura);
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setCreativeTab(Main.DraconiummodTab);
		ItemInit.ITEMS.add(this);
	}
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0);
	}
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

		this.player = (EntityPlayer)entityIn;

		if (this.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemRadar)
		{
			if(dura >= 0)
			{
				this.dura --;
				stack.damageItem(1, this.player);

				if(GuiRadar.amountTiles <= 25)
				{
					this.percents = GuiRadar.amountTiles;
				}
				else if (GuiRadar.amountTiles >= 26)
				{
					this.percents = 26;
				}
			}
		}

		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
}
