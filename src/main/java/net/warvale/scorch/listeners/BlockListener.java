package net.warvale.scorch.listeners;

import net.warvale.scorch.physics.ObsidianToLava;
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
        if(event.getBlockPlaced().getType().equals(Material.END_CRYSTAL)){
            event.setCancelled(true);
        }
    }
}
