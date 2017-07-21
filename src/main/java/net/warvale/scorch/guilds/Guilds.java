package net.warvale.scorch.guilds;

import net.warvale.scorch.Main;
import net.warvale.scorch.sql.SQLConnection;
import net.warvale.scorch.sql.SQLUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by AAces on 7/21/2017
 */
public class Guilds {

    private static HashMap<Player, Integer> invites = new HashMap<>();

    private static SQLConnection connection = Main.getDB();

    public static boolean isInGuild(Player player) throws SQLException, ClassNotFoundException {
        try {
            return (getGuildId(player) != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean doesGuildExist(String name) throws SQLException, ClassNotFoundException {
        return false;
    }

    public static void createGuild(String name, Player owner) throws SQLException, ClassNotFoundException {
        int id = getNextId();
        connection.executeSQL("INSERT INTO guilds(id, name, owner, max_players, islands_owned) VALUES ("+String.valueOf(id)+",'"+name+"','"+owner.getName()+"',50,0)");
        SQLUtil.update(connection, "users", "guild_ranking", 4, new SQLUtil.Where(new SQLUtil.WhereVar("name", owner.getName()).getWhere()));
        SQLUtil.update(connection, "users", "guild_id", id, new SQLUtil.Where(new SQLUtil.WhereVar("name", owner.getName()).getWhere()));
    }

    private static int getNextId() throws SQLException, ClassNotFoundException {
        int i = 1;
        ArrayList<Integer> ids = new ArrayList<>();

        ResultSet rs = SQLUtil.query(connection, "guilds", "id", new SQLUtil.Where("1"));

        while(rs.next()){
            ids.add(rs.getInt("id"));
        }
        for (int id : ids){
            i += id;
        }
        return i;
    }

    public static int getGuildId(Player player) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(connection, "users", "guild_id", new SQLUtil.Where(new SQLUtil.WhereVar("name", player.getName()).getWhere()));
        set.next();
        return set.getInt("guild_id");
    }

    public static int getGuildId(String name) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(connection, "guilds", "id", new SQLUtil.Where(new SQLUtil.WhereVar("name", name).getWhere()));
        set.next();
        return set.getInt("id");
    }

    public static String getGuildName(Player player) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(connection, "users", "guild_id", new SQLUtil.Where(new SQLUtil.WhereVar("name", player.getName()).getWhere()));
        set.next();
        return getGuildName(set.getInt("guild_id"));
    }

    public static String getGuildName(int id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(connection, "guilds", "name", new SQLUtil.Where(new SQLUtil.WhereVar("id", id).getWhere()));
        set.next();
        return set.getString("name");
    }

    public static Player getGuildOwner(int id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(connection, "guilds", "owner", new SQLUtil.Where(new SQLUtil.WhereVar("id", id).getWhere()));
        set.next();
        return Bukkit.getPlayer(set.getString("owner"));
    }

    public static Player getGuildOwner(String name) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(connection, "guilds", "owner", new SQLUtil.Where(new SQLUtil.WhereVar("name", name).getWhere()));
        set.next();
        return Bukkit.getPlayer(set.getString("owner"));

    }

    public static void leaveGuild(Player player) throws SQLException, ClassNotFoundException{
        if(isGuildOwner(player)){
            player.sendMessage(ChatColor.RED + "As the guild owner, you can not leave your guild! Transfer ownership by typing " + ChatColor.DARK_RED + "/guild transferownership" + ChatColor.RED + "!");
            return;
        }
        String name = getGuildName(player);
        int id = getGuildId(player);
        SQLUtil.update(connection, "users", "guild_ranking", 0, new SQLUtil.Where(new SQLUtil.WhereVar("name", player.getName()).getWhere()));
        SQLUtil.update(connection, "users", "guild_id", 0, new SQLUtil.Where(new SQLUtil.WhereVar("name", player.getName()).getWhere()));
        player.sendMessage(ChatColor.RED + "You have left the guild " + ChatColor.DARK_RED + name + ChatColor.RED + "!");
        sendGuildMessage(id, ChatColor.DARK_RED + player.getName() + " has left the guild!", false);
    }

    public static void invitePlayer(Player invited, int guildId) throws SQLException, ClassNotFoundException {
        invites.put(invited, guildId);
        invited.sendMessage(ChatColor.RED + "You have been invited to join the guild " + ChatColor.DARK_RED + getGuildName(guildId) + ChatColor.RED +  "! Type " + ChatColor.DARK_RED + "/guild accept" + ChatColor.RED + " to accept the invite!");
    }

    public static void acceptInvite(Player invited) throws SQLException, ClassNotFoundException {
        if(!invites.containsKey(invited)){
            invited.sendMessage(ChatColor.RED + "You have no pending invites!");
        }
        joinGuild(invited, invites.get(invited));
        invited.sendMessage(ChatColor.RED + "You have joined the guild " + ChatColor.DARK_RED + getGuildName(invites.get(invited)) + ChatColor.RED + "!");
        invites.remove(invited);
    }

    public static void declineInvite(Player invited) throws SQLException, ClassNotFoundException {
        if(!invites.containsKey(invited)){
            invited.sendMessage(ChatColor.RED + "You have no pending invites!");
        }
        int t = invites.get(invited);
        String i = getGuildName(t);
        invites.remove(invited);
        invited.sendMessage(ChatColor.RED + "You have declined the invite to the guild " + ChatColor.DARK_RED + i + ChatColor.RED + "!");
        getGuildOwner(t).sendMessage(ChatColor.DARK_RED + invited.getName() + ChatColor.RED + " has declined the invite to your guild!");
    }

    public static void disbandGuild(int guildId)throws SQLException, ClassNotFoundException{
        sendGuildMessage(guildId, ChatColor.RED + "The guild has been disbanded!", true);
        for(Player player : getPlayersInGuild(guildId)){
            leaveGuild(player);
        }
        connection.executeSQL("DELETE FROM guilds WHERE id = " + String.valueOf(guildId));
        //TODO: Unclaim Islands
    }

    public static boolean isGuildOwner(Player player) throws SQLException, ClassNotFoundException {
        return getGuildRanking(player) == 4; //4 is the rank a guild owner has
    }

    public static void transferOwnership(Player newOwner, int guildId) throws SQLException, ClassNotFoundException{
        Player currentOwner = getGuildOwner(guildId);
        if (getGuildId(newOwner) != getGuildId(currentOwner)){
            currentOwner.sendMessage(ChatColor.RED + "You can not transfer ownership of your guild to someone who is not a member!");
            return;
        }
        setGuildRanking(currentOwner, 3);
        setGuildRanking(newOwner, 4);
        setGuildOwner(newOwner, getGuildId(newOwner));
        sendGuildMessage(getGuildId(newOwner), ChatColor.DARK_RED + newOwner.getName() + " has been promoted to the owner of this guild!", true);
    }

    public static void promote(Player executor, Player target)throws SQLException, ClassNotFoundException{
        int exR = getGuildRanking(executor);
        int plR = getGuildRanking(target);
        if(getGuildId(executor) != getGuildId(target)){
            executor.sendMessage(ChatColor.RED + "You can not promote players in other guilds!");
            return;
        }
        if(exR == 4 && plR != 3){
            setGuildRanking(target, plR + 1);
            sendGuildMessage(getGuildId(target), ChatColor.DARK_RED + target.getName() + ChatColor.RED + " has been promoted to " + (getGuildRanking(target) == 2 ? ChatColor.GOLD + "MODERATOR!" : getGuildRanking(target) == 3 ? ChatColor.GOLD + "ADMINISTRATOR!" : ChatColor.DARK_RED + "ERROR: CONTACT SERVER ADMIN."), false);
        } else if(exR == 4){
            executor.sendMessage(ChatColor.RED + "This player is already an administrator! To make this player the guild owner, type /transferownership <player>");
        } else if(exR == 3 && (plR == 1 || plR == 2)){
            setGuildRanking(target, plR + 1);
            sendGuildMessage(getGuildId(target), ChatColor.DARK_RED + target.getName() + ChatColor.RED + " has been promoted to " + (getGuildRanking(target) == 2 ? ChatColor.GOLD + "MODERATOR!" : getGuildRanking(target) == 3 ? ChatColor.GOLD + "ADMINISTRATOR!" : ChatColor.DARK_RED + "ERROR: CONTACT SERVER ADMIN."), false);
        } else if (exR == 3 && (plR == 3 || plR == 4)){
            executor.sendMessage(ChatColor.RED + "You can not promote this player!");
        } else if (exR == 2 || exR == 1 || exR == 0){
            executor.sendMessage(ChatColor.RED + "You can not promote players!");
        }
    }

    public static void demote(Player executor, Player target)throws SQLException, ClassNotFoundException{
        int exR = getGuildRanking(executor);
        int plR = getGuildRanking(target);
        if(getGuildId(executor) != getGuildId(target)){
            executor.sendMessage(ChatColor.RED + "You can not demote players in other guilds!");
            return;
        }
        if(exR == 4 && plR != 1){
            setGuildRanking(target, plR - 1);
            sendGuildMessage(getGuildId(target), ChatColor.DARK_RED + target.getName() + ChatColor.RED + " has been demoted to " + (getGuildRanking(target) == 2 ? ChatColor.GOLD + "MODERATOR!" : getGuildRanking(target) == 1 ? ChatColor.GOLD + "MEMBER!" : ChatColor.DARK_RED + "ERROR: CONTACT SERVER ADMIN."), false);
        } else if(exR == 4){
            executor.sendMessage(ChatColor.RED + "This player is already a member! To remove them from the guild, type /kick <player>");
        } else if(exR == 3 && plR == 2){
            setGuildRanking(target, plR - 1);
            sendGuildMessage(getGuildId(target), ChatColor.DARK_RED + target.getName() + ChatColor.RED + " has been demoted to " + (getGuildRanking(target) == 2 ? ChatColor.GOLD + "MODERATOR!" : getGuildRanking(target) == 1 ? ChatColor.GOLD + "MEMBER!" : ChatColor.DARK_RED + "ERROR: CONTACT SERVER ADMIN."), false);
        } else if (exR == 3 && (plR == 3 || plR == 4)){
            executor.sendMessage(ChatColor.RED + "You can not demote this player!");
        } else if (exR == 2 || exR == 1 || exR == 0){
            executor.sendMessage(ChatColor.RED + "You can not demote players!");
        }
    }

    public static void joinGuild(Player player, int guildId)throws SQLException, ClassNotFoundException{
        SQLUtil.update(connection, "users", "guild_id", guildId, new SQLUtil.Where(new SQLUtil.WhereVar("name", player.getName()).getWhere()));

        SQLUtil.update(connection, "users", "guild_ranking", 1, new SQLUtil.Where(new SQLUtil.WhereVar("name", player.getName()).getWhere()));
    }

    public static void kickPlayer(Player player) throws SQLException, ClassNotFoundException {
        String name = getGuildName(player);
        int id = getGuildId(player);
        SQLUtil.update(connection, "users", "guild_ranking", 0, new SQLUtil.Where(new SQLUtil.WhereVar("name", player.getName()).getWhere()));
        SQLUtil.update(connection, "users", "guild_id", 0, new SQLUtil.Where(new SQLUtil.WhereVar("name", player.getName()).getWhere()));
        player.sendMessage(ChatColor.RED + "You have been kicked from the guild " + ChatColor.DARK_RED + name + ChatColor.RED + "!");
        sendGuildMessage(id, ChatColor.DARK_RED + player.getName() + " has been kicked from the guild!", false);
    }

    public static void setMaxPlayers(int players, int guildId) throws SQLException, ClassNotFoundException {
        SQLUtil.update(connection, "guilds", "max_players", players, new SQLUtil.Where(new SQLUtil.WhereVar("id", guildId).getWhere()));
    }

    public static void setGuildOwner(Player player, int guildId) throws SQLException, ClassNotFoundException {
        SQLUtil.update(connection, "guilds", "owner", player.getName(), new SQLUtil.Where(new SQLUtil.WhereVar("id", guildId).getWhere()));
    }

    public static ArrayList<Player> getPlayersInGuild(int guildId) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(connection, "users", "name", new SQLUtil.Where(new SQLUtil.WhereVar("guild_id", guildId).getWhere()));
        set.next();
        ArrayList<Player> ps = new ArrayList<>();
        ArrayList<String> st = new ArrayList<>();
        for(String s : st){
            ps.add(Bukkit.getPlayer(s));
        }
        while(set.next()){
            st.add(set.getString("name"));
        }
        return ps;
    }
    //4 == Owner, 3 == Administrator, 2 == Moderator, 1 == Member
    public static int getGuildRanking(Player player) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(connection, "users", "guild_ranking", new SQLUtil.Where(new SQLUtil.WhereVar("name", player.getName()).getWhere()));
        set.next();
        return set.getInt("guild_ranking");
    }
    //4 == Owner, 3 == Administrator, 2 == Moderator, 1 == Member
    public static void setGuildRanking(Player player, int ranking) throws SQLException, ClassNotFoundException {
        SQLUtil.update(connection, "users", "guild_ranking", ranking, new SQLUtil.Where(new SQLUtil.WhereVar("name", player.getName()).getWhere()));
    }

    public static int getAmountOfPlayers(int guildId) throws SQLException, ClassNotFoundException {
        return getPlayersInGuild(guildId).size();
    }
    //Send a message to every player of a specified guild
    public static void sendGuildMessage(int guildId, String message, boolean sound) throws SQLException, ClassNotFoundException {
        ArrayList<Player> players = getPlayersInGuild(guildId);
        for (Player player : players){
            player.sendMessage(message);
            if(sound){
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 1, 1);
            }
        }
    }

    public static void setAmountOfClaimedIslands(int islands, int guildId) throws SQLException, ClassNotFoundException {
        SQLUtil.update(connection, "guilds", "islands_claimed", islands, new SQLUtil.Where(new SQLUtil.WhereVar("id", guildId).getWhere()));
    }

    public static int getAmountOfClaimedIslands(int guildId) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.query(connection, "guilds", "islands_claimed", new SQLUtil.Where(new SQLUtil.WhereVar("id", guildId).getWhere()));
        set.next();
        return set.getInt("islands_claimed");
    }
}
