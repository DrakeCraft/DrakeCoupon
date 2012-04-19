package com.drakecoupon;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DrakeConfig {

	private DrakeCoupon dc;
	private Configuration config;
	
	private File couponCodeFile;
	private YamlConfiguration couponCode;
	
	public DrakeConfig(DrakeCoupon dc)
	{
		this.dc = dc;
		
		config = dc.getConfig();
		
		setupConfiguration();
	}
	
	public String getCoupon(String couponID)
	{
		return couponCode.getString(couponID);
	}
	
	public boolean getCouponFileName()
	{
		return config.getBoolean("options.files.couponfile");
	}
	
	public void reloadConfiguration()
	{
		if(couponCodeFile == null)
		{
			couponCodeFile = new File(dc.getDataFolder(), "coupons.yml");
		}
		
		if(!couponCodeFile.exists())
		{
			try {
				couponCodeFile.createNewFile();
			} catch (IOException e) {}
		}
		couponCode = YamlConfiguration.loadConfiguration(couponCodeFile);
	}
	
	private void setupConfiguration()
	{
		if(couponCode == null)
		{
			reloadConfiguration();
		}
	}
	
	public void saveConfiguration()
	{
		dc.saveConfig();
		try
		{
			couponCode.save(couponCodeFile);
		} catch(IOException e) {}
	}
}