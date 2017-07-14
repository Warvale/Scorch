package net.warvale.scorch.enchantments;

import net.warvale.scorch.Main;
import net.warvale.scorch.utils.EnchantUtils;
import net.warvale.scorch.utils.RomanNumerals;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** To make a custom enchant:
 * 1. Make a class in the package enchantments.enchants and have it extend this class.
 * 2. Implement methods, make a constructor (look at other enchants to see what to put in the super)
 * 3. Copy the code for getItem() over from another enchant (they are all the same)
 * 4. @Override one or more of any of these and put in code:
 * offenseEffect (called when player hits an enemy),
 * defenseEffect (called when the player is hit),
 * passiveEffect (called every time the player moves).
 * 5. Go to Main and under loadEnchantments(), put "enchantments.put("ENCHANT NAME ALL CAPS", new EnchantClass());" - (replace EnchantClass() with whatever the class is)
 * 6. Tag @AAces#3889 on discord. (I'll add it to the guis. I dont want to explain all of that right here.)
 */


/**
 * Created by AAces on 7/9/2017
 */
public abstract class CustomEnchantment {
    private String name;
    private String description;
    private int id;
    private int maxLevel;
    private Rarity rarity;
    private ItemsEnum applicableTo;
    private boolean stackable;

    public CustomEnchantment(String name, String description, int id, int maxLevel, Rarity rarity, ItemsEnum applicableTo, boolean stackable){
        this.name = name;
        this.description = description;
        this.id = id;
        this.maxLevel = maxLevel;
        this.rarity = rarity;
        this.applicableTo = applicableTo;
        this.stackable = stackable;
    }

    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public int getId(){
        return this.id;
    }
    public int getMaxLevel(){
        return this.maxLevel;
    }
    public Rarity getRarity(){
        return this.rarity;
    }
    public ItemsEnum getApplicableTo(){
        return this.applicableTo;
    }
    public boolean isStackable(){return this.stackable;}

    public void offenseEffect(LivingEntity user, LivingEntity target, EntityDamageByEntityEvent event){}
    public void defenseEffect(LivingEntity user, LivingEntity damager, EntityDamageByEntityEvent event){}
    public void passiveEffect(Player player, PlayerMoveEvent event){}

    public abstract ItemStack getItem();

    public ItemStack addToItem(ItemStack item, int enchantLevel)
    {
        Validate.notNull(item);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) meta = Bukkit.getServer().getItemFactory().getItemMeta(item.getType());
        List<String> metaLore = meta.getLore() == null ? new ArrayList<>() : meta.getLore();

        // Make sure the enchantment doesn't already exist on the item
        for (Map.Entry<CustomEnchantment, Integer> entry : getEnchantments(item).entrySet())
        {
            if (entry.getKey().getName().equals(getName()))
            {
                if (entry.getValue() < enchantLevel)
                {
                    metaLore.remove(ChatColor.GRAY + getName() + " " + RomanNumerals.numeralOf(entry.getValue()));
                }
                else
                {
                    return item;
                }
            }
        }

        // Add the enchantment
        metaLore.add(0, ChatColor.GRAY + getName() + " " + RomanNumerals.numeralOf(enchantLevel));
        meta.setLore(metaLore);
        String name = EnchantUtils.getName(item);
        if (name != null) meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }


    public ItemStack removeFromItem(ItemStack item)
    {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        if (!meta.hasLore()) return item;
        List<String> metaLore = meta.getLore();

        // Make sure the enchantment doesn't already exist on the item
        for (Map.Entry<CustomEnchantment, Integer> entry : getEnchantments(item).entrySet())
        {
            if (entry.getKey().getName().equals(getName()))
            {
                metaLore.remove(ChatColor.GRAY + getName() + " " + RomanNumerals.numeralOf(entry.getValue()));
            }
        }
        return item;
    }

    public static Map<CustomEnchantment, Integer> getEnchantments(ItemStack item) {
        HashMap<CustomEnchantment, Integer> list = new HashMap<>();
        if (item == null) return list;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return list;
        if (!meta.hasLore()) return list;
        for (String lore : meta.getLore()) {
            String name = EnchantUtils.parseName(lore);
            int level = EnchantUtils.parseLevel(lore);
            if (name == null) continue;
            if (level == 0) continue;
            if (Main.isEnchantmentRegistered(name)) {
                CustomEnchantment enchant = Main.getEnchantment(name);
                if (enchant.isStackable() && list.containsKey(enchant)) {
                    level += list.get(enchant);
                }
                list.put(enchant, level);
            }
        }
        return list;
    }

}
