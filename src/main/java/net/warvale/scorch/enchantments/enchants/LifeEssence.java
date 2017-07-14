package net.warvale.scorch.enchantments.enchants;

import net.warvale.scorch.enchantments.CustomEnchantment;
import net.warvale.scorch.enchantments.ItemsEnum;
import net.warvale.scorch.enchantments.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAces on 7/9/2017
 */
public class LifeEssence extends CustomEnchantment {

    public LifeEssence(){
        super("Life Essence", "Constantly regenerate health!", 5, 1, Rarity.COMMON, ItemsEnum.ARMOR, false);
    }

    @Override
    public void passiveEffect(Player player, PlayerMoveEvent event){
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1, 0, false, false));
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
