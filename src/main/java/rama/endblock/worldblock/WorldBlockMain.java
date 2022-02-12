package rama.endblock.worldblock;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitScheduler;
import rama.endblock.EndBlock;

import java.util.ArrayList;

import static org.bukkit.Sound.ENTITY_ENDER_DRAGON_GROWL;

public class WorldBlockMain{

    private EndBlock plugin;

    public WorldBlockMain(EndBlock plugin) {
        this.plugin = plugin;
    }

    public static void unloadEnd(Plugin pl) {
        FileConfiguration config = pl.getConfig();

        Bukkit.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "mv unload world_the_end");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lAVISO&e Se ha descargado el mundo del end."));
        config.set("load_unload-check", true);
        pl.saveConfig();

    }

    public static void loadEnd(Plugin pl) {
        FileConfiguration config = pl.getConfig();

        Bukkit.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "mv load world_the_end");
        Bukkit.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "hd reload");
        Bukkit.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "jumppad reload");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lAVISO&e Se ha cargado el mundo del end."));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7El &eEND &7se encuentra abierto"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cCerrará &7el &8Domingo &7a las &823:59:59"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7"));

        for(Player player : Bukkit.getOnlinePlayers()){
            player.playSound(player.getLocation(), ENTITY_ENDER_DRAGON_GROWL, 1,1);
        }
        config.set("load_unload-check", false);
        pl.saveConfig();
    }

    public static void setEndStatus(Plugin pl) {
        FileConfiguration config = pl.getConfig();

        if (config.getBoolean("load_unload-check")) {
            unloadEnd(pl);
        }
    }
}

