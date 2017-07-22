package net.warvale.scorch.guilds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by AAces on 7/22/2017
 */
public class GuildInvitations {
    public static HashMap<Player, ArrayList<Integer>> invites = new HashMap<>();
    //Invite a player to a specified guild
    public static void invitePlayer(Player host, Player invited) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> ids;
        if (invites.containsKey(invited)){
            ids = invites.get(invited);
        } else {
            ids = new ArrayList<>();
        }
        int size = invites.get(invited).size();
        if(ids.contains(Guilds.getGuildId(host))){
            host.sendMessage(ChatColor.RED + "This player has already been invited to your guild!");
            return;
        }
        if(size >= 10){
            host.sendMessage(ChatColor.RED + "This player has too many pending invites!");
            return;
        }
        ids.add(Guilds.getGuildId(host));
        invited.sendMessage(ChatColor.DARK_RED + host.getName() + ChatColor.RED + " has invited you to their guild " + ChatColor.DARK_RED + Guilds.getGuildName(host) +ChatColor.RED + "! Type /guild accept to accept their invitation!");
        invites.put(invited, ids);
    }

    //accept a pending invite
    public static void acceptInvite(Player invited, int num, boolean helpMessage) throws SQLException, ClassNotFoundException {
        if (!invites.containsKey(invited) || invites.get(invited).size() == 0){
            invited.sendMessage(ChatColor.RED + "You do not have any pending invites!");
            return;
        }
        if (invites.get(invited).size() == 1){
            Guilds.joinGuild(invited, invites.get(invited).get(0));
            invited.sendMessage(ChatColor.RED + "You joined the guild " + ChatColor.DARK_RED + Guilds.getGuildName(invites.get(invited).get(0)) + ChatColor.RED + "!");
            invites.remove(invited);
            return;
        }
        int size = invites.get(invited).size();
        if (helpMessage){
            switch (size){
                case 2:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to accept by typing " + ChatColor.DARK_RED + "/guild accept <#>");
                    invited.sendMessage(ChatColor.RED + "Please note that accepting an invitation clears your list of pending invitations.");
                    break;
                case 3:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to accept by typing " + ChatColor.DARK_RED + "/guild accept <#>");
                    invited.sendMessage(ChatColor.RED + "Please note that accepting an invitation clears your list of pending invitations.");
                    break;
                case 4:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to accept by typing " + ChatColor.DARK_RED + "/guild accept <#>");
                    invited.sendMessage(ChatColor.RED + "Please note that accepting an invitation clears your list of pending invitations.");
                    break;
                case 5:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to accept by typing " + ChatColor.DARK_RED + "/guild accept <#>");
                    invited.sendMessage(ChatColor.RED + "Please note that accepting an invitation clears your list of pending invitations.");
                    break;
                case 6:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.DARK_RED + "6. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(5)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to accept by typing " + ChatColor.DARK_RED + "/guild accept <#>");
                    invited.sendMessage(ChatColor.RED + "Please note that accepting an invitation clears your list of pending invitations.");
                    break;
                case 7:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.DARK_RED + "6. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(5)));
                    invited.sendMessage(ChatColor.DARK_RED + "7. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(6)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to accept by typing " + ChatColor.DARK_RED + "/guild accept <#>");
                    invited.sendMessage(ChatColor.RED + "Please note that accepting an invitation clears your list of pending invitations.");
                    break;
                case 8:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.DARK_RED + "6. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(5)));
                    invited.sendMessage(ChatColor.DARK_RED + "7. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(6)));
                    invited.sendMessage(ChatColor.DARK_RED + "8. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(7)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to accept by typing " + ChatColor.DARK_RED + "/guild accept <#>");
                    invited.sendMessage(ChatColor.RED + "Please note that accepting an invitation clears your list of pending invitations.");
                    break;
                case 9:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.DARK_RED + "6. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(5)));
                    invited.sendMessage(ChatColor.DARK_RED + "7. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(6)));
                    invited.sendMessage(ChatColor.DARK_RED + "8. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(7)));
                    invited.sendMessage(ChatColor.DARK_RED + "9. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(8)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to accept by typing " + ChatColor.DARK_RED + "/guild accept <#>");
                    invited.sendMessage(ChatColor.RED + "Please note that accepting an invitation clears your list of pending invitations.");
                    break;
                case 10:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.DARK_RED + "6. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(5)));
                    invited.sendMessage(ChatColor.DARK_RED + "7. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(6)));
                    invited.sendMessage(ChatColor.DARK_RED + "8. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(7)));
                    invited.sendMessage(ChatColor.DARK_RED + "9. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(8)));
                    invited.sendMessage(ChatColor.DARK_RED + "10. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(9)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to accept by typing " + ChatColor.DARK_RED + "/guild accept <#>");
                    invited.sendMessage(ChatColor.RED + "Please note that accepting an invitation clears your list of pending invitations.");
                    break;
            }
            return;
        }
        int id = invites.get(invited).get(num-1);
        Guilds.joinGuild(invited, id);
        invited.sendMessage(ChatColor.RED + "You joined the guild " + ChatColor.DARK_RED + Guilds.getGuildName(invites.get(invited).get(id)) + ChatColor.RED + "!");
        invites.remove(invited);
    }

    //Decline a pending invite
    public static void declineInvite(Player invited, int num, boolean helpMessage) throws SQLException, ClassNotFoundException {
        if (!invites.containsKey(invited) || invites.get(invited).size() == 0){
            invited.sendMessage(ChatColor.RED + "You do not have any pending invites!");
            return;
        }
        if (invites.get(invited).size() == 1){
            invited.sendMessage(ChatColor.RED + "You declined the invitation from the guild " + ChatColor.DARK_RED + Guilds.getGuildName(invites.get(invited).get(0)) + ChatColor.RED + "!");
            Guilds.getGuildOwner(invites.get(invited).get(0)).sendMessage(ChatColor.DARK_RED + invited.getName() + ChatColor.RED + " has declined your invitation!");
            invites.remove(invited);
            return;
        }
        int size = invites.get(invited).size();
        if (helpMessage){
            switch (size){
                case 2:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to decline by typing " + ChatColor.DARK_RED + "/guild decline <#>");
                    break;
                case 3:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to decline by typing " + ChatColor.DARK_RED + "/guild decline <#>");
                    break;
                case 4:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to decline by typing " + ChatColor.DARK_RED + "/guild decline <#>");
                    break;
                case 5:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to decline by typing " + ChatColor.DARK_RED + "/guild decline <#>");
                    break;
                case 6:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.DARK_RED + "6. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(5)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to decline by typing " + ChatColor.DARK_RED + "/guild decline <#>");
                    break;
                case 7:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.DARK_RED + "6. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(5)));
                    invited.sendMessage(ChatColor.DARK_RED + "7. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(6)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to decline by typing " + ChatColor.DARK_RED + "/guild decline <#>");
                    break;
                case 8:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.DARK_RED + "6. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(5)));
                    invited.sendMessage(ChatColor.DARK_RED + "7. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(6)));
                    invited.sendMessage(ChatColor.DARK_RED + "8. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(7)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to decline by typing " + ChatColor.DARK_RED + "/guild decline <#>");
                    break;
                case 9:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.DARK_RED + "6. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(5)));
                    invited.sendMessage(ChatColor.DARK_RED + "7. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(6)));
                    invited.sendMessage(ChatColor.DARK_RED + "8. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(7)));
                    invited.sendMessage(ChatColor.DARK_RED + "9. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(8)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to decline by typing " + ChatColor.DARK_RED + "/guild decline <#>");
                    break;
                case 10:
                    invited.sendMessage(ChatColor.DARK_RED + "Invitations: ");
                    invited.sendMessage(ChatColor.DARK_RED + "1. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(0)));
                    invited.sendMessage(ChatColor.DARK_RED + "2. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(1)));
                    invited.sendMessage(ChatColor.DARK_RED + "3. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(2)));
                    invited.sendMessage(ChatColor.DARK_RED + "4. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(3)));
                    invited.sendMessage(ChatColor.DARK_RED + "5. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(4)));
                    invited.sendMessage(ChatColor.DARK_RED + "6. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(5)));
                    invited.sendMessage(ChatColor.DARK_RED + "7. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(6)));
                    invited.sendMessage(ChatColor.DARK_RED + "8. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(7)));
                    invited.sendMessage(ChatColor.DARK_RED + "9. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(8)));
                    invited.sendMessage(ChatColor.DARK_RED + "10. " + ChatColor.RED + Guilds.getGuildName(invites.get(invited).get(9)));
                    invited.sendMessage(ChatColor.RED + "You have multiple invites pending. Choose which to decline by typing " + ChatColor.DARK_RED + "/guild decline <#>");
                    break;
            }
            return;
        }
        int id = invites.get(invited).get(num-1);
        invited.sendMessage(ChatColor.RED + "You declined the invitation from the guild " + ChatColor.DARK_RED + Guilds.getGuildName(invites.get(invited).get(id)) + ChatColor.RED + "!");
        Guilds.getGuildOwner(invites.get(invited).get(id)).sendMessage(ChatColor.DARK_RED + invited.getName() + ChatColor.RED + " has declined your invitation!");
        ArrayList<Integer> ids = invites.get(invited);
        ids.remove(num-1);
        invites.remove(invited);
        invites.put(invited, ids);
    }
}
