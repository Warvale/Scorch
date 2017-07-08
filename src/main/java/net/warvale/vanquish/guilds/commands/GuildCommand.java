package main.java.net.warvale.vanquish.guilds.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.java.net.warvale.vanquish.commands.AbstractCommand;
import main.java.net.warvale.vanquish.exceptions.CommandException;

public class GuildCommand extends AbstractCommand {

	/* Created by Tricks */

	public GuildCommand() {
		super("guild", "");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof Player)) {
			throw new CommandException("Only players can use this command!");
		}

		Player player = (Player) sender;

		/* Dont know all the Commands needed yet */
		player.sendMessage("�6Guild Commands");
		player.sendMessage("�e/Create");
		player.sendMessage("�e/Info");
		player.sendMessage("�e/Rename");
		player.sendMessage("�e/Promote");
		player.sendMessage("�e/Demote");

		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		return new ArrayList<>();
	}

}
