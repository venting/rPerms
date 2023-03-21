package me.regret.rperms.commands;

import me.regret.rperms.main.Main;
import me.regret.rperms.main.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class grantCommand implements CommandExecutor, Listener {

    private final Main plugin;
    private Player t;
    Ranks ranks;

    public grantCommand(Main plugin) {
        this.plugin = plugin;
        t = null;
    }
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
                            grantGUI(p, target);
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

    public void grantGUI(Player p, Player target){
        t = target;

        Inventory inv = Bukkit.createInventory(null, 18, "§dRank for " + t.getName());
        if(plugin.getConfig().getConfigurationSection("Ranks") != null){
            for (String names : plugin.getConfig().getConfigurationSection("Ranks").getKeys(false)) {
                String prefix = ranks.getRankPrefix(names);

                ItemStack rank = new ItemStack(Material.EMERALD);
                ItemMeta ranks = rank.getItemMeta();
                ranks.setDisplayName(names);
                ArrayList<String> rLore = new ArrayList<String>();
                rLore.add("§f - " + ChatColor.translateAlternateColorCodes('&', prefix) + " " + p.getName());
                ranks.setLore(rLore);
                rank.setItemMeta(ranks);
                inv.addItem(rank);

                p.openInventory(inv);
            }
        } else {
            p.sendMessage("§4There are no ranks available.");
        }
    }

    @EventHandler
    public void grantGUI(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() != null) {
            if (e.getInventory().getTitle().contains("§dRank for ")) {
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        String rank = e.getCurrentItem().getItemMeta().getDisplayName();
                        ranks.setRank(t, rank);
                        p.closeInventory();
                        e.setCancelled(true);

                        p.sendMessage("§dSuccessfully set §f" + t.getName() + "'s §drank to §f" +
                                ChatColor.translateAlternateColorCodes('&', rank + "§d."));
                    }
                }
            }
        }
    }
}
