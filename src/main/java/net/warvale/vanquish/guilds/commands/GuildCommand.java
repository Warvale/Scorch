package net.warvale.vanquish.guilds.commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.warvale.vanquish.utils.ChatUtils;
import net.warvale.vanquish.commands.AbstractCommand;
import net.warvale.vanquish.exceptions.CommandException;

public class GuildCommand extends AbstractCommand {

	/* Created by Tricks */

	public GuildCommand() {
		super("guild", "<create|info|rename|promote|demote>");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof Player)) {
			throw new CommandException("Only players can use this command!");
		}

		Player player = (Player) sender;
		Object guildsPrefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("GuildsPrefix"));
		if(args.length == 0){
			player.sendMessage(ChatUtils.gray + "[" + ChatUtils.red + "Guilds" + ChatUtils.gray + "] ");
			
			player.sendMessage("");
			player.sendMessage(ChatUtils.yellow + "General Commands");
			player.sendMessage(ChatUtils.gold + "/Guild Info" + ChatUtils.gray + "- List information on your Guild.");
			player.sendMessage(ChatUtils.gold+ "/Guild Leave" + ChatUtils.gray + "- Leave your current Guild.");
			player.sendMessage(ChatUtils.gold+ "/Guild Base" + ChatUtils.gray + "- Teleport to your Guild Base");
			player.sendMessage(ChatUtils.gold+ "/Guild Accept" + ChatUtils.gray + "- Accept a Guild Invite");
			player.sendMessage(ChatUtils.gold+ "/Guild Deny" + ChatUtils.gray + "- Deny a Guild Invite");
			
			player.sendMessage("");
			player.sendMessage(ChatUtils.yellow + "Guild Assistant Commands");
			player.sendMessage(ChatUtils.gold+ "/Guild Kick" + ChatUtils.gray + "- Kick a player from your Guild.");
			
			player.sendMessage("");
			player.sendMessage(ChatUtils.yellow + "Guild Owner Commands");
			player.sendMessage(ChatUtils.gold + "/Guild Create " + ChatUtils.gray + "- Create a Guild.");
			player.sendMessage(ChatUtils.gold + "/Guild Rename" + ChatUtils.gray + "- Rename your Guild.");
			player.sendMessage(ChatUtils.gold + "/Guild Promote" + ChatUtils.gray + "- Promote a Player in your Guild.");
			player.sendMessage(ChatUtils.gold+ "/Guild Demote" + ChatUtils.gray + "- Demote a Player in your Guild.");
			player.sendMessage(ChatUtils.gold+ "/Guild SetBase" + ChatUtils.gray + "- Set the Base of your Guild for Teleports.");
			return true;
		}
		switch (args[0]){
			case "create": //I just copied the code from the previous create class
				if (args.length != 2){
					player.sendMessage(guildsPrefix + "/guild create <name>");
					break;
				}
				/* Check to see if the Player is already in a Guild */
				if (!(plugin.getConfig().get("Player-Data." + player.getUniqueId().toString() + ".InGuild") == null)) {
					player.sendMessage(guildsPrefix + "You're already in a Guild.");
					return true;
				}

				/* String for the GuildName the player wants */
				String guildName = args[1];

				/* Check to see if anyone else has that Guild Name */
				if (plugin.getConfig().contains("Guild-Data." + guildName)) {
					player.sendMessage(guildsPrefix + "That Guild name is already in use.");
					return true;
				}

				/* Check to make sure the Guild name is less than 10 Characters (Prevents spam) */
				if(guildName.length() > 10) {
					player.sendMessage(guildsPrefix + "That Guild name is too long.");
					return true;
				}

				/* Check to make sure the Guild name only contains alphabet letters, again to prevent spam */
				if(!guildName.matches("[a-zA-Z_]*")){
					player.sendMessage(guildsPrefix + "Guild names can only contain A-Z");
					return true;
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

				player.sendMessage(guildsPrefix + "You have just created the Guild " + ChatColor.YELLOW + guildName);
				break;
			case "info":

				if (plugin.getConfig().get("Player-Data." + player.getUniqueId().toString() + ".InGuild") == null) {
					player.sendMessage(guildsPrefix + "You're not in a Guild.");
					return true;
				}

				
				/* Object for the players current guild name */
				Object currentGuildName = plugin.getConfig().get("Player-Data." + player.getUniqueId().toString() + ".GuildName");

				
				/* Get all online members in their guild */
				List<String> playersInGuild = new ArrayList<>();

				for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
					if (plugin.getConfig()
							.get("Player-Data." + onlinePlayers.getUniqueId().toString() + ".GuildName") == currentGuildName)
						playersInGuild.add(onlinePlayers.getName().toString() + ChatUtils.gray);
				}


				player.sendMessage("");

				player.sendMessage(ChatUtils.gray + "[" + ChatUtils.red + "Guilds" + ChatUtils.gray + "] ");

				player.sendMessage(ChatUtils.gray + "Guild Name: " + ChatUtils.gold + currentGuildName);
				
				player.sendMessage(ChatUtils.gray + "Islands Claimed: " + ChatUtils.gold + "Soon");

				player.sendMessage(ChatUtils.gray + "Date Created: " + ChatUtils.gold + plugin.getConfig().get("Guild-Data." + currentGuildName + ".DateCreated"));

				player.sendMessage(ChatUtils.gray + "Online Members: " + playersInGuild.toString().replace("[", "").replace("]", "").trim());

				player.sendMessage("");
				break;
			case "invite":
				//todo: Put code here
				break;
			case "accept":
				//todo: Put code here
				break;
			case "deny":
				//todo: Put code here
				break;
			case "kick":
				//todo: Put code here
				break;
			case "setbase":
				//todo: Put code here
				break;
			case "base":
				//todo: Put code here
				break;
			case "rename":
				//todo: Put code here
				break;
			case "promote":
				//todo: Put code here
				break;
			case "demote":
				//todo: Put code here
				break;
			default:
				player.sendMessage(ChatUtils.gray + "[" + ChatUtils.red + "Guilds" + ChatUtils.gray + "] ");
				
				player.sendMessage("");
				player.sendMessage(ChatUtils.yellow + "General Commands");
				player.sendMessage(ChatUtils.gold + "/Guild Info" + ChatUtils.gray + "- List information on your Guild.");
				player.sendMessage(ChatUtils.gold+ "/Guild Leave" + ChatUtils.gray + "- Leave your current Guild.");
				player.sendMessage(ChatUtils.gold+ "/Guild Base" + ChatUtils.gray + "- Teleport to your Guild Base");
				player.sendMessage(ChatUtils.gold+ "/Guild Accept" + ChatUtils.gray + "- Accept a Guild Invite");
				player.sendMessage(ChatUtils.gold+ "/Guild Deny" + ChatUtils.gray + "- Deny a Guild Invite");
				
				player.sendMessage("");
				player.sendMessage(ChatUtils.yellow + "Guild Assistant Commands");
				player.sendMessage(ChatUtils.gold+ "/Guild Kick" + ChatUtils.gray + "- Kick a player from your Guild.");
				
				player.sendMessage("");
				player.sendMessage(ChatUtils.yellow + "Guild Owner Commands");
				player.sendMessage(ChatUtils.gold + "/Guild Create " + ChatUtils.gray + "- Create a Guild.");
				player.sendMessage(ChatUtils.gold + "/Guild Rename" + ChatUtils.gray + "- Rename your Guild.");
				player.sendMessage(ChatUtils.gold + "/Guild Promote" + ChatUtils.gray + "- Promote a Player in your Guild.");
				player.sendMessage(ChatUtils.gold+ "/Guild Demote" + ChatUtils.gray + "- Demote a Player in your Guild.");
				player.sendMessage(ChatUtils.gold+ "/Guild SetBase" + ChatUtils.gray + "- Set the Base of your Guild for Teleports.");
				break;
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		return new ArrayList<>();
	}

}
