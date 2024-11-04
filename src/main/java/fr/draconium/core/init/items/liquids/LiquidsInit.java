package fr.draconium.core.init.items.liquids;

//@Mod.EventBusSubscriber(modid = Reference.MODID)
public class LiquidsInit
{
	/*
	public static Block FLUID_BLOCK;
	public static Fluid FLUID_TEXTURE;
	
	public static void init()
	{
		FLUID_TEXTURE 	= 	new FluidBasic("fluid", 
							new ResourceLocation(Reference.MODID, "blocks/fluids/fluid_texture"), 
							new ResourceLocation(Reference.MODID, "blocks/fluids/fluid_texture"));
		FLUID_BLOCK 	= 	new BlockFluid("fluid", FLUID_TEXTURE, Material.WATER);
	}
	
	@SubscribeEvent
	protected static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(
				FLUID_BLOCK
		);
	}
	
	@SubscribeEvent
	protected static void registerItemBlocks(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				new ItemBlock(FLUID_BLOCK).setRegistryName(FLUID_BLOCK.getRegistryName())
		);
	}
	
	/**
	 * @apiNote Get json model
	 
	@SubscribeEvent
	protected static void regsiterRenders(ModelRegistryEvent event)
	{
		registerRender(Item.getItemFromBlock(FLUID_BLOCK));
	}
	
	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	*/
}

