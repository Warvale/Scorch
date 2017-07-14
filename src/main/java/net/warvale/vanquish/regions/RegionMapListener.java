package net.warvale.vanquish.regions;

import net.warvale.vanquish.Main;
import net.warvale.vanquish.utils.Broadcast;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Map;

/**
 * Created by Ron on 14/7/2017.
 */

public class RegionMapListener implements Listener {
    public void onPlayerMove(PlayerMoveEvent e) {
        Map cpl = RegionMapGen.getPlayerLocations();
        cpl.put(e.getPlayer().getUniqueId(), RegionMapGen.getMap()[(int)e.getTo().getX()][(int)e.getTo().getZ()]);
        RegionMapGen.setPlayerLocations(cpl);

    }
    public void onBlockPlace(BlockPlaceEvent e) {

        Map cpl = RegionMapGen.getPlayerLocations();
        cpl.put(e.getPlayer().getUniqueId(), RegionMapGen.getMap()[(int)e.getPlayer().getLocation().getX()][(int)e.getPlayer().getLocation().getZ()]);
        RegionMapGen.setPlayerLocations(cpl);

        if (cpl.get(e.getPlayer().getUniqueId()).equals("lava")) {
            Broadcast.toPlayer(e.getPlayer(), Broadcast.BroadcastType.FAILURE, "You cannot build in Warvale's territory.");
            e.setCancelled(true);
        } else if (cpl.get(e.getPlayer().getUniqueId()).equals(Main.get().getConfig().getString("Player-Data."+e.getPlayer().getUniqueId()+"Guild-Name"))) {
            e.setCancelled(false);
        } else {
            Broadcast.toPlayer(e.getPlayer(), Broadcast.BroadcastType.FAILURE, "You cannot build in "+cpl.get(e.getPlayer().getUniqueId())+"'s territory.");
            e.setCancelled(true);
        }
    }
    public void onBlockBreak(BlockBreakEvent e) {
        Map cpl = RegionMapGen.getPlayerLocations();
        cpl.put(e.getPlayer().getUniqueId(), RegionMapGen.getMap()[(int)e.getPlayer().getLocation().getX()][(int)e.getPlayer().getLocation().getZ()]);
        RegionMapGen.setPlayerLocations(cpl);

        if (cpl.get(e.getPlayer().getUniqueId()).equals("lava")) {
            Broadcast.toPlayer(e.getPlayer(), Broadcast.BroadcastType.FAILURE, "You cannot break blocks in Warvale's territory.");
            e.setCancelled(true);
        } else if (cpl.get(e.getPlayer().getUniqueId()).equals(Main.get().getConfig().getString("Player-Data."+e.getPlayer().getUniqueId()+"Guild-Name"))) {
            e.setCancelled(false);
        } else {
            Broadcast.toPlayer(e.getPlayer(), Broadcast.BroadcastType.FAILURE, "You cannot break blocks in "+cpl.get(e.getPlayer().getUniqueId())+"'s territory.");
            e.setCancelled(true);
        }
    }


    public void onFrameBreak(HangingBreakByEntityEvent e) {
        if (e.getRemover() instanceof Player) {
            return;
        }
        Player p = (Player) e.getRemover();

        Map cpl = RegionMapGen.getPlayerLocations();
        cpl.put(p.getUniqueId(), RegionMapGen.getMap()[(int)p.getLocation().getX()][(int)p.getLocation().getZ()]);
        RegionMapGen.setPlayerLocations(cpl);

        if (cpl.get(p.getUniqueId()).equals("lava")) {
            Broadcast.toPlayer(p.getPlayer(), Broadcast.BroadcastType.FAILURE, "You cannot break item frames in Warvale's territory.");
            e.setCancelled(true);
        } else if (cpl.get(p.getUniqueId()).equals(Main.get().getConfig().getString("Player-Data."+p.getUniqueId()+"Guild-Name"))) {
            e.setCancelled(false);
        } else {
            Broadcast.toPlayer(p, Broadcast.BroadcastType.FAILURE, "You cannot break item frames in "+cpl.get(p.getUniqueId())+"'s territory.");
            e.setCancelled(true);
        }
    }
    public void onFramePlace(HangingPlaceEvent e) {

        Player p = e.getPlayer();

        Map cpl = RegionMapGen.getPlayerLocations();
        cpl.put(p.getUniqueId(), RegionMapGen.getMap()[(int)p.getLocation().getX()][(int)p.getLocation().getZ()]);
        RegionMapGen.setPlayerLocations(cpl);

        if (cpl.get(p.getUniqueId()).equals("lava")) {
            Broadcast.toPlayer(p.getPlayer(), Broadcast.BroadcastType.FAILURE, "You cannot place item frames in Warvale's territory.");
            e.setCancelled(true);
        } else if (cpl.get(p.getUniqueId()).equals(Main.get().getConfig().getString("Player-Data."+p.getUniqueId()+"Guild-Name"))) {
            e.setCancelled(false);
        } else {
            Broadcast.toPlayer(p, Broadcast.BroadcastType.FAILURE, "You cannot place item frames in "+cpl.get(p.getUniqueId())+"'s territory.");
            e.setCancelled(true);
        }
    }
}
