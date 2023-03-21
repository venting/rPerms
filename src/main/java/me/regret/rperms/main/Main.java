package me.regret.rperms.main;

import me.regret.rperms.commands.grantCommand;
import me.regret.rperms.commands.listCommand;
import me.regret.rperms.commands.rankCommand;
import me.regret.rperms.listeners.connectListener;
import me.regret.rperms.listeners.chatListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);

        getCommand("Rank").setExecutor(new rankCommand());
        getCommand("Grant").setExecutor(new grantCommand(this));
        getCommand("List").setExecutor(new listCommand(this));

        getServer().getPluginManager().registerEvents(new grantCommand(this), this);
        getServer().getPluginManager().registerEvents(new chatListener(this), this);
        getServer().getPluginManager().registerEvents(new connectListener(this), this);
    }
}
