package me.regret.rperms.listeners;

import me.regret.rperms.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class connectListener implements Listener {

    private final Main plugin;
    public connectListener(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!(p.hasPlayedBefore())){
            plugin.getConfig().set("Players." + p.getUniqueId().toString() + ".rank", " ");

        }
    }


}
