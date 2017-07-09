package net.warvale.vanquish.commands.admin;

import net.warvale.vanquish.commands.AbstractCommand;
import net.warvale.vanquish.exceptions.CommandException;
import net.warvale.vanquish.guilds.regions.RegionMapGen;
import org.bukkit.*;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ron on 8/7/2017.
 */
public class Gen2dCommand extends AbstractCommand {

    public Gen2dCommand() {super("gen2d", "<world name>");}
    private double sealevel = 20;
    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (Bukkit.getServer().getWorld(args[0]) == null) {
            throw new CommandException("Invalid world name!");
        }
        World world = Bukkit.getServer().getWorld(args[0]);
        sender.sendMessage(ChatColor.RED + "Generating region map, output will be printed to console.");
        RegionMapGen.genRegionMap(world);

        return true;
    }
    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
