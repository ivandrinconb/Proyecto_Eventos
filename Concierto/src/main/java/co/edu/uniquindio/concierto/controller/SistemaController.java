package co.edu.uniquindio.concierto.controller;

import co.edu.uniquindio.concierto.model.clases.Administrador;
import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Evento;
import co.edu.uniquindio.concierto.model.patrones.composite.Asiento;
import co.edu.uniquindio.concierto.model.patrones.composite.Recinto;
import co.edu.uniquindio.concierto.model.patrones.composite.Zona;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SistemaController {
    private static SistemaController instance;

    private List<Usuario> listUsuarios;
    private List<Administrador> listAdministradores;
    private List<Evento> listEventos;
    private List<Compra> listCompras;
    private List<Recinto> listRecintos;
    private List<Zona> listZonas;
    private List<Asiento> listAsientos;

    private SistemaController () {
        listUsuarios = new ArrayList<>();
        listEventos = new ArrayList<>();
        listCompras = new ArrayList<>();
        listAdministradores = new ArrayList<>();
        listZonas = new ArrayList<>();
        listAsientos = new ArrayList<>();
        listRecintos = new ArrayList<>();

        Administrador admin = new Administrador(
                "0000",
                "Ivan",
                "ivan@.com",
                "0000",
                "3117899169"
        );
        listAdministradores.add(admin);






    }
    public static SistemaController getInstance() {
        if (instance == null) {
            instance = new SistemaController();

        }
        return instance;
    }

    public static void setInstance(SistemaController instance) {
        SistemaController.instance = instance;
    }

    public List<Asiento> getListAsientos() {
        return listAsientos;
    }

    public void setListAsientos(List<Asiento> listAsientos) {
        this.listAsientos = listAsientos;
    }

    public List<Zona> getListZonas() {
        return listZonas;
    }

    public void setListZonas(List<Zona> listZonas) {
        this.listZonas = listZonas;
    }

    public List<Compra> getListCompras() {
        return listCompras;
    }

    public void setListCompras(List<Compra> listCompras) {
        this.listCompras = listCompras;
    }

    public List<Evento> getListEventos() {
        return listEventos;
    }

    public void setListEventos(List<Evento> listEventos) {
        this.listEventos = listEventos;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Recinto> getListRecintos() {
        return listRecintos;
    }

    public void setListRecintos(List<Recinto> listRecintos) {
        this.listRecintos = listRecintos;
    }

    public List<Administrador> getListAdministradores() {
        return listAdministradores;
    }

    public void setListAdministradores(List<Administrador> listAdministradores) {
        this.listAdministradores = listAdministradores;
    }

    public void agregarEvento (Evento evento){
        listEventos.add (evento);
    }
    public List<Evento> getEventos () {
        return listEventos;
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


    public Usuario buscarUsuarioPorCredenciales(String correo, String password) {
        return listUsuarios.stream()
                .filter(u -> u.getCorreoElectronico() != null
                        && u.getCorreoElectronico().equalsIgnoreCase(correo)
                        && u.getPassword() != null
                        && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

    }
    public Administrador buscarAdministradorPorCredenciales(String correo, String password) {
        return listAdministradores.stream()
                .filter(u -> u.getCorreoElectronico() != null
                        && u.getCorreoElectronico().equalsIgnoreCase(correo)
                        && u.getPassword() != null
                        && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }


    public void agregarUsuario(Usuario usuario) {
        listUsuarios.add(usuario); }

    public void eliminarUsuario(Usuario usuario) { listUsuarios.remove(usuario); }

    public void actualizarUsuario(Usuario viejo, Usuario nuevo) {
        listUsuarios.remove(viejo);
        listUsuarios.add(nuevo);
    }
    public void agregarZona(Zona zona) {
        listZonas.add(zona);
    }

    public void eliminarZona(Zona zona) {
        listZonas.remove(zona);
    }
    public void agregarRecinto(Recinto recinto) {
        listRecintos.add(recinto);
    }



}
