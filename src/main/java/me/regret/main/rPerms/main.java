package me.regret.main.rPerms;

import me.regret.main.rPerms.commands.grantCommand;
import me.regret.main.rPerms.commands.listCommand;
import me.regret.main.rPerms.commands.rankCommand;
import me.regret.main.rPerms.listeners.chatListener;
import me.regret.main.rPerms.listeners.connectListener;
import me.regret.main.rPerms.listeners.gui.grantGUIListener;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    public static main plugin;

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults(true);

        getCommand("Rank").setExecutor(new rankCommand());
        getCommand("Grant").setExecutor(new grantCommand());
        getCommand("List").setExecutor(new listCommand());

        getServer().getPluginManager().registerEvents(new chatListener(), this);
        getServer().getPluginManager().registerEvents(new connectListener(), this);
        getServer().getPluginManager().registerEvents(new grantGUIListener(), this);
    }
}
