package co.edu.uniquindio.concierto.controller;

import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Evento;
import co.edu.uniquindio.concierto.model.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SistemaController {
    private static SistemaController instance;

    private List<Usuario> listUsuarios;
    private List<Evento> listEventos;
    private List<Compra> listCompras;

    private SistemaController () {
        listUsuarios = new ArrayList<>();
        listEventos = new ArrayList<>();
        listCompras = new ArrayList<>();
    }
    public static SistemaController getInstance() {
        if (instance == null) {
            instance = new SistemaController();

        }
        return instance;
    }

    public void agregarEvento (Evento evento){
        listEventos.add (evento);
    }
    public List<Evento> getEventos () {
        return listEventos;
    }
    public void agregarUsuario(Usuario usuario) {
        listUsuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {
        return listUsuarios;
    }

    public void agregarCompra(Compra compra) {
        listCompras.add(compra);
    }

    public List<Compra> getCompras() {
        return listCompras;
    }

}
