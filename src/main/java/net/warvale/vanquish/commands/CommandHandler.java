package main.java.net.warvale.vanquish.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

import main.java.net.warvale.vanquish.Main;
import main.java.net.warvale.vanquish.commands.admin.SetObsidianInLavaDecayCommand;
import main.java.net.warvale.vanquish.guilds.commands.GuildCommand;
import main.java.net.warvale.vanquish.utils.Broadcast;

public class CommandHandler implements CommandExecutor, TabCompleter {
    private final Main plugin;

    public CommandHandler(Main plugin) {
        this.plugin = plugin;
    }

    private List<AbstractCommand> cmds = new ArrayList<AbstractCommand>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        AbstractCommand command = getCommand(cmd.getName());

        if (command == null) { // this shouldn't happen, it only uses registered commands but incase.
            return true;
        }

        if (!sender.hasPermission(command.getPermission())) {
            Broadcast.toSender(sender, Broadcast.BroadcastType.FAILURE, "You do not have permission to execute this command!");
            return true;
        }

        try {
            if (!command.execute(sender, args)) {
                Broadcast.toSender(sender, Broadcast.BroadcastType.FAILURE, "Usage: " + command.getUsage());
            }
        } catch (CommandException ex) {
            Broadcast.toSender(sender, Broadcast.BroadcastType.FAILURE, ex.getMessage()); // send them the exception message
        } catch (Exception ex) {
            Broadcast.toSender(sender, Broadcast.BroadcastType.FAILURE, ex.getClass().getName() + ": " + ex.getMessage());
            ex.printStackTrace(); // send them the exception and tell the console the error if its not a command exception
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        AbstractCommand command = getCommand(cmd.getName());

        if (command == null) { // this shouldn't happen, it only uses registered commands but incase.
            return null;
        }

        if (!sender.hasPermission(command.getPermission())) {
            return null;
        }

        try {
            List<String> list = command.tabComplete(sender, args);

            // if the list is null, replace it with everyone online.
            if (list == null) {
                return null;
            }

            // I don't want anything done if the list is empty.
            if (list.isEmpty()) {
                return list;
            }

            List<String> toReturn = new ArrayList<String>();

            if (args[args.length - 1].isEmpty()) {
                for (String type : list) {
                    toReturn.add(type);
                }
            } else {
                for (String type : list) {
                    if (type.toLowerCase().startsWith(args[args.length - 1].toLowerCase())) {
                        toReturn.add(type);
                    }
                }
            }

            return toReturn;
        } catch (Exception ex) {
            Broadcast.toSender(sender, Broadcast.BroadcastType.FAILURE, ex.getClass().getName() + ": " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Get a uhc command.
     *
     * @param name The name of the uhc command
     * @return The Command if found, null otherwise.
     */
    protected AbstractCommand getCommand(String name) {
        for (AbstractCommand cmd : cmds) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                return cmd;
            }
        }

        return null;
    }

    /**
     * Register all the commands.
     */
    public void registerCommands() {

        // Admin
        cmds.add(new SetObsidianInLavaDecayCommand());

        // Basic
        cmds.add(new GuildCommand());

        for (AbstractCommand cmd : cmds) {
            PluginCommand pCmd = plugin.getCommand(cmd.getName());

            cmd.setupInstances(plugin);

            // if its null, broadcast the command name so I know which one it is (so I can fix it).
            if (pCmd == null) {
                Broadcast.toConsole(Level.WARNING, "Error from command: " + cmd.getName() + "!");
                continue;
            }

            pCmd.setExecutor(this);
            pCmd.setTabCompleter(this);
        }
    }
}
