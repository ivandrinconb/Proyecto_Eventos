package co.edu.uniquindio.concierto.model.clases;

import co.edu.uniquindio.concierto.model.Enums.CategoriaEvento;
import co.edu.uniquindio.concierto.model.Enums.EstadoEvento;
import co.edu.uniquindio.concierto.model.interfaces.IEvento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento implements IEvento {
    private String idEvento;
    private String nombre;
    private CategoriaEvento categoria;
    private String descripcion;
    private String ciudad;
    private LocalDateTime fechaHora;
    private EstadoEvento estadoEvento;
    private String politicas;
    private Recinto recinto;
    private List<Usuario> usuariosAsistentes;
    private List<Zona> zonas;


    public Evento(String idEvento, String nombre, CategoriaEvento categoria, String descripcion,
                  String ciudad, LocalDateTime fechaHora, EstadoEvento estadoEvento, String politicas,
                  Recinto recinto) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.fechaHora = fechaHora;
        this.estadoEvento = estadoEvento;
        this.politicas = politicas;
        this.recinto = recinto;
        this.usuariosAsistentes = new ArrayList<>();
        this.zonas = new ArrayList<>();
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaEvento getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEvento categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EstadoEvento estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public String getPoliticas() {
        return politicas;
    }

    public void setPoliticas(String politicas) {
        this.politicas = politicas;
    }

    public Recinto getRecinto() {
        return recinto;
    }

    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }

    public List<Usuario> getUsuariosAsistentes() {
        return usuariosAsistentes;
    }

    public void setUsuariosAsistentes(List<Usuario> usuariosAsistentes) {
        this.usuariosAsistentes = usuariosAsistentes;
    }

    public List<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }


    @Override
    public void crearEvento() {

    }

    @Override
    public void actualizarEvento() {

    }

    @Override
    public void eliminarEvento() {

    }

    @Override
    public void cancelarEvento() {

    }

    @Override
    public void pausarEvento() {

    }

    @Override
    public void consultarEvento() {

    }
    @Override
    public void consultarDisponibilidad() {

    }

    @Override
    public void publicarEvento() {

    }
}
