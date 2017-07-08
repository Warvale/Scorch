package net.warvale.vanquish;

import net.warvale.vanquish.guilds.FirstJoinGuildStats;
import net.warvale.vanquish.commands.CommandHandler;
import net.warvale.vanquish.listeners.BlockListener;
import net.warvale.vanquish.listeners.PlayerListener;
import net.warvale.vanquish.physics.ObsidianToLava;
import net.warvale.vanquish.utils.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by AAces on 7/7/2017
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static CommandHandler cmds;
    @Override
    public void onEnable(){
        
    	instance = this;
        
    	try {
            initialise();
            Broadcast.toConsole(Level.INFO, "Successfully enabled!");
        } catch(Exception ex) {
            ex.printStackTrace();
            getLogger().log(Level.WARNING, "Failed to enable LobbyCore!");
        }
        //Register Events Here
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new FirstJoinGuildStats(this), this);

    }
    @Override
    public void onDisable(){
        getLogger().info("Disabled Vanquish");
    }

    private void initialise(){

        cmds = new CommandHandler(this);
        cmds.registerCommands();

        ObsidianToLava.setDelay(5);
        new ObsidianToLava().runTaskTimer(this, 0, 20);

    }

    public static Main get(){
        return instance;
    }

}
