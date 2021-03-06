package net.warvale.scorch.crates;

import net.warvale.scorch.commands.AbstractCommand;
import net.warvale.scorch.commands.CommandException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAces on 7/19/2017
 */
public class GiveVoteCrateKeyCommand extends AbstractCommand {

    public GiveVoteCrateKeyCommand(){
        super("givevotecratekey", "<player>");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)){
            throw new CommandException("Only players can execute this command!");
        }
        if (args.length != 1){
            return false;
        }
        if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
            throw new CommandException("The specified player can not be found!");
        }
        Player target = Bukkit.getPlayer(args[0]);
        target.getInventory().addItem(Crate.getVoteCrateKey());
        sender.sendMessage(ChatColor.RED + "Successfully gave " + ChatColor.RED + target.getName() + ChatColor.RED + " a vote crate key!");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
