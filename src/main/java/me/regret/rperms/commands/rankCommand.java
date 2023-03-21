package me.regret.rperms.commands;

import me.regret.rperms.main.Ranks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rankCommand implements CommandExecutor {

    private Ranks ranks;

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
                    if(ranks.isRank(rank)){
                        ranks.deleteRank(rank);
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
                    if(!ranks.isRank(rank)){
                        ranks.createRank(rank, prefix);
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
