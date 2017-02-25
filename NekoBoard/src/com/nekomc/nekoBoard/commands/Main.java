package com.nekomc.nekoBoard.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scoreboard.ScoreboardManager;

import com.nekomc.leCorePlugin.customEvents.PlayerBoardUpdateEvent;
import com.nekomc.leCorePlugin.randomStuff.BoardSection;

public class Main implements CommandExecutor {

	PluginManager pm = Bukkit.getPluginManager();
	ScoreboardManager sbm = Bukkit.getScoreboardManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		if (args.length < 1) {
			
			sender.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /nm <update | hide> [player | section | board] [section | board] [section | board]...");
			return false;
			
		} else if (args[0].equalsIgnoreCase("update")) {
		
			if (args.length == 1) {
				
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					
					for (BoardSection section : BoardSection.values()) {
						
						pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), section));
						
					}
					
				}
				
			} else if (args.length == 2) {
				
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[1]))) {
					
					Player p = Bukkit.getPlayerExact(args[1]);
					
					for (BoardSection section : BoardSection.values()) {
					
						pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), section));
					
					}
						
				} else {
				
					try {
						
						BoardSection section = BoardSection.valueOf(args[1].toUpperCase());
						
						for (Player p : Bukkit.getServer().getOnlinePlayers()) {
							
							pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), section));
							
						}
						
					} catch (IllegalArgumentException e) {
						
						sender.sendMessage(ChatColor.DARK_RED + "No user or board section " + args[1]);
						return false;
						
					}
					
				}
				
			} else if (args.length == 3) {
				
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[1]))) {
					
					Player p = Bukkit.getPlayerExact(args[1]);
					
					try {
						
						pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(args[2].toUpperCase())));
					
					} catch (IllegalArgumentException e) {
						
						sender.sendMessage(ChatColor.DARK_RED + "No board section " + args[2]);
						return false;
						
					}
						
				} else {
					
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
						
						List<String> argsL = new ArrayList<String>(Arrays.asList(args));
						
						argsL.remove(0);
						
						for (String str : argsL) {
							
							try {
								
								pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(str.toUpperCase())));
								
							} catch (IllegalArgumentException e) {
								
								sender.sendMessage(ChatColor.DARK_RED + "No player name " + args[1] + " and no board section " + args.toString());
								return false;
								
							}
							
						}
						
					}
					
				}
				
			} else {
				
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayerExact(args[1]))) {
					
					Player p = Bukkit.getPlayerExact(args[1]);
					List<String> argsL = new ArrayList<String>(Arrays.asList(args));
					
					argsL.remove(0);
					argsL.remove(0);
					
					for (String str : argsL) {
						
						try {
						
							pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(str.toUpperCase())));
						
						} catch (IllegalArgumentException e) {
							
							sender.sendMessage(ChatColor.DARK_RED + "No board section " + str);
							return false;
							
						}
							
					}
					
				} else {
					
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
						
						List<String> argsL = new ArrayList<String>(Arrays.asList(args));
						
						argsL.remove(0);
						
						for (String str : argsL) {
							
							try {
								
								pm.callEvent(new PlayerBoardUpdateEvent(p.getUniqueId(), BoardSection.valueOf(str)));
								
							} catch (IllegalArgumentException e) {
								
								sender.sendMessage(ChatColor.DARK_RED + "No player " + args[1] + " and no section " + str);
								
							}
							
						}
						
					}
					
				}
				
			}
		
		} else if (args[0].equals("hide")) {
			
			if (args.length == 2) {
				
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					
					p.setScoreboard(sbm.getMainScoreboard());
					
				}
				
			} else if (args.length == 3) {
				
				Player p = Bukkit.getPlayerExact(args[2]);
				
				p.setScoreboard(sbm.getMainScoreboard());
				
			} else {
				
				sender.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /nm <update | hide> [player | section | board] [section | board] [section | board]...");
				return false;
				
			}
			
		} else {
			
			sender.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /nm <update | hide> [player | section | board] [section | board] [section | board]...");
			return false;
			
		}
		
		return true;
	}

}
