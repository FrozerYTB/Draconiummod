package fr.frozerytb.draconiummod.objects.items;

import fr.frozerytb.draconiummod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.fluids.Fluid;

public class ItemBucketFakeWater extends ItemBucket {

    public ItemBucketFakeWater(Block block) {
        super(block);
        this.setUnlocalizedName("bucket_fake_water");
        this.setRegistryName("bucket_fake_water");
    }

    @Override
    public ItemBucket setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
