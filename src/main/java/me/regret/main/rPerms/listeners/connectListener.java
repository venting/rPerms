package me.regret.main.rPerms.listeners;

import me.regret.main.rPerms.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class connectListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!(p.hasPlayedBefore())){
            main.plugin.getConfig().set("Players." + p.getUniqueId().toString() + ".rank", " ");

        }
    }
}
