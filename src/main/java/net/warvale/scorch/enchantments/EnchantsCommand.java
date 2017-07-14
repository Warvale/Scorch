package net.warvale.scorch.enchantments;

import net.warvale.scorch.Main;
import net.warvale.scorch.commands.AbstractCommand;
import net.warvale.scorch.commands.CommandException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AAces on 7/10/2017
 */
public class EnchantsCommand extends AbstractCommand {

    public EnchantsCommand(){
        super("enchantments", "");
    }

    public static Inventory enchantsGUI;
    public static Inventory meleeGUI;
    public static Inventory bowGUI;
    public static Inventory armorGUI;

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        enchantsGUI(player);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
    //The actual gui
    static void enchantsGUI(Player player){
        enchantsGUI = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Enchantments");

        ItemStack meleeCategory = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meleeMeta = meleeCategory.getItemMeta();
        meleeMeta.setDisplayName(ChatColor.RED + "Melee Enchantments");
        meleeCategory.setItemMeta(meleeMeta);

        ItemStack bowCategory = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bowCategory.getItemMeta();
        bowMeta.setDisplayName(ChatColor.RED + "Bow Enchantments");
        bowCategory.setItemMeta(bowMeta);

        ItemStack armorCategory = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta armorMeta = armorCategory.getItemMeta();
        armorMeta.setDisplayName(ChatColor.RED + "Armor Enchantments");
        armorCategory.setItemMeta(armorMeta);

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(ChatColor.GRAY + "");
        filler.setItemMeta(fillerMeta);

        enchantsGUI.setItem(0, filler);
        enchantsGUI.setItem(1, meleeCategory);
        enchantsGUI.setItem(2, filler);
        enchantsGUI.setItem(3, filler);
        enchantsGUI.setItem(4, bowCategory);
        enchantsGUI.setItem(5, filler);
        enchantsGUI.setItem(6, filler);
        enchantsGUI.setItem(7, armorCategory);
        enchantsGUI.setItem(8, filler);

        player.openInventory(enchantsGUI);
    }
    public static void meleeEnchantGUI(Player player){
        meleeGUI = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Melee Enchantments");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(ChatColor.GRAY + "");
        filler.setItemMeta(fillerMeta);

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.RED + "Previous");
        back.setItemMeta(backMeta);

        ItemStack thunderingStrike = new ItemStack(Material.BLAZE_ROD);
        ItemMeta thunderMeta = thunderingStrike.getItemMeta();
        thunderMeta.setDisplayName(ChatColor.GOLD + "Thundering Strike");
        thunderMeta.setLore(Arrays.asList(ChatColor.GRAY + Main.getEnchantment("Thundering Strike").getDescription(), ChatColor.GRAY + "Rarity: " + Main.getEnchantment("Thundering Strike").getRarity().getName()));
        thunderingStrike.setItemMeta(thunderMeta);

        ItemStack lifeSteal = new ItemStack(Material.FLINT);
        ItemMeta lifeStealMeta = lifeSteal.getItemMeta();
        lifeStealMeta.setDisplayName(ChatColor.RED + "Life Steal");
        lifeStealMeta.setLore(Arrays.asList(ChatColor.GRAY + Main.getEnchantment("Life Steal").getDescription(), ChatColor.GRAY + "Rarity: " + Main.getEnchantment("Life Steal").getRarity().getName()));
        lifeSteal.setItemMeta(lifeStealMeta);

        ItemStack lastHope = new ItemStack(Material.GHAST_TEAR);
        ItemMeta lastHopeMeta = lastHope.getItemMeta();
        lastHopeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Last Hope");
        lastHopeMeta.setLore(Arrays.asList(ChatColor.GRAY + Main.getEnchantment("Last Hope").getDescription(), ChatColor.GRAY + "Rarity: " + Main.getEnchantment("Last Hope").getRarity().getName()));
        lastHope.setItemMeta(lastHopeMeta);

        ItemStack EXPSteal = new ItemStack(Material.EXP_BOTTLE);
        ItemMeta EXPStealMeta = EXPSteal.getItemMeta();
        EXPStealMeta.setDisplayName(ChatColor.GREEN + "Experience Steal");
        EXPStealMeta.setLore(Arrays.asList(ChatColor.GRAY + Main.getEnchantment("Experience Steal").getDescription(), ChatColor.GRAY + "Rarity: " + Main.getEnchantment("Experience Steal").getRarity().getName()));
        EXPSteal.setItemMeta(EXPStealMeta);

        meleeGUI.setItem(0, filler);
        meleeGUI.setItem(1, thunderingStrike);
        meleeGUI.setItem(2, filler);
        meleeGUI.setItem(3, lifeSteal);
        meleeGUI.setItem(4, filler);
        meleeGUI.setItem(5, lastHope);
        meleeGUI.setItem(6, filler);
        meleeGUI.setItem(7, EXPSteal);
        meleeGUI.setItem(8, back);

        player.openInventory(meleeGUI);
    }
    public static void bowEnchantGUI(Player player){
        bowGUI = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + " Bow Enchantments");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(ChatColor.GRAY + "");
        filler.setItemMeta(fillerMeta);

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.RED + "Previous");
        back.setItemMeta(backMeta);

        ItemStack empty = new ItemStack(Material.BARRIER);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(ChatColor.RED + "There are currently no bow enchants!");
        empty.setItemMeta(emptyMeta);

        bowGUI.setItem(0, filler);
        bowGUI.setItem(1, filler);
        bowGUI.setItem(2, filler);
        bowGUI.setItem(3, filler);
        bowGUI.setItem(4, empty);
        bowGUI.setItem(5, filler);
        bowGUI.setItem(6, filler);
        bowGUI.setItem(7, filler);
        bowGUI.setItem(8, back);

        player.openInventory(bowGUI);
    }
    public static void armorEnchantGUI(Player player){
        armorGUI = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Armor Enchantments");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(ChatColor.GRAY + "");
        filler.setItemMeta(fillerMeta);

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.RED + "Previous");
        back.setItemMeta(backMeta);

        ItemStack lifeEssence = new ItemStack(Material.POTION);
        ItemMeta lifeEssenceMeta = lifeEssence.getItemMeta();
        lifeEssenceMeta.setDisplayName(ChatColor.RED + "Life Essence");
        lifeEssenceMeta.setLore(Arrays.asList(ChatColor.GRAY + Main.getEnchantment("Life Essence").getDescription(), ChatColor.GRAY + "Rarity: " + Main.getEnchantment("Life Essence").getRarity().getName()));
        lifeEssence.setItemMeta(lifeEssenceMeta);

        ItemStack endurance = new ItemStack(Material.SHIELD);
        ItemMeta enduranceMeta = endurance.getItemMeta();
        enduranceMeta.setDisplayName(ChatColor.GOLD + "Endurance");
        enduranceMeta.setLore(Arrays.asList(ChatColor.GRAY + Main.getEnchantment("Endurance").getDescription(), ChatColor.GRAY + "Rarity: " + Main.getEnchantment("Endurance").getRarity().getName()));
        endurance.setItemMeta(enduranceMeta);

        armorGUI.setItem(0, filler);
        armorGUI.setItem(1, filler);
        armorGUI.setItem(2, filler);
        armorGUI.setItem(3, lifeEssence);
        armorGUI.setItem(4, filler);
        armorGUI.setItem(5, endurance);
        armorGUI.setItem(6, filler);
        armorGUI.setItem(7, filler);
        armorGUI.setItem(8, back);

        player.openInventory(armorGUI);
    }
}
