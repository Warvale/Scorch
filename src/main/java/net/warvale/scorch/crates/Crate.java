package net.warvale.scorch.crates;

import net.warvale.scorch.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by AAces on 7/18/2017
 */
public class Crate {

    private static boolean openingCrate = false;
    private static boolean openingVoteCrate = false;
    private static boolean openingReleaseCrate = false;

    public static ItemStack getKey(){
        ItemStack key = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta keyMeta = key.getItemMeta();
        keyMeta.setDisplayName(ChatColor.GOLD + "Crate Key");
        List<String> lore = keyMeta.getLore();
        lore.clear();
        lore.add("Bring this to the crate at spawn to open!");
        keyMeta.setLore(lore);
        key.setItemMeta(keyMeta);
        return key;
    }

    public static ItemStack getVoteKey(){
        ItemStack key = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta keyMeta = key.getItemMeta();
        keyMeta.setDisplayName(ChatColor.GOLD + "Vote Crate Key");
        List<String> lore = keyMeta.getLore();
        lore.clear();
        lore.add("Bring this to the vote crate at spawn to open!");
        keyMeta.setLore(lore);
        key.setItemMeta(keyMeta);
        return key;
    }

    public static ItemStack getReleaseKey(){
        ItemStack key = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta keyMeta = key.getItemMeta();
        keyMeta.setDisplayName(ChatColor.GOLD + "Release Crate Key");
        List<String> lore = keyMeta.getLore();
        lore.clear();
        lore.add("Bring this to the release crate at spawn to open!");
        keyMeta.setLore(lore);
        key.setItemMeta(keyMeta);
        return key;
    }

    public static void setup(){
        Main.getCrateChestLocation().getBlock().setType(Material.CHEST);
        Main.getVoteCrateChestLocation().getBlock().setType(Material.CHEST);
        Main.getReleaseCrateChestLocation().getBlock().setType(Material.CHEST);
        openingCrate = false;
        openingVoteCrate = false;
        openingReleaseCrate = false;
    }

    public static boolean isCrateKey(ItemStack item) {
        return item.getItemMeta().getLore().contains("Bring this to the crate at spawn to open!");
    }

    public static boolean isVoteCrateKey(ItemStack item) {
        return item.getItemMeta().getLore().contains("Bring this to the vote crate at spawn to open!");
    }

    public static boolean isReleaseCrateKey(ItemStack item) {
        return item.getItemMeta().getLore().contains("Bring this to the release crate at spawn to open!");
    }

    public static void openCrate(Player player) {
        if (openingCrate){
            player.sendMessage(ChatColor.RED + "This crate is in use at the moment!");
        } else {
            openingCrate = true;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255, false, false), true);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playNote(Main.getCrateChestLocation(), (byte) 1, (byte) 1);
            }
            int rand = ThreadLocalRandom.current().nextInt(0, 1001);
            CrateType crate = rand == 1 ? CrateType.MYTHICAL : rand >= 2 && rand <= 60 ? CrateType.LEGENDARY : rand >= 61 && rand <= 260 ? CrateType.RARE : rand >= 261 ? CrateType.COMMON : null;
            if(crate == null){
                return;
            }

            ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = itemMeta.getLore();
            lore.clear();
            lore.add("Bring this to the crate at spawn to open!");
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);

            player.getInventory().removeItem(item);
            int itemNum = ThreadLocalRandom.current().nextInt(0, crate.getItems().size());
            player.getInventory().addItem(crate.getItems().get(itemNum));
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playNote(Main.getCrateChestLocation(), (byte) 1, (byte) 0);
            }
            player.removePotionEffect(PotionEffectType.SLOW);
            openingCrate = false;
        }
    }

    public static void openVoteCrate(Player player) {
        if (openingVoteCrate){
            player.sendMessage(ChatColor.RED + "This crate is in use at the moment!");
        } else {
            openingVoteCrate = true;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255, false, false), true);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playNote(Main.getVoteCrateChestLocation(), (byte) 1, (byte) 1);
            }
            CrateType crate = CrateType.VOTE;

            ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = itemMeta.getLore();
            lore.clear();
            lore.add("Bring this to the vote crate at spawn to open!");
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);

            player.getInventory().removeItem(item);
            int itemNum = ThreadLocalRandom.current().nextInt(0, crate.getItems().size());
            player.getInventory().addItem(crate.getItems().get(itemNum));
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playNote(Main.getVoteCrateChestLocation(), (byte) 1, (byte) 0);
            }
            player.removePotionEffect(PotionEffectType.SLOW);
            openingVoteCrate = false;
        }
    }

    public static void openReleaseCrate(Player player) {
        if (openingReleaseCrate){
            player.sendMessage(ChatColor.RED + "This crate is in use at the moment!");
        } else {
            openingReleaseCrate = true;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 255, false, false), true);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playNote(Main.getReleaseCrateChestLocation(), (byte) 1, (byte) 1);
            }
            CrateType crate = CrateType.RELEASE;

            ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = itemMeta.getLore();
            lore.clear();
            lore.add("Bring this to the release crate at spawn to open!");
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);

            player.getInventory().removeItem(item);
            int itemNum = ThreadLocalRandom.current().nextInt(0, crate.getItems().size());
            player.getInventory().addItem(crate.getItems().get(itemNum));
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playNote(Main.getReleaseCrateChestLocation(), (byte) 1, (byte) 0);
            }
            player.removePotionEffect(PotionEffectType.SLOW);
            openingReleaseCrate = false;
        }
    }
}