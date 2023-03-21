package me.regret.rperms.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Ranks {

    private final Main plugin;
    public Ranks(Main plugin) {
        this.plugin = plugin;
    }

    public String getRank(Player p) {
        return plugin.getConfig().getString("Players." + p.getUniqueId().toString() + ".rank");

    }
    public boolean isRank(String rank){
      if(plugin.getConfig().getString("Ranks." + rank) != null){
            return true;
        }
        return false;
    }
    public boolean hasRank(Player p){
        if(plugin.getConfig().getStringList("Players." + p.getUniqueId().toString() + ".rank") != null){
            return true;
        }
        return false;
    }
    public void setRank(Player p, String rank){
        plugin.getConfig().set("Players." + p.getUniqueId().toString() + ".rank", rank);
        plugin.saveConfig();
    }
    public void createRank(String rank, String prefix){
        if(!isRank(rank)){
            plugin.getConfig().set("Ranks." + rank + ".permissions", "");
            plugin.getConfig().set("Ranks." + rank + ".prefix", prefix);
            plugin.saveConfig();
        }
    }
    public void deleteRank(String rank){
        if(isRank(rank)){
            plugin.getConfig().set("Ranks." + rank, null);
            for (String players : plugin.getConfig().getConfigurationSection("Players").getKeys(false)) {
                Player player = Bukkit.getPlayer(players);
                if(hasRank(player)){
                    String pRank = getRank(player);
                    if(pRank == rank){
                        plugin.getConfig().set("Players." + player.getUniqueId().toString() + ".rank", " ");
                        plugin.saveConfig();
                    }
                }
            }
            plugin.saveConfig();
        }
    }
    public String getRankPrefix(String rank){
        return plugin.getConfig().getString("Ranks." + rank + ".prefix");
    }
}
