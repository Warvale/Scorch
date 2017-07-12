package net.warvale.vanquish.commands;

import net.warvale.vanquish.Main;
import net.warvale.vanquish.exceptions.CommandException;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class AbstractCommand extends Parser {
    private String name, usage;

    /**
     * Constructor for the uhc command super class.
     *
     * @param name The name of the command.
     * @param usage the command usage (after /command)
     */
    public AbstractCommand(String name, String usage) {
        this.usage = usage;
        this.name = name;
    }

    protected Main plugin;

    /**
     * Setup the instances needed.
     *
     * @param plugin The plugin instance.
     */
    protected void setupInstances(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Get the name of the command used after the /
     *
     * @return The command name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the usage of the command
     * <p>
     * Usage can be /nameofcommand [argurments...]
     *
     * @return The command usage.
     */
    public String getUsage() {
        return "/" + name + " " + usage;
    }

    /**
     * Return the permission of the command
     * <p>
     * The permission will be warvale.[nameofcommand]
     *
     * @return The command permission.
     */
    public String getPermission() {
        return "warvale." + name;
    }

    /**
     * Execute the command.
     *
     * @param sender The sender of the command.
     * @param args The argurments typed after the command.
     * @return True if successful, false otherwise. Returning false will send usage to the sender.
     *
     * @throws CommandException If anything was wrongly typed this is thrown sending the sender a warning.
     */
    public abstract boolean execute(CommandSender sender, String[] args) throws CommandException;

    /**
     * Tab complete the command.
     *
     * @param sender The sender of the command.
     * @param args The argurments typed after the command
     * @return A list of tab completable argurments.
     */
    public abstract List<String> tabComplete(CommandSender sender, String[] args);

    /**
     * Turn a the given boolean into "Enabled" or "Disabled".
     *
     * @param converting The boolean converting.
     * @return The converted boolean.
     */
    public String booleanToString(boolean converting) {
        return converting ? "enabled" : "disabled";
    }
}
