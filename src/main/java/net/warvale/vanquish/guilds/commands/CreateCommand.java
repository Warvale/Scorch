package main.java.net.warvale.vanquish.guilds.commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.java.net.warvale.vanquish.commands.AbstractCommand;
import main.java.net.warvale.vanquish.exceptions.CommandException;

public class CreateCommand extends AbstractCommand {

	/* Created by Tricks */

	public CreateCommand() {
		super("create", "");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof Player)) {
			throw new CommandException("Only players can use this command!");
		}

		Player player = (Player) sender;

		Object guildsPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("GuildsPrefix"));


		/* Check to see if the Player is already in a Guild */
		if (!(plugin.getConfig().get("Player-Data." + player.getUniqueId().toString() + ".InGuild") == null)) {
			player.sendMessage(guildsPrefix + "�7You're already in a Guild.");
			return false;
		}

		/* Check to make sure the Right args are used */
		if(args.length == 0 || args.length > 1) {	
			player.sendMessage(guildsPrefix + "�7Usage: /Create (GuildName)");
			return false;
		}

		/* String for the GuildName the player wants */
		String guildName = args[0];

		/* Check to see if anyone elser has that Guild Name */
		if (plugin.getConfig().contains("Guild-Data." + guildName)) {
			player.sendMessage(guildsPrefix + "�7That Guild name is already in use.");
			return false;
		}
		
		/* Check to make sure the Guild name is less than 10 Characters (Prevents spam) */
		if(guildName.length() > 10) {
			player.sendMessage(guildsPrefix + "�7That Guild name is too long.");
			return false;
		}
		
		/* Check to make sure the Guild name only contains alphabet letters, again to prevent spam */
		if(!guildName.matches("[a-zA-Z_]*")){
			player.sendMessage(guildsPrefix + "�7Guild names can only contain A-Z");
			return false;
		}
		
		/* Making Guild details */
		plugin.getConfig().createSection("Guild-Data." + guildName);
		plugin.getConfig().set("Guild-Data." + guildName + ".GuildOwner", player.getUniqueId().toString());
		
		plugin.getConfig().set("Guild-Data." + guildName + ".AmountOfMembers", 1);
		plugin.getConfig().set("Guild-Data." + guildName + ".DateCreated", new SimpleDateFormat("dd/MM/yy").format(new Date()));
		plugin.getConfig().set("Guild-Data." + guildName + ".GuildLevel", 0);
		plugin.saveConfig();
		
		// * Making player details */
		plugin.getConfig().set("Player-Data." + player.getUniqueId().toString() + ".InGuild", true);
		plugin.getConfig().set("Player-Data." + player.getUniqueId().toString() + ".GuildName", guildName);
		plugin.saveConfig();
		
		player.sendMessage(guildsPrefix + "�7You have just created the Guild �e" + guildName);


		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		return new ArrayList<>();
	}

}	
