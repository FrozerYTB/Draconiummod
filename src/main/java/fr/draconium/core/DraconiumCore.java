package fr.draconium.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.draconium.core.entitys.EntityGrenade;
import fr.draconium.core.handlers.AmbientSoundHandler;
import fr.draconium.core.handlers.AnvilEventHandler;
import fr.draconium.core.handlers.PlayerJoinHandler;
import fr.draconium.core.handlers.RenderHandler;
import fr.draconium.core.init.blocks.BlocksInit;
import fr.draconium.core.init.blocks.ores.BlocksOresInit;
import fr.draconium.core.init.capabilities.CapabilitiesInit;
import fr.draconium.core.init.enchants.EnchantmentsInit;
import fr.draconium.core.init.items.armors.ArmorsInit;
import fr.draconium.core.init.items.foods.FoodsInit;
import fr.draconium.core.init.items.liquids.FluidInit;
import fr.draconium.core.init.items.ores.OresInit;
import fr.draconium.core.init.items.others.OthersInit;
import fr.draconium.core.init.items.sticks.SticksInit;
import fr.draconium.core.init.items.swords.SwordsInit;
import fr.draconium.core.init.items.tools.ToolsInit;
import fr.draconium.core.init.keys.KeyBindings;
import fr.draconium.core.init.sounds.BackgroundInit;
import fr.draconium.core.overlays.RadarOverlay;
import fr.draconium.core.proxy.ServerProxy;
import fr.draconium.core.proxy.packets.server.DraconiumCorePackets;
import fr.draconium.core.references.Reference;
import fr.draconium.core.renders.RenderGrenade;
import fr.draconium.core.tabs.DraconiumCoreTab;
import fr.draconium.core.tabs.DraconiumCoreTabEnchant;
import fr.draconium.core.worlds.generation.GenerationOres;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MINECRAFT_VERSION)
public class DraconiumCore
{
	private static DraconiumCore instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER, modId = Reference.MODID)
	private static ServerProxy serverProxy;
	
	public static final Logger LOGGER = LogManager.getLogger("");
	
	static
	{
		FluidRegistry.enableUniversalBucket();
	}
	
	public static CreativeTabs DRACONIUM_TAB_BLOCK 		= new DraconiumCoreTab("draconium_block");
	public static CreativeTabs DRACONIUM_TAB_FOOD 		= new DraconiumCoreTab("draconium_food");
	public static CreativeTabs DRACONIUM_TAB_ARMORS 	= new DraconiumCoreTab("draconium_armor");
	public static CreativeTabs DRACONIUM_TAB_TOOLS 		= new DraconiumCoreTab("draconium_tool");
	public static CreativeTabs DRACONIUM_TAB_OTHERS 	= new DraconiumCoreTab("draconium_other");
	public static CreativeTabs DRACONIUM_TAB_STIKCS 	= new DraconiumCoreTab("draconium_sticks");
	public static CreativeTabs DRACONIUM_TAB_ENCHANTS 	= new DraconiumCoreTabEnchant("draconium_enchant");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//Register proxy
		CapabilitiesInit.init();
		KeyBindings.init();
		DraconiumCorePackets.registerMessages();
		
		//Register Fluids
		FluidInit.registerFluids();
		
		//Register Blocks
		BlocksInit.init();
		BlocksOresInit.init();
		
		//Register Items
		FoodsInit.init();
		ArmorsInit.init();
		OresInit.init();
		ToolsInit.init();
		SwordsInit.init();
		OthersInit.init();
		SticksInit.init();
		
		//Register Enchants
		
		//Register Event
		this.registerEventBus();
		
		//Register Game Sound
		BackgroundInit.init();
		
		//Register Render
		RenderGrenade.register();
		
		//Register Entity
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "grenade"), EntityGrenade.class, "Grenade", 1, this, 64, 10, true);
		
		//Register Handler Fluids
		RenderHandler.registerCustomMeshesAndStates();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		instance = this;
		
		serverProxy.register();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		((DraconiumCoreTab) DRACONIUM_TAB_BLOCK).setIcon(BlocksInit.DRACONIUM_BLOCK);
		((DraconiumCoreTab) DRACONIUM_TAB_FOOD).setIcon(FoodsInit.DRACONIUM_APPLE);
		((DraconiumCoreTab) DRACONIUM_TAB_ARMORS).setIcon(ArmorsInit.DRACONIUM_CHESTPLATE);
		((DraconiumCoreTab) DRACONIUM_TAB_TOOLS).setIcon(ToolsInit.DRACONIUM_PICKAXE);
		((DraconiumCoreTab) DRACONIUM_TAB_OTHERS).setIcon(OthersInit.RADAR);
		((DraconiumCoreTab) DRACONIUM_TAB_STIKCS).setIcon(SticksInit.REGENERATION_STICK);
		((DraconiumCoreTabEnchant) DRACONIUM_TAB_ENCHANTS).setIcon(Items.ENCHANTED_BOOK);
	}
	
	private void registerEventBus()
	{
		MinecraftForge.EVENT_BUS.register(AmbientSoundHandler.class);
		MinecraftForge.EVENT_BUS.register(PlayerJoinHandler.class);
		
		MinecraftForge.EVENT_BUS.register(new RadarOverlay());
		
		MinecraftForge.EVENT_BUS.register(AnvilEventHandler.class);
		MinecraftForge.EVENT_BUS.register(EnchantmentsInit.class);
		
		GameRegistry.registerWorldGenerator(new GenerationOres(), 0);
	}
	
	public static DraconiumCore getInstance()
	{
		return instance;
	}
	
	public static ServerProxy getServerProxy()
	{
		return serverProxy;
	}
}
