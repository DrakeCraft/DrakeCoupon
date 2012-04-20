package com.drakecoupon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;

public class DataPool {

	private DrakeCoupon dc;
	private File couponFile;
	private YamlConfiguration couponConfig;
	
	private Economy economy;
	
	public final ArrayList<String> ServiceList = new ArrayList<String>();
	private ArrayList<String> EffectiveServiceList = new ArrayList<String>();
	
	public DataPool(DrakeCoupon dc)
	{
		this.dc = dc;
		
		addDefaults();
		setupConfiguration();
		
		setupEconomy();
		
		ServiceList.add("Economy");
	}
	
	private void addDefaults()
	{
		dc.getConfig().addDefault("options.files.couponfile", "coupons.yml");
		dc.getConfig().options().copyDefaults(true);
		dc.saveConfig();
	}
	
	private void setupConfiguration()
	{
		couponFile = new File(dc.getDataFolder(), getCouponFileName());
		
		if(!couponFile.exists())
		{
			try {
				couponFile.createNewFile();
				couponConfig = YamlConfiguration.loadConfiguration(couponFile);
			} catch (IOException e) {}
		} else {
			couponConfig = YamlConfiguration.loadConfiguration(couponFile);
		}
		
		couponConfig.addDefault("18876", "AdminCoupon;Never");
		couponConfig.options().header("Coupon codes storage file. Editing may cause unintended behaviour!");
		couponConfig.options().copyDefaults(true);
		couponConfig.options().copyHeader(true);
		
		try {
			couponConfig.save(couponFile);
		} catch (IOException e) {}
	}
	
	private void setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = dc.getServer().getServicesManager().getRegistration(Economy.class);
		
		if(economyProvider != null)
		{
			economy = economyProvider.getProvider();
		}
		
		if(economy != null)
		{
			EffectiveServiceList.add("Economy");
		}
	}
	
	public ArrayList<String> enabledServices()
	{
		return EffectiveServiceList;
	}
	
	public String getCouponFileName()
	{
		return dc.getConfig().getString("options.files.couponfile");
	}
	
	public String getCoupon(String couponId)
	{
		return couponConfig.getString(couponId);
	}
}