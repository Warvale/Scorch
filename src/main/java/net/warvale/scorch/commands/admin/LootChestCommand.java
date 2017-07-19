package net.warvale.scorch.commands.admin;

import net.warvale.scorch.commands.AbstractCommand;
import net.warvale.scorch.commands.CommandException;
import net.warvale.scorch.loot.LootChest;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by AAces on 7/8/2017
 */
public class LootChestCommand extends AbstractCommand {
    public LootChestCommand(){
        super("lootchest", "<x> <y> <z> <biome id>");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)){
            throw new CommandException("Only players can use this command!");
        }
        Player player = (Player) sender;
        if (args.length != 4){
            player.sendMessage(ChatColor.RED + "Please specify all arguments!");
            return false;
        }

        try {
            new LootChest(parseInt(args[3]), new Location(player.getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2])));
        }  catch (IOException | ParseException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        player.sendMessage(ChatColor.RED + "Loot chest spawned at desired location!");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Block block = player.getTargetBlock((Set<Material>) null, 100);
        if(args.length == 0) {
            return new ArrayList<>(block.getX());
        } else if(args.length == 1){
            return new ArrayList<>(block.getY());
        } else if(args.length == 2){
            return new ArrayList<>(block.getZ());
        } else {
            return new ArrayList<>();
        }
    }
}
