package net.warvale.scorch;

import net.warvale.scorch.commands.CommandHandler;
import net.warvale.scorch.enchantments.CustomEnchantment;
import net.warvale.scorch.enchantments.EnchantListener;
import net.warvale.scorch.enchantments.enchants.*;
import net.warvale.scorch.guilds.FirstJoinGuildStats;
import net.warvale.scorch.listeners.BlockListener;
import net.warvale.scorch.listeners.PlayerListener;
import net.warvale.scorch.physics.ObsidianToLava;
import net.warvale.scorch.regions.RegionMapGen;
import net.warvale.scorch.regions.RegionMapListener;
import net.warvale.scorch.utils.Broadcast;
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
            getLogger().log(Level.WARNING, "Failed to enable Scorch!");
        }
        //Register Events Here
        Bukkit.getPluginManager().registerEvents(new RegionMapListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new FirstJoinGuildStats(this), this);
        Bukkit.getPluginManager().registerEvents(new EnchantListener(), this);

    }
    @Override
    public void onDisable(){
        enchantments.clear();
    try {
        RegionMapGen.saveMapFile(getConfig().getString("RegionMapPath"));
    } catch (IOException ex) {ex.printStackTrace();}
    }

    private void initialise(){

        cmds = new CommandHandler(this);
        cmds.registerCommands();

        loadEnchantments();

        ObsidianToLava.setDelay(5);
        new ObsidianToLava().runTaskTimer(this, 0, 20);

        try {
            RegionMapGen.setMap(RegionMapGen.getRegionMapFromFile(getConfig().getString("RegionMapPath")));
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
