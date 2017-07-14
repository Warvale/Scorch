package net.warvale.scorch.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Hashtable;

public class EnchantUtils {

    public static String parseName(String lore) {
        if (!lore.contains(" ")) return null;

        String[] pieces = lore.split(" ");

        String name = "";
        for (int i = 0; i < pieces.length - 1; i++) {
            name += pieces[i] + (i < pieces.length - 2 ? " " : "");
        }
        name = ChatColor.stripColor(name);
        return name;
    }

    public static int parseLevel(String lore) {
        if (!lore.contains(" ")) return 0;

        String[] pieces = lore.split(" ");
        return RomanNumerals.getValueOf(pieces[pieces.length - 1]);
    }

    public static String getName(ItemStack item) {
        if (item.hasItemMeta()) {
            if (item.getItemMeta().hasEnchants()) return null;
            if (item.getItemMeta().hasDisplayName()) return null;
        }
        return getName(item.getType());
    }

    public static String getName(Material item) {
        String name = item.name().toLowerCase();
        String[] pieces = name.split("_");
        name = "";
        for (int i = 0; i < pieces.length; i++) {
            name += pieces[i].substring(0, 1).toUpperCase() + pieces[i].substring(1);
            if (i < pieces.length - 1) name += " ";
        }
        if (differentNames.containsKey(name))
            return ChatColor.AQUA + differentNames.get(name);
        return ChatColor.AQUA + name;
    }

    private static final Hashtable<String, String> differentNames = new Hashtable<String, String>(){{
        put("Birch Wood Stairs",  "Wooden Stairs");
        put("Book And Quill",     "Book and Quill");
        put("Cobble Wall",        "Cobblestone Wall");
        put("Command",            "Command Block");
        put("Cooked Beef",        "Steak");
        put("Daylight Detector",  "Daylight Sensor");
        put("Dead Bush",          "Tall Grass");
        put("Diamond Spade",      "Diamond Shovel");
        put("Diode",              "Redstone Repeater");
        put("Exp Bottle",         "Bottle of Enchanting");
        put("Explosive Minecart", "TNT Minecart");
        put("Gold Spade",         "Gold Shovel");
        put("Gold Record",        "Music Disk");
        put("Green Record",       "Music Disk");
        put("Grilled Pork",       "Cooked Porkchop");
        put("Iron Spade",         "Iron Shovel");
        put("Jack O Lantern",     "Jack-O-Lantern");
        put("Jungle Wood Stairs", "Wooden Stairs");
        put("Lapis Block",        "Lapis Lazuli Block");
        put("Lapis Ore",          "Lapis Lazuli Ore");
        put("Leather Chestplate", "Leather Tunic");
        put("Leather Helmet",     "Leather Cap");
        put("Leather Leggings",   "Leather Pants");
        put("Long Grass",         "Tall Grass");
        put("Mycel",              "Mycelium");
        put("Nether Fence",       "Nether Brick Fence");
        put("Nether Warts",       "Nether Wart");
        put("Pork",               "Raw Porkchop");
        put("Record 3",           "Music Disk");
        put("Record 4",           "Music Disk");
        put("Record 5",           "Music Disk");
        put("Record 6",           "Music Disk");
        put("Record 7",           "Music Disk");
        put("Record 8",           "Music Disk");
        put("Record 9",           "Music Disk");
        put("Record 10",          "Music Disk");
        put("Record 11",          "Music Disk");
        put("Record 12",          "Music Disk");
        put("Red Rose",           "Rose");
        put("Smooth Stairs",      "Stone Brick Stairs");
        put("Speckled Melon",     "Glistering Melon");
        put("Sprue Wood Stairs",  "Wooden Stairs");
        put("Stone Plate",        "Stone Pressure Plate");
        put("Stone Spade",        "Stone Shovel");
        put("Sulphur",            "Gunpowder");
        put("Thin Glass",         "Glass Pane");
        put("Tnt",                "TNT");
        put("Water Lily",         "Lily Pad");
        put("Wood",               "Wooden Plank");
        put("Wood Axe",           "Wooden Axe");
        put("Wood Button",        "Button");
        put("Wood Double Step",   "Wooden Slab");
        put("Wood Hoe",           "Wooden Hoe");
        put("Wood Pickaxe",       "Wooden Pickaxe");
        put("Wood Plate",         "Wooden Pressure Plate");
        put("Wood Spade",         "Wooden Shovel");
        put("Wood Stairs",        "Wooden Stairs");
        put("Wood Sword",         "Wooden Sword");
        put("Yellow Flower",      "Dandelion");
    }};
}