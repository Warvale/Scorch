package net.warvale.scorch.commands.admin;

import net.warvale.scorch.commands.AbstractCommand;
import net.warvale.scorch.commands.CommandException;
import net.warvale.scorch.physics.ObsidianToLava;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAces on 7/8/2017
 */
public class SetObsidianInLavaDecayCommand extends AbstractCommand {
    public SetObsidianInLavaDecayCommand(){
        super("setobsidiandecay", "<seconds>");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)){
            throw new CommandException("Only players can use this command!");
        }
        Player player = (Player) sender;
        if (args.length != 1){
            return false;
        }
        if (!(Integer.valueOf(args[0]) >= 1 && Integer.valueOf(args[0]) <= 50)){
            player.sendMessage(ChatColor.RED + "Please enter a number between 1 and 50!");
            return true;
        }
        ObsidianToLava.setDelay(Integer.valueOf(args[0]));
        player.sendMessage(ChatColor.RED + "Obsidian decay delay is now set to " + ChatColor.GOLD + String.valueOf(ObsidianToLava.getDelay()) + ChatColor.RED + " seconds!");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
