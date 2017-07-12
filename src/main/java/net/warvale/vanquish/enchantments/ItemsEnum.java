package main.java.net.warvale.vanquish.enchantments;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AAces on 7/9/2017
 */
public enum ItemsEnum {
    MELEE {
        @Override
        public List<Material> getItems() {
            List<Material> list = new ArrayList<>();
            list.addAll(Arrays.asList(Material.DIAMOND_AXE, Material.GOLD_AXE, Material.IRON_AXE, Material.STONE_AXE, Material.WOOD_AXE, Material.DIAMOND_SWORD, Material.GOLD_SWORD, Material.IRON_SWORD, Material.STONE_SWORD, Material.WOOD_SWORD));
            return list;
        }
    },
    BOW{
        @Override
        public List<Material> getItems() {
            List<Material> list = new ArrayList<>();
            list.add(Material.BOW);
            return list;
        }
    },
    ARMOR{
        @Override
        public List<Material> getItems() {
            List<Material> list = new ArrayList<>();
            list.addAll(Arrays.asList(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS));
            return list;
        }
    };

    public abstract List<Material> getItems();
}
