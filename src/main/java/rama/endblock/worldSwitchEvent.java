package rama.endblock;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;


public class worldSwitchEvent implements Listener {
    @EventHandler
    public void worldSwitchEvent(PlayerChangedWorldEvent e){
        Player player = e.getPlayer();
        World world = player.getWorld();
        if(world.getName().equalsIgnoreCase("world_the_end")){
            if(!player.hasPermission("acceso.end")){
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                String command = "customwarps warp minas %player_name%".replaceAll("%player_name%", player.getName());
                Bukkit.dispatchCommand(console, command);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo has desbloqueado el &eEnd &caún!"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNecesitas ser al menos &6nivel 4&c."));

            }
        }
    }
}
