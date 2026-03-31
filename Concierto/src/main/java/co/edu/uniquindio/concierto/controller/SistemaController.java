package co.edu.uniquindio.concierto.controller;

import co.edu.uniquindio.concierto.model.Compra;
import co.edu.uniquindio.concierto.model.Evento;
import co.edu.uniquindio.concierto.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SistemaController {
    private static SistemaController instans;

    private List<Usuario> listUsuarios;
    private List<Evento> listEventos;
    private List<Compra> listCompras;

    private SistemaController () {
        listUsuarios = new ArrayList<>();
        listEventos = new ArrayList<>();
        listCompras = new ArrayList<>();
    }
    public static SistemaController getInstance() {
        if (instans == null) {
            instans = new SistemaController();

        }
        return instans;
    }
    // Metdodos basicos para eventos.
    public void agregarEvento (Evento evento){
        listEventos.add (evento);
    }
    public List<Evento> getEventos () {
        return listEventos;
    }
}
