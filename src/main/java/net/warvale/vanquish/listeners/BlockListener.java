package main.java.net.warvale.vanquish.listeners;

import main.java.net.warvale.vanquish.physics.ObsidianToLava;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

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
}
