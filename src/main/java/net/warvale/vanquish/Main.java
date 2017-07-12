package main.java.net.warvale.vanquish;

import main.java.net.warvale.vanquish.commands.CommandHandler;
import main.java.net.warvale.vanquish.enchantments.CustomEnchantment;
import main.java.net.warvale.vanquish.enchantments.EnchantListener;
import main.java.net.warvale.vanquish.enchantments.enchants.*;
import main.java.net.warvale.vanquish.guilds.FirstJoinGuildStats;
import main.java.net.warvale.vanquish.guilds.regions.RegionMapGen;
import main.java.net.warvale.vanquish.listeners.BlockListener;
import main.java.net.warvale.vanquish.listeners.PlayerListener;
import main.java.net.warvale.vanquish.physics.ObsidianToLava;
import main.java.net.warvale.vanquish.utils.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;

/**
 * Created by AAces on 7/7/2017
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static CommandHandler cmds;
    private static Hashtable<String, CustomEnchantment> enchantments = new Hashtable<>();
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
        Bukkit.getPluginManager().registerEvents(new EnchantListener(), this);

    }
    @Override
    public void onDisable(){
        enchantments.clear();
        getLogger().info("Disabled Vanquish");
    }

    private void initialise(){

        cmds = new CommandHandler(this);
        cmds.registerCommands();

        loadEnchantments();

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

    private static void loadEnchantments() {
        enchantments.put("THUNDERING STRIKE", new ThunderingStrike());
        enchantments.put("LAST HOPE", new LastHope());
        enchantments.put("ENDURANCE", new Endurance());
        enchantments.put("LIFE STEAL", new LifeSteal());
        enchantments.put("EXPERIENCE STEAL", new EXPSteal());
        enchantments.put("LIFE ESSENCE", new LifeEssence());
    }

    public static boolean isEnchantmentRegistered(String enchantment){
        return enchantments.containsKey(enchantment.toUpperCase());
    }
    public static CustomEnchantment getEnchantment(String name) {
        return enchantments.get(name.toUpperCase());
    }

    public static Main get(){
        return instance;
    }

}
