package net.warvale.scorch.crates;

import net.warvale.scorch.enchantments.CustomEnchantment;
import net.warvale.scorch.enchantments.Rarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Created by AAces on 7/18/2017
 */
public enum CrateType {
    COMMON {
        @Override
        public int getChance() {
            return 740;
        }

        @Override
        public ArrayList<ItemStack> getItems() {
            ArrayList<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(Material.STICK, 3));
            items.add(new ItemStack(Material.BONE, 2));
            items.add(new ItemStack(Material.WEB, 2));
            items.add(new ItemStack(Material.DEAD_BUSH, 3));
            items.add(new ItemStack(Material.WOOD_SWORD));
            items.add(new ItemStack(Material.LEATHER_BOOTS));
            items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
            items.add(new ItemStack(Material.LEATHER_LEGGINGS));
            items.add(new ItemStack(Material.LEATHER_HELMET));
            items.add(new ItemStack(Material.FISHING_ROD));
            items.add(new ItemStack(Material.ARROW, 5));
            items.add(new ItemStack(Material.VINE, 4));
            return items;
        }

    },
    RARE {
        @Override
        public int getChance() {
            return 200;
        }

        @Override
        public ArrayList<ItemStack> getItems() {
            ArrayList<ItemStack> items = new ArrayList<>();

            ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
            ItemMeta swordMeta = ironSword.getItemMeta();
            swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
            ironSword.setItemMeta(swordMeta);

            items.add(ironSword);
            items.add(new ItemStack(Material.WATER_BUCKET));
            items.add(new ItemStack(Material.DIAMOND, 2));
            items.add(new ItemStack(Material.IRON_BOOTS));
            items.add(new ItemStack(Material.IRON_CHESTPLATE));
            items.add(new ItemStack(Material.IRON_LEGGINGS));
            items.add(new ItemStack(Material.IRON_HELMET));
            items.add(CustomEnchantment.getRandomEnchantItem(Rarity.COMMON, Rarity.UNCOMMON));

            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta bowMeta = bow.getItemMeta();
            bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            bow.setItemMeta(bowMeta);

            items.add(bow);

            return items;
        }

    },
    LEGENDARY {
        @Override
        public int getChance() {
            return 59;
        }

        @Override
        public ArrayList<ItemStack> getItems() {
            ArrayList<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(Material.DIAMOND, 16));
            items.add(CustomEnchantment.getRandomEnchantItem(Rarity.RARE, Rarity.EPIC));

            ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta swordMeta = diamondSword.getItemMeta();
            swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
            diamondSword.setItemMeta(swordMeta);

            items.add(diamondSword);

            ItemStack diamondHelm = new ItemStack(Material.DIAMOND_HELMET);
            ItemMeta helmMeta = diamondHelm.getItemMeta();
            helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            diamondHelm.setItemMeta(helmMeta);

            items.add(diamondHelm);

            ItemStack diamondChest = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemMeta chestMeta = diamondChest.getItemMeta();
            chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            diamondChest.setItemMeta(chestMeta);

            items.add(diamondChest);

            ItemStack diamondLegs = new ItemStack(Material.DIAMOND_LEGGINGS);
            ItemMeta legMeta = diamondLegs.getItemMeta();
            legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            diamondLegs.setItemMeta(legMeta);

            items.add(diamondLegs);

            ItemStack diamondBoots = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta bootMeta = diamondBoots.getItemMeta();
            bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            diamondBoots.setItemMeta(bootMeta);

            items.add(diamondBoots);

            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta bowMeta = bow.getItemMeta();
            bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
            bow.setItemMeta(bowMeta);

            items.add(bow);

            return items;
        }

    },
    MYTHICAL {
        @Override
        public int getChance() {
            return 1;
        }

        @Override
        public ArrayList<ItemStack> getItems() {
            ArrayList<ItemStack> items = new ArrayList<>();
            items.add(CustomEnchantment.getRandomEnchantItem(Rarity.LEGENDARY));

            //Donator Rank

            items.add(new ItemStack(Material.DIAMOND, 64));

            ItemStack diamondHelm = new ItemStack(Material.DIAMOND_HELMET);
            ItemMeta helmMeta = diamondHelm.getItemMeta();
            helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            helmMeta.addEnchant(Enchantment.MENDING, 1, true);
            helmMeta.addEnchant(Enchantment.THORNS, 2, true);
            helmMeta.addEnchant(Enchantment.DURABILITY, 3, true);
            helmMeta.addEnchant(Enchantment.OXYGEN, 3, true);
            diamondHelm.setItemMeta(helmMeta);

            items.add(diamondHelm);

            ItemStack diamondChest = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemMeta chestMeta = diamondChest.getItemMeta();
            chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            chestMeta.addEnchant(Enchantment.MENDING, 1, true);
            chestMeta.addEnchant(Enchantment.THORNS, 2, true);
            chestMeta.addEnchant(Enchantment.DURABILITY, 3, true);
            diamondChest.setItemMeta(chestMeta);

            items.add(diamondChest);

            ItemStack diamondLegs = new ItemStack(Material.DIAMOND_LEGGINGS);
            ItemMeta legMeta = diamondLegs.getItemMeta();
            legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            legMeta.addEnchant(Enchantment.MENDING, 1, true);
            legMeta.addEnchant(Enchantment.THORNS, 2, true);
            legMeta.addEnchant(Enchantment.DURABILITY, 3, true);
            diamondLegs.setItemMeta(legMeta);

            items.add(diamondLegs);

            ItemStack diamondBoots = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta bootMeta = diamondBoots.getItemMeta();
            bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            bootMeta.addEnchant(Enchantment.MENDING, 1, true);
            bootMeta.addEnchant(Enchantment.THORNS, 2, true);
            bootMeta.addEnchant(Enchantment.DEPTH_STRIDER, 2, true);
            bootMeta.addEnchant(Enchantment.DURABILITY, 3, true);
            diamondBoots.setItemMeta(bootMeta);

            items.add(diamondBoots);

            ItemStack diamondBoots1 = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta bootMeta1 = diamondBoots1.getItemMeta();
            bootMeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            bootMeta1.addEnchant(Enchantment.MENDING, 1, true);
            bootMeta1.addEnchant(Enchantment.THORNS, 2, true);
            bootMeta1.addEnchant(Enchantment.FROST_WALKER, 2, true);
            bootMeta1.addEnchant(Enchantment.DURABILITY, 3, true);
            diamondBoots1.setItemMeta(bootMeta1);

            items.add(diamondBoots1);

            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta bowMeta = bow.getItemMeta();
            bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
            bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            bowMeta.addEnchant(Enchantment.MENDING, 3, true);
            bowMeta.addEnchant(Enchantment.ARROW_FIRE, 2, true);
            bowMeta.addEnchant(Enchantment.DURABILITY, 3, true);
            bow.setItemMeta(bowMeta);

            items.add(bow);

            ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemMeta pickaxeMeta = pickaxe.getItemMeta();
            pickaxeMeta.addEnchant(Enchantment.MENDING, 2, true);
            pickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 4, true);
            pickaxeMeta.addEnchant(Enchantment.DURABILITY, 3, true);
            pickaxeMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
            pickaxe.setItemMeta(pickaxeMeta);

            items.add(pickaxe);

            ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta swordMeta = diamondSword.getItemMeta();
            swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
            swordMeta.addEnchant(Enchantment.MENDING, 2, true);
            swordMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
            swordMeta.addEnchant(Enchantment.DURABILITY, 4, true);
            swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
            swordMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 3, true);
            diamondSword.setItemMeta(swordMeta);

            items.add(diamondSword);

            return items;
        }

    },
    VOTE {
        @Override
        public int getChance() {
            return 0;
        }

        @Override
        public ArrayList<ItemStack> getItems() {
            ArrayList<ItemStack> items = new ArrayList<>();
            items.add(CustomEnchantment.getRandomEnchantItem(Rarity.COMMON, Rarity.UNCOMMON, Rarity.RARE));
            return items;
        }

    },
    RELEASE {
        @Override
        public int getChance() {
            return 0;
        }

        @Override
        public ArrayList<ItemStack> getItems() {
            ArrayList<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(Material.STICK, 3));
            items.add(new ItemStack(Material.BONE, 2));
            items.add(new ItemStack(Material.WEB, 2));
            items.add(new ItemStack(Material.DEAD_BUSH, 3));
            items.add(new ItemStack(Material.WOOD_SWORD));
            items.add(new ItemStack(Material.LEATHER_BOOTS));
            items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
            items.add(new ItemStack(Material.LEATHER_LEGGINGS));
            items.add(new ItemStack(Material.LEATHER_HELMET));
            items.add(new ItemStack(Material.FISHING_ROD));
            items.add(new ItemStack(Material.ARROW, 5));
            items.add(new ItemStack(Material.VINE, 4));

            ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
            ItemMeta swordMeta = ironSword.getItemMeta();
            swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
            ironSword.setItemMeta(swordMeta);

            items.add(ironSword);
            items.add(new ItemStack(Material.WATER_BUCKET));
            items.add(new ItemStack(Material.DIAMOND, 2));
            items.add(new ItemStack(Material.IRON_BOOTS));
            items.add(new ItemStack(Material.IRON_CHESTPLATE));
            items.add(new ItemStack(Material.IRON_LEGGINGS));
            items.add(new ItemStack(Material.IRON_HELMET));
            items.add(CustomEnchantment.getRandomEnchantItem(Rarity.COMMON, Rarity.UNCOMMON));

            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta bowMeta = bow.getItemMeta();
            bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            bow.setItemMeta(bowMeta);

            items.add(bow);
            return items;
        }
    };

    public ArrayList<CrateType> getStandardCrates(){
        ArrayList<CrateType> crateTypes = new ArrayList<>();
        crateTypes.add(COMMON);
        crateTypes.add(RARE);
        crateTypes.add(LEGENDARY);
        crateTypes.add(MYTHICAL);
        return crateTypes;
    }
    public abstract int getChance();
    public String getLore(){
        return null;
    }
    public abstract ArrayList<ItemStack> getItems();
}
