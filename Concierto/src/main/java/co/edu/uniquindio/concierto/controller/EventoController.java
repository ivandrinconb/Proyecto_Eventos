package co.edu.uniquindio.concierto.controller;

import co.edu.uniquindio.concierto.model.clases.Evento;

import java.util.ArrayList;
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
    // Obtener eventos
    public List<Evento> getEventos (){
        return sistema.getEventos();
    }
    // Buscar evento por el nombre
    public Evento buscarEventoPorNombre(String nombre){
        for (Evento evento : sistema.getEventos()){
            if (evento.getNombre().equalsIgnoreCase(nombre)){
                return evento;
            }
        }
        return null;
    }
    // Filtro por ciudad
    public List<Evento> filtrarPorCiudad (String ciudad){
        List<Evento> resultado = new ArrayList<>();

        for (Evento evento : sistema.getEventos()){
            if (evento.getCiudad().equalsIgnoreCase(ciudad)){
                resultado.add(evento);
            }
        }
        return resultado;
    }
}
