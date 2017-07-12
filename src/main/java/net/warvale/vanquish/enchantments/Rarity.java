package net.warvale.vanquish.enchantments;

import org.bukkit.ChatColor;

/**
 * Created by AAces on 7/9/2017
 */
public enum Rarity {
    COMMON {
        @Override
        public String getName() {
            return ChatColor.GRAY + "Common";
        }

        @Override
        public ChatColor getColor() {
            return ChatColor.GRAY;
        }
    },
    UNCOMMON {
        @Override
        public String getName() {
            return ChatColor.BLUE + "Uncommon";
        }

        @Override
        public ChatColor getColor() {
            return ChatColor.BLUE;
        }
    },
    RARE {
        @Override
        public String getName() {
            return ChatColor.DARK_PURPLE + "Rare";
        }

        @Override
        public ChatColor getColor() {
            return ChatColor.DARK_PURPLE;
        }
    },
    EPIC {
        @Override
        public String getName() {
            return ChatColor.RED + "Epic";
        }

        @Override
        public ChatColor getColor() {
            return ChatColor.RED;
        }
    },
    LEGENDARY {
        @Override
        public String getName() {
            return ChatColor.GOLD + "Legendary";
        }

        @Override
        public ChatColor getColor() {
            return ChatColor.GOLD;
        }
    };

    public abstract String getName();
    public abstract ChatColor getColor();
}
