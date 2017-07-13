package net.warvale.vanquish.listeners;

import net.warvale.vanquish.Main;
import net.warvale.vanquish.regions.RegionMapGen;
import net.warvale.vanquish.utils.Broadcast;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by AAces on 7/7/2017
 */
public class PlayerListener implements Listener {
    @EventHandler
    public void RegionPlayerMove(BlockPlaceEvent event) {
    if (!RegionMapGen.getMap()[(int)event.getBlockPlaced().getX()][(int)event.getBlockPlaced().getZ()].equals("lava") &&
            !RegionMapGen.getMap()[(int)event.getBlockPlaced().getLocation().getX()][(int)event.getBlockPlaced().getLocation().getZ()].equals(Main.get().getConfig().getString("Player-Data." + event.getPlayer().getUniqueId().toString() + ".GuildName"))) {
        Broadcast.toPlayer(event.getPlayer(), Broadcast.BroadcastType.FAILURE, "You cannot build here!");
        event.setCancelled(true);
    }

    }
}
