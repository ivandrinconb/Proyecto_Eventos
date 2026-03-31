package co.edu.uniquindio.concierto.controller;

import co.edu.uniquindio.concierto.model.Evento;

import java.util.List;

public class EventoController {
    private SistemaController sistema;

    public EventoController() {
        sistema = SistemaController.getInstance();
    }
    // Crear evento
    public void crearEvento (Evento evento){
        sistema.agregarEvento(evento);
    }
    //
    public List<Evento> getEventos (){
        return sistema.getEventos();
    }
}
