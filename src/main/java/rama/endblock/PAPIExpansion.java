package rama.endblock;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PAPIExpansion extends PlaceholderExpansion {

    private EndBlock plugin;

    public PAPIExpansion(EndBlock plugin) {
        this.plugin = plugin;
    }

    public static String timer;
    public static String timer_cierre;

    @Override
    public @NotNull String getIdentifier() {
        return "EndBlock";
    }

    @Override
    public @NotNull String getAuthor() {
        return "ImRama";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }


    @Override
    public @Nullable String onPlaceholderRequest(Player player, String params) {
        if(params.equals("timer")){
            return timer;
        }else if(params.equals("timer_dragon")){
            String dragonslayer_timer = PlaceholderAPI.setPlaceholders(player, "%dragonslayer_nexttimehms%");
            if(dragonslayer_timer.equalsIgnoreCase("not yet")){
                return "&5&lGRIFFIN &7está vivo!";
            }else{
                return dragonslayer_timer;
            }
        }else if(params.equals("timer_cierre")){
            return timer_cierre;
        }
        return null;
    }
}
