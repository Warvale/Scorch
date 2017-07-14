package net.warvale.scorch.enchantments.enchants;

import net.warvale.scorch.enchantments.CustomEnchantment;
import net.warvale.scorch.enchantments.ItemsEnum;
import net.warvale.scorch.enchantments.Rarity;
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
public class LifeSteal extends CustomEnchantment {

    public LifeSteal(){
        super("Life Steal", "Gain 25% of damage dealt back as health as you attack!", 3, 1, Rarity.EPIC, ItemsEnum.MELEE, false);
    }

    @Override
    public void offenseEffect(LivingEntity user, LivingEntity target, EntityDamageByEntityEvent event){
        user.setHealth(user.getHealth() + (event.getDamage()/4));
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
