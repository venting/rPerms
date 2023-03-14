package me.regret.main.rPerms.listeners;

import me.regret.main.rPerms.Ranks;
import me.regret.main.rPerms.main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        String rank = Ranks.getRank(p);
        String prefix = Ranks.getRankPrefix(rank);

        if(main.plugin.getConfig().getConfigurationSection("Ranks") != null){
            if(main.plugin.getConfig().getStringList("Players." + p.getUniqueId().toString() + ".rank") != null){
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
