package rama.endblock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import rama.endblock.worldblock.WorldBlockMain;

import java.io.File;

public final class EndBlock extends JavaPlugin {

    public String rutaConfig;

    @Override
    public void onEnable() {
        registerEvents();
        log();
        registerConfig();
        WorldBlockMain.setEndStatus(this);

        new PAPIExpansion(this).register();
    }

    @Override
    public void onDisable() {
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new worldSwitchEvent(),this);
        pm.registerEvents(new CalendarEvent(this), this);
    }
    public void log(){
        String prefix = ChatColor.translateAlternateColorCodes('&', "&3[&cEndBlock&3] &r");
        String msg1 = ChatColor.translateAlternateColorCodes('&', "&eCargando EndBlock por ImRama...");
        Bukkit.getConsoleSender().sendMessage(prefix+msg1);
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
}
