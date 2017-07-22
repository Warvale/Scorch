package net.warvale.scorch.guilds;

import net.warvale.scorch.commands.AbstractCommand;
import net.warvale.scorch.commands.CommandException;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AAces on 7/21/2017
 */
public class GuildCommand extends AbstractCommand{

    public GuildCommand(){
        super("guild","");
    } //I put the usage at the very bottom of this class to have a custom usage message

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                sendUsageMessage(player);
                return true;
            }
            String term = args[0];
            switch (term){
                case "create":
                    if(args.length != 2){
                        player.sendMessage(ChatColor.DARK_RED + "[Scorch] " + ChatColor.RED + "Usage: /guild create <name>");
                        break;
                    }
                    String name = args[1];
                    try {
                        if(Guilds.isInGuild(player)){
                            player.sendMessage(ChatColor.RED + "You must leave your current guild with " + ChatColor.DARK_RED + "/guild leave" + ChatColor.RED + " before creating a new one!");
                            break;
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(!StringUtils.isAlphanumeric(name)){
                        player.sendMessage(ChatColor.RED + "Guild names must be alphanumeric and contain at least one letter!");
                        break;
                    }
                    boolean alpha = false;
                    for (int i = 0; i < name.length(); i++) {
                        if (Character.isLetter(name.charAt(i))) {
                            alpha = true;
                        }
                    }
                    if(!alpha){
                        player.sendMessage(ChatColor.RED + "Guild names must be alphanumeric and contain at least one letter!");
                        break;
                    }
                    try {
                        if(Guilds.doesGuildExist(name)){
                            player.sendMessage(ChatColor.RED + "A guild with the name " + ChatColor.DARK_RED + name + ChatColor.RED + " already exists!");
                            break;
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        Guilds.createGuild(name, player);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "info":
                    if (args.length == 1){
                        try {
                            if (!Guilds.isInGuild(player)){
                                player.sendMessage(ChatColor.RED + "You are not in a guild! Join a guild, or type /guild info <name>");
                                break;
                            } else{
                                int id = Guilds.getGuildId(player);
                                String guildName = Guilds.getGuildName(id);
                                String owner = Guilds.getGuildOwner(id).getName();
                                String numberOfPlayers = String.valueOf(Guilds.getAmountOfPlayers(id));
                                String islandsClaimed = String.valueOf(Guilds.getAmountOfClaimedIslands(id));
                                player.sendMessage(ChatColor.RED + "Info for the guild " + ChatColor.RED + guildName + ChatColor.RED + ":");
                                player.sendMessage(ChatColor.RED + "Owner: " + ChatColor.DARK_RED + owner);
                                player.sendMessage(ChatColor.RED + "Number of Players: " + ChatColor.DARK_RED + numberOfPlayers);
                                player.sendMessage(ChatColor.RED + "Number of Islands Claimed: " + ChatColor.DARK_RED + islandsClaimed);
                                break;
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (args.length == 2){
                        String gName = args[1];
                        try {
                            if(!Guilds.doesGuildExist(gName)){
                                player.sendMessage(ChatColor.RED + "The guild " + ChatColor.DARK_RED + gName + ChatColor.RED + " does not exist!");
                                break;
                            } else {
                                int id = Guilds.getGuildId(gName);
                                String guildName = Guilds.getGuildName(id);
                                String owner = Guilds.getGuildOwner(id).getName();
                                String numberOfPlayers = String.valueOf(Guilds.getAmountOfPlayers(id));
                                String islandsClaimed = String.valueOf(Guilds.getAmountOfClaimedIslands(id));
                                player.sendMessage(ChatColor.RED + "Info for the guild " + ChatColor.RED + guildName + ChatColor.RED + ":");
                                player.sendMessage(ChatColor.RED + "Owner: " + ChatColor.DARK_RED + owner);
                                player.sendMessage(ChatColor.RED + "Number of Players: " + ChatColor.DARK_RED + numberOfPlayers);
                                player.sendMessage(ChatColor.RED + "Number of Islands Claimed: " + ChatColor.DARK_RED + islandsClaimed);
                                break;
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "[Scorch] " + ChatColor.RED + "Usage: /guild info [name]");
                    }
                    break;
                case "kick":
                    if(args.length != 2){
                        player.sendMessage(ChatColor.DARK_RED + "[Scorch] " + ChatColor.RED + "Usage: /guild kick <player>");
                        break;
                    }
                    String p = args[1];
                    try {
                        if(!Guilds.isInGuild(player)){
                            player.sendMessage(ChatColor.RED + "You are not in a guild!");
                            break;
                        } else {
                            if (!(Guilds.getGuildRanking(player) == 3 || Guilds.getGuildRanking(player) == 4)){
                                player.sendMessage(ChatColor.RED + "You do not have permission to kick players from your guild!");
                                break;
                            }
                            if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(p))){
                                player.sendMessage(ChatColor.RED + "This player can not be found!");
                                break;
                            }
                            if (Guilds.getGuildRanking(Bukkit.getPlayer(p)) == 4){
                                player.sendMessage(ChatColor.RED + "You can not kick the owner of the guild!");
                                break;
                            }
                            if (player.equals(Bukkit.getPlayer(p))){
                                player.sendMessage(ChatColor.RED + "You can not kick yourself!");
                                break;
                            }
                            if (Guilds.getGuildRanking(player) == 3 && (Guilds.getGuildRanking(Bukkit.getPlayer(p)) == 3 || Guilds.getGuildRanking(Bukkit.getPlayer(p)) == 2)){
                                player.sendMessage(ChatColor.RED + "You can not kick players that have been promoted!");
                                break;
                            }
                            Guilds.kickPlayer(Bukkit.getPlayer(p));
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "invite":
                    try {
                        if(!Guilds.isInGuild(player)){
                            player.sendMessage(ChatColor.RED + "You are not in a guild!");
                            break;
                        }
                        if (Guilds.getGuildRanking(player) == 1){
                            player.sendMessage(ChatColor.RED + "You do not have permission to invite players!");
                            break;
                        }
                        if(args.length != 2){
                            player.sendMessage(ChatColor.DARK_RED + "[Scorch] " + ChatColor.RED + "Usage: /guild invite <player>");
                            break;
                        }
                        String a = args[1];
                        if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(a))){
                            player.sendMessage(ChatColor.RED + "This player can not be found!");
                            break;
                        }
                        GuildInvitations.invitePlayer(player, Bukkit.getPlayer(a));
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "promote":
                    if(args.length != 2){
                        player.sendMessage(ChatColor.DARK_RED + "[Scorch] " + ChatColor.RED + "Usage: /guild promote <player>");
                        break;
                    }
                    String o = args[1];
                    if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(o))){
                        player.sendMessage(ChatColor.RED + "This player can not be found!");
                        break;
                    }
                    try {
                        if(!Guilds.isInGuild(player)){
                            player.sendMessage(ChatColor.RED + "You are not in a guild!");
                            break;
                        } else {
                            if (player.equals(Bukkit.getPlayer(o))){
                                player.sendMessage(ChatColor.RED + "You can not promote yourself!");
                                break;
                            }
                            Guilds.promote(player, Bukkit.getPlayer(o));
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "demote":
                    if(args.length != 2){
                        player.sendMessage(ChatColor.DARK_RED + "[Scorch] " + ChatColor.RED + "Usage: /guild demote <player>");
                        break;
                    }
                    String q = args[1];
                    if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(q))){
                        player.sendMessage(ChatColor.RED + "This player can not be found!");
                        break;
                    }
                    try {
                        if(!Guilds.isInGuild(player)){
                            player.sendMessage(ChatColor.RED + "You are not in a guild!");
                            break;
                        } else {
                            if (player.equals(Bukkit.getPlayer(q))){
                                player.sendMessage(ChatColor.RED + "You can not demote yourself!");
                                break;
                            }
                            Guilds.demote(player, Bukkit.getPlayer(q));
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "transferownership":
                    if(args.length != 2){
                        player.sendMessage(ChatColor.DARK_RED + "[Scorch] " + ChatColor.RED + "Usage: /guild transferownership <player>");
                        break;
                    }
                    String z = args[1];
                    if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(z))){
                        player.sendMessage(ChatColor.RED + "This player can not be found!");
                        break;
                    }
                    try {
                        if(!Guilds.isInGuild(player)){
                            player.sendMessage(ChatColor.RED + "You are not in a guild!");
                            break;
                        }
                        if(Guilds.isGuildOwner(player)){
                            if(Bukkit.getPlayer(z).equals(player)){
                                player.sendMessage(ChatColor.RED + "You can not transfer ownership to yourself!");
                                break;
                            }
                            Guilds.transferOwnership(Bukkit.getPlayer(z), Guilds.getGuildId(player));
                            break;
                        } else {
                            player.sendMessage(ChatColor.RED + "You are not the owner of your guild!");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "leave":
                    try {
                        if(!Guilds.isInGuild(player)){
                            player.sendMessage(ChatColor.RED + "You are not in a guild!");
                            break;
                        }
                        Guilds.leaveGuild(player);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "accept":
                    try {
                        if(Guilds.isInGuild(player)){
                            player.sendMessage(ChatColor.RED + "You are already in a guild!");
                            break;
                        }
                        if(GuildInvitations.getAmountOfInvitations(player) == 1){
                            GuildInvitations.acceptInvite(player, -1, false);
                            break;
                        }
                        if(args.length == 2 && (args[1].equals("info") || args[1].equals("list"))){
                            GuildInvitations.acceptInvite(player, -1, true);
                        } else if(args.length == 2 && StringUtils.isNumeric(args[1])){
                            int i = Integer.valueOf(args[1]);
                            if (i >= 1 && i <= 10){
                                GuildInvitations.acceptInvite(player, i, false);
                            }
                        } else {
                            GuildInvitations.acceptInvite(player, -1, true);
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "decline":
                    try {
                        if(GuildInvitations.getAmountOfInvitations(player) == 1){
                            GuildInvitations.declineInvite(player, -1, false);
                            break;
                        }
                        if(args.length == 2 && (args[1].equals("info") || args[1].equals("list"))){
                            GuildInvitations.declineInvite(player, -1, true);
                        } else if(args.length == 2 && StringUtils.isNumeric(args[1])){
                            int i = Integer.valueOf(args[1]);
                            if (i >= 1 && i <= 10){
                                GuildInvitations.declineInvite(player, i, false);
                            }
                        } else {
                            GuildInvitations.declineInvite(player, -1, true);
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "disband":
                    try {
                        if(!Guilds.isInGuild(player)){
                            player.sendMessage(ChatColor.RED + "You are not in a guild!");
                            break;
                        }
                        if(!Guilds.isGuildOwner(player)){
                            player.sendMessage(ChatColor.RED + "You can not disband a guild that you do not own!");
                        } else {
                            Guilds.disbandGuild(Guilds.getGuildId(player));
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    sendUsageMessage(player);
                    break;
            }
            return true;
        } else {
            throw new CommandException("Only players can execute this command!");
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    private void sendUsageMessage(Player player){
        player.sendMessage(ChatColor.DARK_RED + "[Scorch] " + ChatColor.RED + "Usage:");
        player.sendMessage(ChatColor.RED + "/guild create <name>" + ChatColor.GOLD + "Create a guild if you are not in one.");
        player.sendMessage(ChatColor.RED + "/guild info [name]" + ChatColor.GOLD + "Get info about a specified guild, or leave blank for your own guild.");
        player.sendMessage(ChatColor.RED + "/guild kick <player>" + ChatColor.GOLD + "Kick a player from your guild (must have moderator+ permission).");
        player.sendMessage(ChatColor.RED + "/guild invite <player>" + ChatColor.GOLD + "Invite a player to your guild (must have admin+ permission).");
        player.sendMessage(ChatColor.RED + "/guild promote <player>" + ChatColor.GOLD + "Promote a player to the next rank (must have admin+ permission).");
        player.sendMessage(ChatColor.RED + "/guild demote <player>" + ChatColor.GOLD + "Demote a player to the previous rank (must have admin+ permission).");
        player.sendMessage(ChatColor.RED + "/guild transferownership <player>" + ChatColor.GOLD + "Transfer the ownership of a guild to another player in the guild (Can not be undone unless the new owner does" + ChatColor.RED + player.getName() + ChatColor.GOLD + "!).");
        player.sendMessage(ChatColor.RED + "/guild leave" + ChatColor.GOLD + "Leave the guild you are in.");
        player.sendMessage(ChatColor.RED + "/guild accept" + ChatColor.GOLD + "Accept a pending invitation to a guild.");
        player.sendMessage(ChatColor.RED + "/guild decline" + ChatColor.GOLD + "Decline a pending invitation to a guild.");
        player.sendMessage(ChatColor.RED + "/guild disband" + ChatColor.GOLD + "Delete the guild and unclaim all islands. CAN NOT BE UNDONE!");
    }
}
