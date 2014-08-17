package net.poweredbyhate.oreutilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class OreUtilities extends JavaPlugin implements Listener {

    public static boolean pluginEnabled;
    boolean canBroadcast;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        saveConfig();
        pluginEnabled = getConfig().getBoolean("EnableOnStart");
        canBroadcast = getConfig().getBoolean("Broadcast");
        getCommand("vdrop").setExecutor(new OreCommand());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent ev) {
        String blck = ev.getBlock().getType().toString();
        if (blck.contains("_ORE")) {
            checkChances(ev.getPlayer(), blck);
        }
    }

    public void checkChances(Player p, String block) {
        int chance = getConfig().getInt(block+".CHANCE");
        Random cats = new Random();
        if (cats.nextInt() + 1 <= chance) {
            rewardPlayer(p, block);
        }
    }

    public void rewardPlayer(Player p, String block) {
        String item = getConfig().getString(block+".ITEMREWARD");
        String[] theItems = item.split("|");
        int itemID = Integer.parseInt(theItems[0]);
        int itemAmount = Integer.parseInt(theItems[1]);
        ItemStack is = new ItemStack(itemID, itemAmount);
        p.getInventory().addItem(is);
        if (canBroadcast) {
            String x = getConfig().getString(block+".MESSAGE");
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', x).replace("%OreUser", p.getName()));
        }
    }

}
