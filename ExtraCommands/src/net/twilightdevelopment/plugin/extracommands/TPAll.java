package net.twilightdevelopment.plugin.extracommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public class TPAll implements CommandExecutor {

	private final JavaPlugin plugin;
	
	public TPAll(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpall")
				&& plugin.getConfig().getBoolean("commands.tpall")) {
			if (sender.hasPermission("extracommands.tpall") || sender instanceof ConsoleCommandSender) {
			if (args.length == 3) {
				double x = 0;
				double y = 0;
				double z = 0;
				boolean failed = false;
				try {
				x = Double.parseDouble(args[0]);
				y = Double.parseDouble(args[1]);
				z = Double.parseDouble(args[2]);
				}
				catch (NumberFormatException e) {
					failed = true;
				}
				if (failed == false) {
				TeleportAll(new Location(Bukkit.getWorld("world"), x, y, z));
				sender.sendMessage(ChatColor.GREEN + "Done!");
				}
				
			}
			else if (args.length == 4) {
				double x = 0;
				double y = 0;
				double z = 0;
				String w = args[3];
				boolean failed = false;
				try {
				x = Double.parseDouble(args[0]);
				y = Double.parseDouble(args[1]);
				z = Double.parseDouble(args[2]);
				}
				catch (NumberFormatException e) {
					sender.sendMessage(ChatColor.RED + "Usage: /tpall <x> <y> <z> (optional)<world>");
					failed = true;
				}
				if (failed == false) {
				TeleportAll(new Location(Bukkit.getWorld(w), x, y, z));
				sender.sendMessage(ChatColor.GREEN + "Done!");
				}
			
		
		else {
			sender.sendMessage(ChatColor.RED + "Incorrect usage of command.");
		}
		}
			return true;
		}
		}			
		return false;
	}

	public void TeleportAll(Location loc) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!p.hasPermission("extracommands.dodgetpall")) {
			p.teleport(loc);
			p.sendMessage(plugin.getConfig().getString("messages.tpall-message"));
			}
		}
		
		
		
	}
	
	
	
	
	
	
	
}