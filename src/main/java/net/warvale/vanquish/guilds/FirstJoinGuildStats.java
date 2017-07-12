package main.java.net.warvale.vanquish.guilds;

import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.net.warvale.vanquish.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoinGuildStats implements Listener {

	/* Created by Tricks */

	private Main plugin;

	public FirstJoinGuildStats(Main pl) {
		plugin = pl;

	}

	@EventHandler
	public void onFirstJoin(PlayerJoinEvent event) {
		Player player = (Player) event.getPlayer();

		if (!player.hasPlayedBefore()) {

			/* This may come in handy later */
			plugin.getConfig().set("Player-Data." + player.getUniqueId().toString() + ".BasicInformation.DateJoined", new SimpleDateFormat("dd/MM/yy").format(new Date()));

			/* Status for if players are in a Guild or not (Booleans) */
			plugin.getConfig().set("Player-Data." + player.getUniqueId().toString() + ".InGuild", false);

			plugin.saveConfig();

		}
	}	
}
