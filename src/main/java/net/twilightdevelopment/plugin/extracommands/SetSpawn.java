package net.twilightdevelopment.plugin.extracommands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SetSpawn extends ExtraCommandExecutor {

  public SetSpawn(JavaPlugin plugin) {
    super(plugin);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("setspawn")
        && plugin.getConfig().getBoolean("commands.setspawn")) {
      if (sender instanceof Player) {
        if (sender.hasPermission("extracommands.setspawn")) {
          Player player = (Player) sender;
          World w = player.getWorld();

          w.setSpawnLocation(
              (int) player.getLocation().getX(),
              (int) player.getLocation().getY(),
              (int) player.getLocation().getZ());
          player.sendMessage(ChatColor.GREEN + "Spawn set.");
        } else
          sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");

      } else sender.sendMessage("You must be a player to execute this command.");

    } else
      sender.sendMessage(
          ChatColor.translateAlternateColorCodes(
              '&', plugin.getConfig().getString("messages.command-disabled-message")));
    return true;
  }
}
