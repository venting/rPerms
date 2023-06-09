package me.regret.rperms.commands;

import me.regret.rperms.main.Main;
import me.regret.rperms.main.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class listCommand implements CommandExecutor {

    private final Main plugin;
    private Ranks r;

    public listCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (sender == p) {
            ConfigurationSection rankNames = plugin.getConfig().getConfigurationSection("Ranks");
            if (rankNames != null) {
                StringBuilder b = new StringBuilder();
                for (String s : rankNames.getKeys(false)) {
                    String prefix = r.getRankPrefix(s);
                    b.append(prefix);
                    b.append("§f, ");
                }

                String ranks = b.toString().substring(0, b.toString().length() - 2);

                StringBuilder pl = new StringBuilder();
                for (Player o : Bukkit.getServer().getOnlinePlayers()) {
                    String rank = r.getRank(o);
                    String prefix = r.getRankPrefix(rank);
                    if(prefix == null){
                        pl.append(o.getName());
                        pl.append("§f, ");
                    } else {
                        pl.append(prefix + o.getName());
                        pl.append("§f, ");
                    }
                }
                String players = pl.toString().substring(0, pl.toString().length() - 2);
                int online = 0;
                for (Player op : Bukkit.getServer().getOnlinePlayers()) {
                    online++;
                }

                p.sendMessage(ranks.replaceAll("&", "§"));
                p.sendMessage(" ");
                p.sendMessage("§7[§f" + online + "/" + Bukkit.getServer().getMaxPlayers() + "§7] §f" + players.replaceAll("&", "§"));
            } else {
                StringBuilder pl = new StringBuilder();
                for (Player o : Bukkit.getServer().getOnlinePlayers()) {
                        pl.append(o.getName());
                        pl.append("§f, ");
                }
                String players = pl.toString().substring(0, pl.toString().length() - 2);
                int online = 0;
                for (Player op : Bukkit.getServer().getOnlinePlayers()) {
                    online++;
                }

                p.sendMessage(" ");
                p.sendMessage("§7[§f" + online + "/" + Bukkit.getServer().getMaxPlayers() + "§7] §f" + players.replaceAll("&", "§"));
            }
        }
        return true;
    }
}
