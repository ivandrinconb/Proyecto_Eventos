package co.edu.uniquindio.concierto;


import co.edu.uniquindio.concierto.controller.EventoController;
import co.edu.uniquindio.concierto.model.Evento;

import java.time.LocalDateTime;
import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        EventoController controller = new EventoController ();

        Evento evento = new Evento();
        evento.setIdEvento("1");
        evento.setNombre("Concierto de rap");
        evento.setCiudad("Armenia");

        controller.crearEvento (evento);
        System.out.println(controller.getEventos().size());

        Evento encontrado = controller.buscarEventoPorNombre("Concierto de rap");
        if (encontrado != null){
            System.out.println("Evento encontrado: " + encontrado.getNombre());
        }
        List<Evento> eventos = controller.filtrarPorCiudad("Armenia");
        for (Evento e : eventos){
            System.out.println("Lugar del evento: " + e.getCiudad());
        }
    }

}

