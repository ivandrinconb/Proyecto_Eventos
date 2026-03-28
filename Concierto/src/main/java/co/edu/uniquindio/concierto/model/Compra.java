package co.edu.uniquindio.concierto.model;

import java.time.LocalDateTime;
import java.util.List;

public class Compra {
    private String idCompra;
    private Usuario usuario;
    private Evento evento;
    private LocalDateTime fechaCreacion;
    private double total;
    private EstadoCompra estadoCompra;
    private List<Entrada> entradas;
    private List<ServicioAdicional> serviciosAdicionales;

    public Compra(String idCompra, Usuario usuario, Evento evento,
                  LocalDateTime fechaCreacion, double total, EstadoCompra estadoCompra, List<Entrada> entradas,
                  List<ServicioAdicional> serviciosAdicionales) {
        this.idCompra = idCompra;
        this.usuario = usuario;
        this.evento = evento;
        this.fechaCreacion = fechaCreacion;
        this.total = total;
        this.estadoCompra = estadoCompra;
        this.entradas = entradas;
        this.serviciosAdicionales = serviciosAdicionales;

    }
    public String getIdCompra() {
        return idCompra;
    }
    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public EstadoCompra getEstadoCompra() {
        return estadoCompra;
    }
    public void setEstadoCompra(EstadoCompra estadoCompra) {
        this.estadoCompra = estadoCompra;
    }
    public List<Entrada> getEntradas() {
        return entradas;
    }
    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    @Override
    public String toString() {
        return "Compra=" +
                "idCompra=" + idCompra + '\'' +
                " usuario=" + usuario +
                " evento=" + evento +
                " fechaCreacion=" + fechaCreacion +
                " total=" + total +
                " estadoCompra=" + estadoCompra +
                " entradas=" + entradas +
                " serviciosAdicionales=" + serviciosAdicionales ;
    }
}
