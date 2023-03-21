package me.regret.rperms.commands;

import me.regret.rperms.main.Main;
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
    public String t;

    public grantCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(p.isOp()){
            if(args.length == 0){
                p.sendMessage("§dGrant §f<player>");
            }
            if(args.length == 1){
                String ta = args[0];
                if(ta != null){
                    if(Bukkit.getServer().getPlayer(ta) != null){
                        Player target = Bukkit.getServer().getPlayer(ta);
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
        t = target.getName();
        Inventory inv = Bukkit.createInventory(null, 18, "§dRank for " + t);
        if(plugin.getConfig().getConfigurationSection("Ranks") != null){
            for (String names : plugin.getConfig().getConfigurationSection("Ranks").getKeys(false)) {
                String prefix = plugin.getConfig().getString("Ranks." + names + ".prefix");

                ItemStack rank = new ItemStack(Material.EMERALD);
                ItemMeta ranks = rank.getItemMeta();
                ranks.setDisplayName(names);
                ArrayList<String> rLore = new ArrayList<String>();
                rLore.add("§f - " + ChatColor.translateAlternateColorCodes('&', prefix) + " " + p.getName());
                ranks.setLore(rLore);
                rank.setItemMeta(ranks);
                inv.addItem(rank);

                p.openInventory(inv);
                t = target.getName();
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
                        plugin.getConfig().set("Players." + p.getUniqueId().toString() + ".rank", rank);
                        plugin.saveConfig();
                        p.closeInventory();
                        e.setCancelled(true);

                        p.sendMessage("§dSuccessfully set §f" + t + "'s §drank to §f" + rank);
                    }
                }
            }
        }
    }
}
