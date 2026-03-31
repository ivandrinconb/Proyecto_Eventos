package co.edu.uniquindio.concierto;


import co.edu.uniquindio.concierto.controller.EventoController;
import co.edu.uniquindio.concierto.model.Evento;

import java.time.LocalDateTime;

public class Launcher {
    public static void main(String[] args) {
        EventoController controller = new EventoController ();
        Evento evento = new Evento("01", "Emminen", null, "Evento de prueba", "Armenia", LocalDateTime.now(), null, "Sin reembolso", null);
        controller.crearEvento (evento);
        System.out.println(controller.getEventos().size());
    }

}

