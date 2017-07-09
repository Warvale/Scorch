package net.warvale.vanquish.utils;

import org.bukkit.ChatColor;

public class ChatUtils {
	 public static String scramble = ChatColor.MAGIC.toString();
	    public static String bold = ChatColor.BOLD.toString();
	    public static String strike = ChatColor.STRIKETHROUGH.toString();
	    public static String underline = ChatColor.UNDERLINE.toString();
	    public static String italics = ChatColor.ITALIC.toString();

	    public static String reset = ChatColor.RESET.toString();

	    public static String aqua = ChatColor.AQUA.toString();
	    public static String black = ChatColor.BLACK.toString();
	    public static String blue = ChatColor.BLUE.toString();
	    public static String daqua = ChatColor.DARK_AQUA.toString();
	    public static String dblue = ChatColor.DARK_BLUE.toString();
	    public static String dgray = ChatColor.DARK_GRAY.toString();
	    public static String dgreen = ChatColor.DARK_GREEN.toString();
	    public static String dpurple = ChatColor.DARK_PURPLE.toString();
	    public static String dred = ChatColor.DARK_RED.toString();
	    public static String gold = ChatColor.GOLD.toString();
	    public static String gray = ChatColor.GRAY.toString();
	    public static String green = ChatColor.GREEN.toString();
	    public static String purple = ChatColor.LIGHT_PURPLE.toString();
	    public static String red = ChatColor.RED.toString();
	    public static String white = ChatColor.WHITE.toString();
	    public static String yellow = ChatColor.YELLOW.toString();

	    public static String warning = ChatUtils.yellow + "[!]";

	    public static String divider = gold + "--------------------------------------";
	    public static String tab = "   ";

	    public static String center(String s)
	    {
	        int le = ( 70 - s.length() ) / 2;
	        String newS = "";
	        for ( int i = 0; i < le; i++ )
	        {
	            newS += " ";
	        }
	        newS += s;
	        for ( int i = 0; i < le; i++ )
	        {
	            newS +=  " ";
	        }
	        return newS;
	    }
}
