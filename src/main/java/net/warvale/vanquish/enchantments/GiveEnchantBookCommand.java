package net.warvale.vanquish.enchantments;

import net.warvale.vanquish.Main;
import net.warvale.vanquish.commands.AbstractCommand;
import net.warvale.vanquish.commands.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAces on 7/12/2017
 */
public class GiveEnchantBookCommand extends AbstractCommand {
    public GiveEnchantBookCommand(){
        super("giveenchant", "<thundering_strike|life_steal|life_essence|last_hope|experience_steal|endurance>");
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)){
            throw new CommandException("Only players can use this command");
        }
        Player player = (Player) sender;
        if(args.length != 1){ return false; }
        String preEnchant = args[0];
        if(!(preEnchant.equals("thundering_strike") || preEnchant.equals("life_steal") || preEnchant.equals("life_essence") || preEnchant.equals("last_hope") || preEnchant.equals("experience_steal") || preEnchant.equals("endurance"))){
            return false;
        }
        String enchant = preEnchant.replaceAll("_", " ");
        ItemStack item = Main.getEnchantment(enchant).getItem();
        player.getInventory().addItem(item);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
