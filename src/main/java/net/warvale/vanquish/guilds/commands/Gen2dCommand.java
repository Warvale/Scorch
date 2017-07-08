package net.warvale.vanquish.guilds.commands;

import net.warvale.vanquish.commands.AbstractCommand;
import net.warvale.vanquish.exceptions.CommandException;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ron on 8/7/2017.
 */
public class Gen2dCommand extends AbstractCommand {

    public Gen2dCommand() {super("gen2d", "");}
    private double sealevel = 20;
    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            sender.sendMessage("this is a player-only command");
            return true;
        }
        World world = ((Player) sender).getWorld();

        ArrayList<Integer> map = new ArrayList<>();

        double sizexz = world.getWorldBorder().getSize();
        System.out.println("Generating 2d world map, worldborder size: " + sizexz);
        for (double x=0; x <= sizexz; x++) {
            for (double z=0; z <= sizexz; z++) {
                Block currentBlock = world.getBlockAt(new Location(world, x,sealevel,z));
                if (currentBlock.getType().equals(Material.BEDROCK)) {map.add(1);} else {map.add(0);}

            }
        }
        System.out.println("Finished generating 2d world map: " + map);
        return true;
    }
    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
