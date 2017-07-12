package net.warvale.vanquish.listeners;

import net.warvale.vanquish.physics.ObsidianToLava;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by AAces on 7/7/2017
 */
public class BlockListener implements Listener {
    @EventHandler
    public void onBlockChange(BlockFromToEvent event){
        if((event.getBlock().getType().equals(Material.LAVA) || event.getBlock().getType().equals(Material.STATIONARY_LAVA)) && event.getToBlock().getType().equals(Material.OBSIDIAN)){
            ObsidianToLava.addObsidianRemovalLocation(event.getToBlock().getLocation());
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        int x = event.getBlockPlaced().getX();
        int i = event.getBlockPlaced().getY();
        int z = event.getBlockPlaced().getZ();
        if(event.getBlockPlaced().getType().equals(Material.END_CRYSTAL)){
            event.setCancelled(true);
            return;
        }
        for (int y = i; y > 0; y--){
            if (Bukkit.getWorld("world").getBlockAt(x, y, z).getType().equals(Material.LAVA) || Bukkit.getWorld("world").getBlockAt(x, y, z).getType().equals(Material.STATIONARY_LAVA)){
                event.setCancelled(true);
                return;
            } else if (!(Bukkit.getWorld("world").getBlockAt(x, y, z).getType().equals(Material.AIR) || Bukkit.getWorld("world").getBlockAt(x, y, z).getType().equals(Material.OBSIDIAN))){
                break;
            }
        }
    }
}
