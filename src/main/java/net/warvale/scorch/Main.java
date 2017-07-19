package net.warvale.scorch;

import net.warvale.scorch.commands.CommandHandler;
import net.warvale.scorch.crates.Crate;
import net.warvale.scorch.enchantments.CustomEnchantment;
import net.warvale.scorch.enchantments.EnchantListener;
import net.warvale.scorch.enchantments.enchants.*;
import net.warvale.scorch.guilds.FirstJoinGuildStats;
import net.warvale.scorch.listeners.BlockListener;
import net.warvale.scorch.listeners.PlayerListener;
import net.warvale.scorch.physics.ObsidianToLava;
import net.warvale.scorch.regions.RegionMapGen;
import net.warvale.scorch.regions.RegionMapListener;
import net.warvale.scorch.sql.SQLConnection;
import net.warvale.scorch.utils.Broadcast;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;

/**
 * Created by AAces on 7/7/2017
 */
public class Main extends JavaPlugin {
    private static Main instance;
    public static SQLConnection db;
    private static CommandHandler cmds;
    private static Hashtable<String, CustomEnchantment> enchantments = new Hashtable<>();
    private static Location crateChestLocation = new Location(Bukkit.getWorld("world"), 0, 50, 0);
    private static Location voteCrateChestLocation = new Location(Bukkit.getWorld("world"), 0, 50, 0);
    private static Location releaseCrateChestLocation = new Location(Bukkit.getWorld("world"), 0, 50, 0);

    public static SQLConnection getDB() {
        return db;
    }


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
        } catch (IOException exx) {exx.printStackTrace();}
    }

    private void initialise(){
        db = new SQLConnection(getConfig().getString("hostname"), getConfig().getInt("port"), getConfig().getString("database"), getConfig().getString("username"), getConfig().getString("password"));
        cmds = new CommandHandler(this);
        cmds.registerCommands();
        getLogger().log(Level.INFO, "Connecting to MySQL...");

        try {
            getDB().openConnection();
        } catch (Exception e) {
            getLogger().log(Level.WARNING, "Could not connect to MySQL", e);
        }

        loadEnchantments();

        Crate.setup();

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
    public static ArrayList<CustomEnchantment> getEnchantments(){
        ArrayList<CustomEnchantment> enchants = new ArrayList<>();
        enchants.addAll(enchantments.values());
        return enchants;
    }
    public static Main get(){
        return instance;
    }

    public static Location getCrateChestLocation(){return crateChestLocation;}

    public static Location getVoteCrateChestLocation(){return voteCrateChestLocation;}

    public static Location getReleaseCrateChestLocation() {
        return releaseCrateChestLocation;
    }

}
