package net.warvale.vanquish.enchantments.enchants;

import net.warvale.vanquish.enchantments.CustomEnchantment;
import net.warvale.vanquish.enchantments.ItemsEnum;
import net.warvale.vanquish.enchantments.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAces on 7/9/2017
 */
public class LastHope extends CustomEnchantment {
    public LastHope(){
        super("Last Hope", "The lower your health, the more damage you do!", 1, 1, Rarity.UNCOMMON, ItemsEnum.MELEE, false);
    }

    @Override
    public void offenseEffect(LivingEntity user, LivingEntity target, EntityDamageByEntityEvent event){
        double health = user.getHealth();
        if (health == 3.0 || health == 2.5) {
            event.setDamage(event.getDamage() + 0.5);
        } else if (health == 2.0 || health == 1.5) {
            event.setDamage(event.getDamage() + 1.0);
        } else if (health == 1.0){
            event.setDamage(event.getDamage() + 1.5);
        } else if (health == 0.5){
            event.setDamage(event.getDamage() + 2.0);
        }

    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.END_CRYSTAL);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + getName());
        lore.add(ChatColor.GRAY + getDescription());
        lore.add(ChatColor.GRAY + "Rarity: " + getRarity().getName());
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(getRarity().getColor() + "Enchanted Crystal");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
}
