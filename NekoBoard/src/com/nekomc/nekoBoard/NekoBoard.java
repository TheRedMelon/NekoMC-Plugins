package com.nekomc.nekoBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.nekomc.nekoBoard.event.custom.GeneralScoreboardUpdate;
import com.nekomc.nekoBoard.event.player.PlayerJoin;

public class NekoBoard extends JavaPlugin {

	public static NekoBoard plugin;
	
	@SuppressWarnings("rawtypes")
	public HashMap<String, Class> worldBoards = new HashMap<String, Class>();
	public HashMap<UUID, Object> playerBoardClassInst = new HashMap<UUID, Object>();
	public HashMap<UUID, Scoreboard> playerBoard = new HashMap<UUID, Scoreboard>();
	public HashMap<UUID, Objective> playerObj = new HashMap<UUID, Objective>();
	
	PluginManager pm = getServer().getPluginManager();
	
	public void onEnable() {
		
		plugin = this;
		
		registerConfig();
		registerEvents();
		
	}
	
	private void registerConfig() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		Set<String> worlds = getConfig().getConfigurationSection("world-boards").getKeys(false);
		Map<String, Object> boards = getConfig().getConfigurationSection("world-boards").getValues(false);
		
		for (String world : worlds) {
				
			try {
				
				worldBoards.put(world, Class.forName("com.nekomc.nekoBoard.boards." + (String) boards.get(world)));
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	private void registerEvents() {
		
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new GeneralScoreboardUpdate(), this);
		
	}
	
}
