package rama.endblock;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Calendar;
import java.util.TimeZone;

import static rama.endblock.EndBlock.*;
import static rama.endblock.PAPIExpansion.timer;
import static rama.endblock.PAPIExpansion.timer_cierre;

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

    @EventHandler
    public void plEnableEvent(PluginEnableEvent e){

        if(e.getPlugin().equals(plugin)) {

            int dia_apertura = 6;
            int hora_apertura = 23;
            int minutos_apertura = 59;
            int segundos_apertura = 59;

            int dia_cierre = 1;
            int hora_cierre = 23;
            int minutos_cierre = 59;
            int segundos_cierre = 59;

            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.runTaskTimerAsynchronously(plugin, () -> {
                Calendar calendar = Calendar.getInstance();
                TimeZone tz = TimeZone.getTimeZone("America/Argentina/Buenos_Aires");
                calendar.setTimeZone(tz);
                int dia_actual = calendar.get(Calendar.DAY_OF_WEEK);
                int hora_actual = calendar.get(Calendar.HOUR_OF_DAY);
                int minutos_actuales = calendar.get(Calendar.MINUTE);
                int segundos_actuales = calendar.get(Calendar.SECOND);



                int dias_restantes = dia_apertura - dia_actual;

                int horas_restantes = hora_apertura - hora_actual;
                int minutos_restantes = minutos_apertura - minutos_actuales;
                int segundos_restantes = segundos_apertura - segundos_actuales;


                if(dias_restantes > 0){
                    if(getEndState(plugin)){
                        timer = "&aABIERTO";
                    }else{
                        timer = dias_restantes+"d "+horas_restantes+"h "+minutos_restantes+"m "+segundos_restantes+"s";
                    }

                }else{
                    if(getEndState(plugin)){
                        timer = "&aABIERTO";
                    }else {
                        timer = horas_restantes + "h " + minutos_restantes + "m " + segundos_restantes + "s";
                    }
                    if(timer.equals("0h 0m 0s")){
                        if(!getEndState(plugin)){
                            loadEnd(plugin);
                        }
                    }
                }
                int dias_restantes_cierre;
                switch (dia_actual){
                    case 1:
                        dias_restantes_cierre = 0;
                        break;
                    case 2:
                        dias_restantes_cierre = 6;
                        break;
                    case 3:
                        dias_restantes_cierre = 5;
                        break;
                    case 4:
                        dias_restantes_cierre = 4;
                        break;
                    case 5:
                        dias_restantes_cierre = 3;
                        break;
                    case 6:
                        dias_restantes_cierre = 2;
                        break;
                    case 7:
                        dias_restantes_cierre = 1;
                        break;
                    default:
                        dias_restantes_cierre = 10;
                }

                int horas_restantes_cierre = hora_cierre - hora_actual;
                int minutos_restantes_cierre = minutos_cierre - minutos_actuales;
                int segundos_restantes_cierre = segundos_cierre - segundos_actuales;
                if(dias_restantes_cierre > 0) {
                    timer_cierre = dias_restantes_cierre + "d " + horas_restantes_cierre + "h " + minutos_restantes_cierre + "m " + segundos_restantes_cierre + "s";
                }else{
                    timer_cierre = horas_restantes_cierre + "h " + minutos_restantes_cierre + "m " + segundos_restantes_cierre + "s";
                }
                if(timer.equals("0h 0m 0s")){
                    if(getEndState(plugin)){
                        unloadEnd(plugin);
                    }
                }



            }, 1L, 1L);
        }
    }


}
