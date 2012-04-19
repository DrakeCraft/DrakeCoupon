package com.drakecoupon;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class DrakeCoupon extends JavaPlugin {
	
	private DrakeConfig config;
	private DataPool dp;
	
	public void onEnable()
	{
		config = new DrakeConfig(this);
		dp = new DataPool(this);
		
		Logger.getLogger("Minecraft").info(config.getCoupon("12345"));
		
		getConfig().addDefault("options.files.couponfile", "coupons.yml");
		config.saveConfiguration();
		
		if(DataPool.useEconomy)
		dp.economy.bankBalance("TheRandomGuy10");
	}
	
	public void onDisable()
	{
		config.saveConfiguration();
	}
}