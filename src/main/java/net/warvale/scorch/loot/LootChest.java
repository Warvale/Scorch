package net.warvale.scorch.loot;

import net.warvale.scorch.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by CommandFox on 7/8/2017.
 */

public class LootChest {
    public LootChest (int biomeId, Location loc) throws IOException, ParseException, NoSuchFieldException {

        Block block = loc.getBlock();
        block.setType(Material.CHEST);

        BlockState state = block.getState();
        Chest chest = (Chest) state;
        Inventory chestInventory = chest.getBlockInventory();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new InputStreamReader(Main.get().getResource("loot.json")));

        JSONObject biome = (JSONObject) ((JSONArray) ((JSONObject) obj).get("biomes")).get(biomeId);
        JSONArray biome_items = (JSONArray) biome.get("items");
        JSONArray global_items = (JSONArray) ((JSONObject) obj).get("global");

        int biomeAmount = ThreadLocalRandom.current().nextInt(1, 3);
        ThreadLocalRandom biomeItem = ThreadLocalRandom.current();

        Set<Integer> generatedBiome = new LinkedHashSet<Integer>();
        while (generatedBiome.size() < biomeAmount) {
            Integer next = biomeItem.nextInt(0, biome_items.size());
            int chance = ((Long) ((JSONObject) biome_items.get(next)).get("chance")).intValue();
            if (ThreadLocalRandom.current().nextInt(1, 100) < chance) {
                generatedBiome.add(next);
            }
        }

        int globalAmount = ThreadLocalRandom.current().nextInt(0, 2);
        ThreadLocalRandom globalItem = ThreadLocalRandom.current();

        Set<Integer> generatedGlobal = new LinkedHashSet<Integer>();
        while (generatedGlobal.size() < globalAmount) {
            Integer next = globalItem.nextInt(0, global_items.size());
            int chance = ((Long) ((JSONObject) global_items.get(next)).get("chance")).intValue();
            if (ThreadLocalRandom.current().nextInt(1, 100) < chance) {
                generatedGlobal.add(next);
            }
        }

        ThreadLocalRandom chestSlot = ThreadLocalRandom.current();

        Set<Integer> chestSlots = new LinkedHashSet<Integer>();
        while (chestSlots.size() < globalAmount + biomeAmount) {
            Integer next = chestSlot.nextInt(1, 26);
            chestSlots.add(next);
        }

        Integer[] generatedGlobalArr = new Integer[generatedGlobal.size()];
        generatedGlobal.toArray(generatedGlobalArr);

        Integer[] generatedBiomeArr = new Integer[generatedBiome.size()];
        generatedBiome.toArray(generatedBiomeArr);

        Integer[] chestSlotsArr = new Integer[chestSlots.size()];
        chestSlots.toArray(chestSlotsArr);

        for (int i = 0; i < chestSlots.size(); i++) {
            if (i < globalAmount) {
                JSONObject cur = (JSONObject) global_items.get(generatedGlobalArr[i]);

                int countMin = ((Long) cur.get("countMin")).intValue();
                int countMax = ((Long) cur.get("countMax")).intValue();
                int amount = ThreadLocalRandom.current().nextInt(countMin, countMax);

                String type = (String) cur.get("type");

                ItemStack is = new ItemStack(Material.valueOf(type.toUpperCase()), amount);

                if(cur.containsKey("names")) {
                    JSONArray names = (JSONArray) cur.get("names");
                    int namesLen = names.size();
                    int name = ThreadLocalRandom.current().nextInt(0, namesLen);
                    String curName = (String) names.get(name);

                    ItemMeta meta = is.getItemMeta();
                    meta.setDisplayName(curName);
                    is.setItemMeta(meta);
                }

                chestInventory.setItem(chestSlotsArr[i], is);
            } else {
                JSONObject cur = (JSONObject) biome_items.get(generatedBiomeArr[i - globalAmount]);

                int countMin = ((Long) cur.get("countMin")).intValue();
                int countMax = ((Long) cur.get("countMax")).intValue();
                int amount = ThreadLocalRandom.current().nextInt(countMin, countMax);

                String type = (String) cur.get("type");

                ItemStack is = new ItemStack(Material.valueOf(type.toUpperCase()), amount);

                if(cur.containsKey("names")) {
                    JSONArray names = (JSONArray) cur.get("names");
                    int namesLen = names.size();
                    int name = ThreadLocalRandom.current().nextInt(0, namesLen);
                    String curName = (String) names.get(name);

                    ItemMeta meta = is.getItemMeta();
                    meta.setDisplayName(curName);
                    is.setItemMeta(meta);
                }

                chestInventory.setItem(chestSlotsArr[i], is);
            }
        }

        chest.update();
    }
}
