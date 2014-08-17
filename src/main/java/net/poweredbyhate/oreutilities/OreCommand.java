package net.poweredbyhate.oreutilities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class OreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("vdrop.use")) {
            if (OreUtilities.pluginEnabled) {
                OreUtilities.pluginEnabled = false;
                sender.sendMessage(ChatColor.RED + "OreUtilities Disabled");
            }   else {
                OreUtilities.pluginEnabled = true;
                sender.sendMessage(ChatColor.RED + "OreUtilities Enabled");
            }
        }
        return false;
    }
}
