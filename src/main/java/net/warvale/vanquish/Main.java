package main.java.net.warvale.vanquish;

import main.java.net.warvale.vanquish.guilds.FirstJoinGuildStats;
import main.java.net.warvale.vanquish.listeners.BlockListener;
import main.java.net.warvale.vanquish.listeners.PlayerListener;
import main.java.net.warvale.vanquish.physics.ObsidianToLava;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created by AAces on 7/7/2017
 */
public class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable(){
        instance = this;
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new FirstJoinGuildStats(this), this);
        ObsidianToLava.setDelay(5);
        new ObsidianToLava().runTaskTimer(this, 0, 20);
        getLogger().info("Enabled Vanquish");
    }
    @Override
    public void onDisable(){
        getLogger().info("Disabled Vanquish");
    }

    public static Main get(){
        return instance;
    }

}
