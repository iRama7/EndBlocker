package rama.endblock;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.scheduler.BukkitScheduler;
import rama.endblock.worldblock.WorldBlockMain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static rama.endblock.PAPIExpansion.timer;

public class CalendarEvent implements Listener {
    private EndBlock plugin;

    public CalendarEvent(EndBlock plugin) {
        this.plugin = plugin;
    }

    //Con una fecha dada en d hh:mm:ss
    //calcular cuánto falta para 6 23:59:59
    //6 - 4 = 2
    //23-23 = 0
    //59-33 = 26
    //59-20 = 39
    public static int bandera = 0;

    @EventHandler
    public void plEnableEvent(PluginEnableEvent e){

        if(e.getPlugin().equals(plugin)) {


            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.runTaskTimerAsynchronously(plugin, () -> {
                Calendar calendar = Calendar.getInstance();
                TimeZone tz = TimeZone.getTimeZone("America/Argentina/Buenos_Aires");
                calendar.setTimeZone(tz);
                int dia_actual = calendar.get(Calendar.DAY_OF_WEEK);
                int hora_actual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutos_actuales = calendar.get(Calendar.MINUTE);
                int segundos_actuales = calendar.get(Calendar.SECOND);

                //6-7 = -1

                int dia_apertura = 6;
                int hora_apertura = 23;
                int minutos_apertura = 59;
                int segundos_apertura = 59;

                int dias_restantes = dia_apertura - dia_actual;

                int horas_restantes = hora_apertura - hora_actual;
                int minutos_restantes = minutos_apertura - minutos_actuales;
                int segundos_restantes = segundos_apertura - segundos_actuales;


                if(!plugin.getConfig().getBoolean("load_unload-check") && dia_actual == 1 && hora_actual == 23 && minutos_actuales == 59 && segundos_actuales == 59){
                    if(bandera == 0){
                        WorldBlockMain.unloadEnd(plugin);
                        bandera = 1;
                    }
                }

                if(dia_actual == 7) {
                    timer = "&aABIERTO&7";

                }else if(dia_actual == 1 && hora_actual < 23 && minutos_actuales < 59 && segundos_actuales < 59){
                    timer = "&aABIERTO&7";

                }else if(dias_restantes > 0){
                    timer = dias_restantes+"d "+horas_restantes+"h "+minutos_restantes+"m "+segundos_restantes+"s";

                }else{
                    timer = horas_restantes + "h " + minutos_restantes + "m " + segundos_restantes + "s";
                    if(timer.equals("0h 0m 0s")){
                        if(bandera == 0){
                            WorldBlockMain.loadEnd(plugin);
                            bandera = 1;
                        }
                    }
                }

            }, 1L, 1L);
        }
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimerAsynchronously(plugin, () -> {
            bandera = 0;
        }, 20L, 20l);
    }


}
