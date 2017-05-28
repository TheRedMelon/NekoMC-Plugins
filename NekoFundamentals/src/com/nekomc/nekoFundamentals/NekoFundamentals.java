package com.nekomc.nekoFundamentals;

import org.bukkit.plugin.java.JavaPlugin;

import com.nekomc.nekoFundamentals.commands.GamemodeCmd;
import com.nekomc.nekoFundamentals.commands.Suicide;

public class NekoFundamentals extends JavaPlugin{
	
	public void onEnable() {
		
		registerCommands();
		
	}
	
	private void registerCommands() {
		
		getCommand("gamemode").setExecutor(new GamemodeCmd());
		getCommand("suicide").setExecutor(new Suicide());
		
	}
	
}
