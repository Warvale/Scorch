package main.java.net.warvale.vanquish.enchantments;

import main.java.net.warvale.vanquish.Main;
import main.java.net.warvale.vanquish.utils.EnchantUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AAces on 7/9/2017
 */
public class EnchantListener implements Listener {

    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof LivingEntity)){
            return;
        }
        LivingEntity damaged = (LivingEntity) event.getEntity();
        LivingEntity damager = event.getDamager() instanceof LivingEntity ? (LivingEntity) event.getDamager() : event.getDamager() instanceof Projectile ? (LivingEntity) ((Projectile) event.getDamager()).getShooter() : null;
        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK
                && event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) return;
        if (damager != null) {
            // Apply offensive enchantments
            if (!getValidEnchantments(getItems(damager)).entrySet().isEmpty()){
                for (Map.Entry<CustomEnchantment, Integer> entry : getValidEnchantments(getItems(damager)).entrySet()) {
                    entry.getKey().offenseEffect(damager, damaged, event);
                }
            }
        }

        // Apply defensive enchantments
        if (!getValidEnchantments(getItems(damaged)).entrySet().isEmpty()){
            for (Map.Entry<CustomEnchantment, Integer> entry : getValidEnchantments(getItems(damaged)).entrySet()) {
                entry.getKey().defenseEffect(damaged, damager, event);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        for (Map.Entry<CustomEnchantment, Integer> entry : getValidEnchantments(getItems(player)).entrySet()) {
            entry.getKey().passiveEffect(player, event);
        }
    }

    private static Inventory enchantmentInv;

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if (event.getClickedInventory().equals(EnchantsCommand.enchantsGUI)){
            event.setCancelled(true);
            switch (slot){
                case 1:
                    player.closeInventory();
                    EnchantsCommand.meleeEnchantGUI(player);
                    break;
                case 4:
                    player.closeInventory();
                    EnchantsCommand.bowEnchantGUI(player);
                    break;
                case 7:
                    player.closeInventory();
                    EnchantsCommand.armorEnchantGUI(player);
                    break;
                default:
                    player.closeInventory();
                    break;
            }
        } else if(event.getClickedInventory().equals(EnchantsCommand.meleeGUI) || event.getClickedInventory().equals(EnchantsCommand.bowGUI) || event.getClickedInventory().equals(EnchantsCommand.armorGUI)){
            switch (slot){
                case 8:
                    player.closeInventory();
                    EnchantsCommand.enchantsGUI(player);
                    break;
                default:
                    player.closeInventory();
                    break;
            }
        } else if(event.getClickedInventory().equals(enchantmentInv)){
            CustomEnchantment enchant = player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + Main.getEnchantment("Thundering Strike").getName()) ? Main.getEnchantment("Thundering Strike") : player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + Main.getEnchantment("Life Steal").getName()) ? Main.getEnchantment("Life Steal") : player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + Main.getEnchantment("Life Essence").getName()) ? Main.getEnchantment("Life Essence") : player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + Main.getEnchantment("Last Hope").getName()) ? Main.getEnchantment("Last Hope") : player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + Main.getEnchantment("Experience Steal").getName()) ? Main.getEnchantment("Experience Steal") : player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + Main.getEnchantment("Endurance").getName()) ? Main.getEnchantment("Endurance") : null;
            event.setCancelled(true);
            if (enchant == null){return;}
            switch (slot){
                case 0:
                    if (player.getInventory().getItem(0).getType() == null) break;
                    if(!enchant.getApplicableTo().getItems().contains(player.getInventory().getItem(0).getType())){
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "This enchant can not be applied to this item!");
                        break;
                    }
                    ItemStack item = player.getInventory().getItem(0);
                    player.getInventory().setItem(0, enchant.addToItem(item, 1));
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Enchant successfully applied!");
                    break;
                case 1:
                    if (player.getInventory().getItem(1).getType() == null) break;
                    if(!enchant.getApplicableTo().getItems().contains(player.getInventory().getItem(1).getType())){
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "This enchant can not be applied to this item!");
                        break;
                    }
                    ItemStack item1 = player.getInventory().getItem(1);
                    player.getInventory().setItem(1, enchant.addToItem(item1, 1));
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Enchant successfully applied!");
                    break;
                case 2:
                    if (player.getInventory().getItem(2).getType() == null) break;
                    if(!enchant.getApplicableTo().getItems().contains(player.getInventory().getItem(2).getType())){
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "This enchant can not be applied to this item!");
                        break;
                    }
                    ItemStack item2 = player.getInventory().getItem(2);
                    player.getInventory().setItem(2, enchant.addToItem(item2, 1));
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Enchant successfully applied!");
                    break;
                case 3:
                    if (player.getInventory().getItem(3).getType() == null) break;
                    if(!enchant.getApplicableTo().getItems().contains(player.getInventory().getItem(3).getType())){
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "This enchant can not be applied to this item!");
                        break;
                    }
                    ItemStack item3 = player.getInventory().getItem(3);
                    player.getInventory().setItem(3, enchant.addToItem(item3, 1));
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Enchant successfully applied!");
                    break;
                case 4:
                    if (player.getInventory().getItem(4).getType() == null) break;
                    if(!enchant.getApplicableTo().getItems().contains(player.getInventory().getItem(4).getType())){
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "This enchant can not be applied to this item!");
                        break;
                    }
                    ItemStack item4 = player.getInventory().getItem(4);
                    player.getInventory().setItem(4, enchant.addToItem(item4, 1));
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Enchant successfully applied!");
                    break;
                case 5:
                    if (player.getInventory().getItem(5).getType() == null) break;
                    if(!enchant.getApplicableTo().getItems().contains(player.getInventory().getItem(5).getType())){
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "This enchant can not be applied to this item!");
                        break;
                    }
                    ItemStack item5 = player.getInventory().getItem(5);
                    player.getInventory().setItem(5, enchant.addToItem(item5, 1));
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Enchant successfully applied!");
                    break;
                case 6:
                    if (player.getInventory().getItem(6).getType() == null) break;
                    if(!enchant.getApplicableTo().getItems().contains(player.getInventory().getItem(6).getType())){
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "This enchant can not be applied to this item!");
                        break;
                    }
                    ItemStack item6 = player.getInventory().getItem(6);
                    player.getInventory().setItem(6, enchant.addToItem(item6, 1));
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Enchant successfully applied!");
                    break;
                case 7:
                    if (player.getInventory().getItem(7).getType() == null) break;
                    if(!enchant.getApplicableTo().getItems().contains(player.getInventory().getItem(7).getType())){
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "This enchant can not be applied to this item!");
                        break;
                    }
                    ItemStack item7 = player.getInventory().getItem(7);
                    player.getInventory().setItem(7, enchant.addToItem(item7, 1));
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Enchant successfully applied!");
                    break;
                case 8:
                    if (player.getInventory().getItem(8).getType() == null) break;
                    if(!enchant.getApplicableTo().getItems().contains(player.getInventory().getItem(8).getType())){
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "This enchant can not be applied to this item!");
                        break;
                    }
                    ItemStack item8 = player.getInventory().getItem(8);
                    player.getInventory().setItem(8, enchant.addToItem(item8, 1));
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Enchant successfully applied!");
                    break;
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if(isEnchantmentItem(item)){
            event.setCancelled(true);
            enchantGUI(player);
        }
    }

    private void enchantGUI(Player player){
        enchantmentInv = Bukkit.createInventory(null, 9, "Choose a hotbar slot to enchant:");

        ItemStack one = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta oneMeta = one.getItemMeta();
        oneMeta.setDisplayName(ChatColor.RED + "One");
        one.setItemMeta(oneMeta);

        ItemStack two = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta twoMeta = two.getItemMeta();
        twoMeta.setDisplayName(ChatColor.RED + "Two");
        two.setItemMeta(twoMeta);

        ItemStack three = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta threeMeta = three.getItemMeta();
        threeMeta.setDisplayName(ChatColor.RED + "Three");
        three.setItemMeta(threeMeta);

        ItemStack four = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta fourMeta = four.getItemMeta();
        fourMeta.setDisplayName(ChatColor.RED + "Four");
        four.setItemMeta(fourMeta);

        ItemStack five = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta fiveMeta = five.getItemMeta();
        fiveMeta.setDisplayName(ChatColor.RED + "Five");
        five.setItemMeta(fiveMeta);

        ItemStack six = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta sixMeta = six.getItemMeta();
        sixMeta.setDisplayName(ChatColor.RED + "Six");
        six.setItemMeta(sixMeta);

        ItemStack seven = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta sevenMeta = seven.getItemMeta();
        sevenMeta.setDisplayName(ChatColor.RED + "Seven");
        seven.setItemMeta(sevenMeta);

        ItemStack eight = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta eightMeta = eight.getItemMeta();
        eightMeta.setDisplayName(ChatColor.RED + "Eight");
        eight.setItemMeta(eightMeta);

        ItemStack nine = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta nineMeta = nine.getItemMeta();
        nineMeta.setDisplayName(ChatColor.RED + "Nine");
        nine.setItemMeta(nineMeta);

        enchantmentInv.setItem(0, one);
        enchantmentInv.setItem(1, two);
        enchantmentInv.setItem(2, three);
        enchantmentInv.setItem(3, four);
        enchantmentInv.setItem(4, five);
        enchantmentInv.setItem(5, six);
        enchantmentInv.setItem(6, seven);
        enchantmentInv.setItem(7, eight);
        enchantmentInv.setItem(8, nine);

        player.openInventory(enchantmentInv);
    }

    public boolean isEnchantmentItem(ItemStack item){
        if (!item.getType().equals(Material.END_CRYSTAL)) return false;
        ItemMeta itemMeta = item.getItemMeta();
        return itemMeta.getLore().contains(ChatColor.GRAY + Main.getEnchantment("Thundering Strike").getName()) || itemMeta.getLore().contains(ChatColor.GRAY + Main.getEnchantment("Life Steal").getName()) || itemMeta.getLore().contains(ChatColor.GRAY + Main.getEnchantment("Life Essence").getName()) || itemMeta.getLore().contains(ChatColor.GRAY + Main.getEnchantment("Last Hope").getName()) || itemMeta.getLore().contains(ChatColor.GRAY + Main.getEnchantment("Experience Steal").getName()) || itemMeta.getLore().contains(ChatColor.GRAY + Main.getEnchantment("Endurance").getName());
    }

    private Map<CustomEnchantment, Integer> getValidEnchantments(ArrayList<ItemStack> items) {
        Map<CustomEnchantment, Integer> validEnchantments = new HashMap<>();
        for (ItemStack item : items) {
            if (item == null) continue;
            ItemMeta meta = item.getItemMeta();
            if (meta == null) continue;
            if (!meta.hasLore()) continue;
            for (String lore : meta.getLore()) {
                String name = EnchantUtils.parseName(lore);
                int level = EnchantUtils.parseLevel(lore);
                if (name == null) continue;
                if (level == 0) continue;
                if (Main.isEnchantmentRegistered(name)) {
                    CustomEnchantment enchant = Main.getEnchantment(name);
                    if (enchant.isStackable() && validEnchantments.containsKey(enchant)) {
                        level += validEnchantments.get(enchant);
                    }
                    validEnchantments.put(enchant, level);
                }
            }
        }
        return validEnchantments;
    }
    private ArrayList<ItemStack> getItems(LivingEntity entity) {
        ItemStack[] armor = entity.getEquipment().getArmorContents();
        ItemStack weapon = entity.getEquipment().getItemInHand();
        ArrayList<ItemStack> items = new ArrayList<>(Arrays.asList(armor));
        items.add(weapon);

        return items;
    }
}
