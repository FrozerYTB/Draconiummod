package fr.frozerytb.draconiummod;

import org.apache.logging.log4j.core.Logger;

import fr.frozerytb.draconiummod.proxy.CommonProxy;
import fr.frozerytb.draconiummod.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	@Instance
	public static Main Instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	public static org.apache.logging.log4j.Logger logger;
	
	@EventHandler
	public static void preinit(FMLPreInitializationEvent e)
	{
		logger = e.getModLog();
		
		proxy.preInit();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent e)
	{
		
	}
	
	@EventHandler
	public static void postinit(FMLPostInitializationEvent e)
	{
		
	}
}
