package me.regret.main.rPerms.listeners.gui;

import me.regret.main.rPerms.Ranks;
import me.regret.main.rPerms.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class grantGUIListener implements Listener {

    private static Player t;

    public static void grantGUI(Player p, Player target){
        t = target;

        Inventory inv = Bukkit.createInventory(null, 18, "§dRank for " + t.getName());
        if(main.plugin.getConfig().getConfigurationSection("Ranks") != null){
            for (String names : main.plugin.getConfig().getConfigurationSection("Ranks").getKeys(false)) {
                String prefix = Ranks.getRankPrefix(names);

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
                        Ranks.setRank(t, rank);
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
