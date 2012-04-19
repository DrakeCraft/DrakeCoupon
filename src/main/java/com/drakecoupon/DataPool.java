package com.drakecoupon;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;

public class DataPool {

	private DrakeCoupon dc;
	public Economy economy = null;
	
	public static boolean useEconomy = true;
	
	public DataPool(DrakeCoupon dc)
	{
		this.dc = dc;
		
		if(setupEconomy())
		{
			Logger.getLogger("[DrakeCoupon] Economy was found!");
		} else {
			useEconomy = false;
			Logger.getLogger("[DrakeCoupon] Economy was not found! Economic features have been disabled!");
		}
	}
	
	private boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = dc.getServer().getServicesManager().getRegistration(Economy.class);
		
		if(economyProvider != null)
		{
			economy = economyProvider.getProvider();
		}
		
		return (economy != null);
	}
}