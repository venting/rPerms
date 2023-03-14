package me.regret.main.rPerms.commands;

import me.regret.main.rPerms.listeners.gui.grantGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class grantCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(p.isOp()){
            if(args.length == 0){
                p.sendMessage("§dGrant §f<player>");
            }
            if(args.length == 1){
                String t = args[0];
                if(t != null){
                    if(Bukkit.getServer().getPlayer(t) != null){
                        Player target = Bukkit.getServer().getPlayer(t);
                        if(target.isOnline()){
                            grantGUIListener.grantGUI(p, target);
                        } else {
                            p.sendMessage("§cThis player is not online.");
                        }
                    } else {
                        p.sendMessage("§cNot a player.");
                    }
                } else {
                    p.sendMessage("§cNot a player.");
                }
            }
        }
        return true;
    }
}
