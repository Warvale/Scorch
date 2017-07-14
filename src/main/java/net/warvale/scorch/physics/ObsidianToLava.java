package net.warvale.scorch.physics;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

/**
 * Created by AAces on 7/7/2017
 */
public class ObsidianToLava extends BukkitRunnable{

    private static int delay = 5;
    private static HashMap<Location, Integer> locations;

    public ObsidianToLava(){locations = new HashMap<>();}

    @Override
    public void run(){
        if(locations.isEmpty()) return;
        for(Location loc : locations.keySet()){
            int x = locations.get(loc) - 1;
            locations.remove(loc);
            locations.put(loc, x);
            if (locations.get(loc) <= 0){
                Bukkit.getWorld("world").playEffect(loc, Effect.SMOKE, 31);
                Bukkit.getWorld("world").getBlockAt(loc).setType(Material.STATIONARY_LAVA);
                locations.remove(loc);
            }
        }
    }

    public static void addObsidianRemovalLocation(final Location loc){
        locations.put(loc, delay);
    }

    public static int getDelay(){
        return delay;
    }
    public static void setDelay(final int newDelay){
        delay = newDelay;
    }
}
