package me.regret.rperms.commands;

import me.regret.rperms.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rankCommand implements CommandExecutor {

    private final Main plugin;

    public rankCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (p.isOp()) {
            if(args.length == 0){
                p.sendMessage("§8§m---------------------------");
                p.sendMessage("§d§lRank Help");
                p.sendMessage("§d§l* §f/rank create §d<prefix> <rank>");
                p.sendMessage("§d§l* §f/rank delete §d<rank>");
                p.sendMessage("§8§m---------------------------");
            }
            if(args.length == 1){
                p.sendMessage("§8§m---------------------------");
                p.sendMessage("§d§lRank Help");
                p.sendMessage("§d§l* §f/rank create §d<prefix> <rank>");
                p.sendMessage("§d§l* §f/rank delete §d<rank>");
                p.sendMessage("§8§m---------------------------");
            }
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("delete")){
                    String rank = args[1];
                    if(plugin.getConfig().getString("Ranks." + rank) != null){
                        plugin.getConfig().set("Ranks." + rank, null);
                        for (String players : plugin.getConfig().getConfigurationSection("Players").getKeys(false)) {
                            Player player = Bukkit.getPlayer(players);
                            if(plugin.getConfig().getStringList("Players." + player.getUniqueId().toString() + ".rank") != null){
                                String pRank = plugin.getConfig().getString("Players." + p.getUniqueId().toString() + ".rank");
                                if(pRank == rank){
                                    plugin.getConfig().set("Players." + player.getUniqueId().toString() + ".rank", " ");
                                    plugin.saveConfig();
                                }
                            }
                        }
                        plugin.saveConfig();
                        p.sendMessage("§dSuccessfully deleted §f" + rank + " §drank.");
                    } else {
                        p.sendMessage("§cThis isn't a rank.");
                    }
                } else {
                    p.sendMessage("§8§m---------------------------");
                    p.sendMessage("§d§lRank Help");
                    p.sendMessage("§d§l* §f/rank create §d<prefix> <rank>");
                    p.sendMessage("§d§l* §f/rank delete §d<rank>");
                    p.sendMessage("§8§m---------------------------");
                }
            }
            if(args.length == 3){
                if(args[0].equalsIgnoreCase("create")){
                    String prefix = args[1];
                    String rank = args[2];
                    if(plugin.getConfig().getString("Ranks." + rank) == null){
                        plugin.getConfig().set("Ranks." + rank + ".permissions", "");
                        plugin.getConfig().set("Ranks." + rank + ".prefix", prefix);
                        plugin.saveConfig();
                        p.sendMessage("§dSuccessfully created " + rank + " §drank.");
                    } else {
                        p.sendMessage("§cAlready a rank.");
                    }
                }
            }
            if(args.length == 4){
            }
        }
        return true;
    }
}
