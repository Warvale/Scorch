package net.warvale.scorch.listeners;

import net.warvale.scorch.Main;
import net.warvale.scorch.crates.Crate;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by AAces on 7/7/2017
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            Block block = event.getClickedBlock();
            if(block.getLocation().equals(Main.getCrateChestLocation())){
                event.setCancelled(true);
                ItemStack item = event.getItem();
                if (Crate.isCrateKey(item)){
                    Crate.openCrate(event.getPlayer());
                } else {
                    event.getPlayer().sendMessage(ChatColor.RED + "You must be holding a crate key to open a crate!");
                }
            } else if(block.getLocation().equals(Main.getVoteCrateChestLocation())){
                event.setCancelled(true);
                ItemStack item = event.getItem();
                if (Crate.isVoteCrateKey(item)){
                    Crate.openVoteCrate(event.getPlayer());
                } else {
                    event.getPlayer().sendMessage(ChatColor.RED + "You must be holding a vote crate key to open a vote crate!");
                }
            } else if(block.getLocation().equals(Main.getReleaseCrateChestLocation())){
                event.setCancelled(true);
                ItemStack item = event.getItem();
                if (Crate.isReleaseCrateKey(item)){
                    Crate.openReleaseCrate(event.getPlayer());
                } else {
                    event.getPlayer().sendMessage(ChatColor.RED + "You must be holding a release crate key to open a release crate!");
                }
            }
        }
    }

}
