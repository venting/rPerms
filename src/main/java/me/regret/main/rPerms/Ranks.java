package me.regret.main.rPerms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class Ranks {

    public static String getRank(Player p){
        return main.plugin.getConfig().getString("Players." + p.getUniqueId().toString() + ".rank");
    }
    public static boolean isRank(String rank){
      if(main.plugin.getConfig().getString("Ranks." + rank) != null){
            return true;
        }
        return false;
    }
    public static boolean hasRank(Player p){
        if(main.plugin.getConfig().getStringList("Players." + p + ".rank") != null){
            return true;
        }
        return false;
    }
    public static void setRank(Player p, String rank){
        main.plugin.getConfig().set("Players." + p.getUniqueId().toString() + ".rank", rank);
        main.plugin.saveConfig();
    }
    public static void createRank(String rank, String prefix){
        if(!isRank(rank)){
            main.plugin.getConfig().set("Ranks." + rank + ".permissions", "");
            main.plugin.getConfig().set("Ranks." + rank + ".prefix", prefix);
            List<String> tags = main.plugin.getConfig().getStringList("Tag.");
            tags.add(rank);
            main.plugin.saveConfig();
        }
    }
    public static void deleteRank(String rank){
        if(isRank(rank)){
            main.plugin.getConfig().set("Ranks." + rank, null);
            for (String players : main.plugin.getConfig().getConfigurationSection("Players").getKeys(false)) {
                Player player = Bukkit.getPlayer(players);
                if(Ranks.hasRank(player)){
                    String pRank = Ranks.getRank(player);
                    if(pRank == rank){
                        main.plugin.getConfig().set("Players." + player.getUniqueId().toString() + ".rank", " ");
                        main.plugin.saveConfig();
                    }
                }
            }
            main.plugin.saveConfig();
        }
    }
    public static String getRankPrefix(String rank){
        return main.plugin.getConfig().getString("Ranks." + rank + ".prefix");
    }
}
