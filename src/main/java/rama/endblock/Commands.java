package rama.endblock;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static rama.endblock.EndBlock.*;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("endblock.*")) {
            if (args[0].equalsIgnoreCase("load")) {
                if (getEndState(plugin)) {
                    sender.sendMessage("El end ya está abierto!");
                }
                loadEnd(plugin);
            } else if (args[0].equalsIgnoreCase("unload")) {
                if (!getEndState(plugin)) {
                    sender.sendMessage("El end ya está cerrado!");
                }
                unloadEnd(plugin);
            } else if (args[0].equalsIgnoreCase("check")) {
                sender.sendMessage("Está el end cargado? " + getEndState(plugin));
            }
            return false;
        }
        return false;
    }
}
