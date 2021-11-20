package rama.endblock.worldblock;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import rama.endblock.EndBlock;

public class Command implements CommandExecutor {

    private EndBlock plugin;

    public Command(EndBlock plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(args.length==0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cEndBlock&3] &7/endblock unloadEnd"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cEndBlock&3] &7/endblock loadEnd"));
        }else if(args[0].equalsIgnoreCase("unloadEnd")){
            WorldBlockMain.unloadEnd(plugin);
        }else if(args[0].equalsIgnoreCase("loadEnd")){
            WorldBlockMain.loadEnd(plugin);
        }
        return false;
    }
}
