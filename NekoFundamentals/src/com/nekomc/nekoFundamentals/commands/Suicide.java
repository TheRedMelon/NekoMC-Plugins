package com.nekomc.nekoFundamentals.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Suicide implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		
		String prefix = ChatColor.DARK_PURPLE + "Cheat" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
		String noPerms = prefix + "Sorry you do not have the required permissions";
		
		if (!sender.hasPermission("nf.suicide")) {
			
			sender.sendMessage(noPerms);
			return false;
			
		}
		
		if (!(sender instanceof Player)) {
			
			sender.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Please run this command as a " + ChatColor.DARK_PURPLE + "PLAYER" + ChatColor.LIGHT_PURPLE + "!");
			return false;
			
		}
		
		Player p = (Player) sender;
		p.setHealth(0D);
		p.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Great sadness drove you to suicide");
		
		return true;
		
	}

}
