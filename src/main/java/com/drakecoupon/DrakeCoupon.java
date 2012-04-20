package com.drakecoupon;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class DrakeCoupon extends JavaPlugin {
	
	private DataPool dp;
	
	public void onEnable()
	{
		dp = new DataPool(this);
		
		if(dp.enabledServices() == dp.ServiceList)
		{
			Logger.getLogger("Minecraft").info(ChatColor.GREEN + "[DrakeCoupon] Services have started! Running normally!");
		} else {
			Logger.getLogger("Minecraft").info(ChatColor.RED + "[DrakeCoupon] One or more of the services are currently not running!");
			Logger.getLogger("Minecraft").info(ChatColor.RED + "[DrakeCoupon] Currently enabled services:");
			
			for(int i = 0; i < dp.enabledServices().size(); i++)
			{
				Logger.getLogger("Minecraft").info(ChatColor.RED + dp.enabledServices().get(i));
			}
		}
	}
	
	public void onDisable()
	{
		Logger.getLogger("Minecraft").info("[DrakeCoupon] Shutting down services!");
	}
}