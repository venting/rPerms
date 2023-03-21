package me.regret.rperms.listeners;

import me.regret.rperms.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatListener implements Listener {

    private final Main plugin;

    public chatListener(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        if(plugin.getConfig().getConfigurationSection("Ranks") != null){
            if(plugin.getConfig().getString("Players." + p.getUniqueId().toString() + ".rank") != null){
                String rank = plugin.getConfig().getString("Players." + p.getUniqueId().toString() + ".rank");
                String prefix = plugin.getConfig().getString("Ranks." + rank + ".prefix");
                if(rank != null){
                    if(prefix != null){
                        e.setFormat(ChatColor.translateAlternateColorCodes('&', prefix) + " " + p.getName() + "§7: §f" + e.getMessage());
                    } else {
                        e.setFormat(p.getName() + "§7: §f" + e.getMessage());
                    }
                } else {
                    e.setFormat(p.getName() + "§7: §f" + e.getMessage());
                }
            }
        } else {
            e.setFormat(p.getName() + "§7: §f" + e.getMessage());
        }
    }
}
