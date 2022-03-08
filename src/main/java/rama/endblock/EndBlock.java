package rama.endblock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static org.bukkit.Sound.ENTITY_ENDER_DRAGON_GROWL;

public final class EndBlock extends JavaPlugin {

    public String rutaConfig;

    public static Plugin plugin;

    @Override
    public void onEnable() {
        registerEvents();
        log("Loading EndBlock by ImRama");
        registerConfig();
        registerCommands();
        if(!getEndState(this)){
            unloadEnd(this);
            log("Cerrando el mundo del end ya que estaba cerrado antes de apagar el servidor.");
        }
        plugin = this;

        new PAPIExpansion(this).register();
        log("Registering PlaceHolderAPI expansion");
    }

    @Override
    public void onDisable() {
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new worldSwitchEvent(),this);
        pm.registerEvents(new CalendarEvent(this), this);
    }

    public void registerCommands(){
        this.getCommand("endblock").setExecutor(new Commands());
    }

    public static void log(String message){
        String prefix = ChatColor.translateAlternateColorCodes('&', "&3[&cEndBlock&3] &r");
        Bukkit.getConsoleSender().sendMessage(prefix+ChatColor.translateAlternateColorCodes('&', message));
    }

    public void registerConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        rutaConfig = config.getPath();
        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cEndBlock&3] &eCargando config.yml..."));
    }

    public static Boolean getEndState(Plugin pl){
        return pl.getConfig().getBoolean("isEndLoaded");
    }

    public static void setEndState(Plugin pl, Boolean state){
        pl.getConfig().set("isEndLoaded", state);
    }

    public static void loadEnd(Plugin pl){

        Bukkit.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "mv load world_the_end");
        Bukkit.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "hd reload");
        Bukkit.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "jumppad reload");
        log("&c&lAVISO&e Se ha cargado el mundo del end.");
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7El &eEND &7se encuentra abierto"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cCerrará &7el &8Domingo &7a las &823:59:59"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7"));

        for(Player player : Bukkit.getOnlinePlayers()){
            player.playSound(player.getLocation(), ENTITY_ENDER_DRAGON_GROWL, 1,1);
        }
        setEndState(plugin, true);
        pl.saveConfig();
    }

    public static void unloadEnd(Plugin pl) {

        Bukkit.getServer().dispatchCommand(pl.getServer().getConsoleSender(), "mv unload world_the_end");
        log("&c&lAVISO&e Se ha descargado el mundo del end.");
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7El &eEND &7se encuentra &ccerrado"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aAbrirá &7el &8Viernes &7a las &823:59:59"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7"));
        setEndState(plugin, false);
        pl.saveConfig();

    }
}
