package net.warvale.scorch.commands.admin;

import net.warvale.scorch.commands.AbstractCommand;
import net.warvale.scorch.commands.CommandException;
import net.warvale.scorch.regions.RegionMapGen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
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
