package net.warvale.vanquish;

import net.warvale.vanquish.commands.CommandHandler;
import net.warvale.vanquish.guilds.FirstJoinGuildStats;
import net.warvale.vanquish.guilds.regions.RegionMapGen;
import net.warvale.vanquish.listeners.BlockListener;
import net.warvale.vanquish.listeners.PlayerListener;
import net.warvale.vanquish.physics.ObsidianToLava;
import net.warvale.vanquish.utils.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.logging.Level;

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

    private void initialise() {

        cmds = new CommandHandler(this);
        cmds.registerCommands();

        ObsidianToLava.setDelay(5);
        new ObsidianToLava().runTaskTimer(this, 0, 20);

        try {
            RegionMapGen.setMap(RegionMapGen.getRegionMapFromFile(this.getConfig().get("RegionMapPath").toString()));
        } catch (IOException e) {
            getLogger().warning("There was a IOExpection while loading the RegionMap file.");
            e.printStackTrace();
        } catch (ParseException e) {
            getLogger().warning("There was a ParseExpection while loading the RegionMap file.");
            e.printStackTrace();
        }

    }

    public static Main get(){
        return instance;
    }

}
