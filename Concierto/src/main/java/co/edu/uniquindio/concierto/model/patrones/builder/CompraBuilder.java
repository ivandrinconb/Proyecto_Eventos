package co.edu.uniquindio.concierto.model.patrones.builder;

import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.clases.*;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompraBuilder {
    private String idCompra;
    private Usuario usuario;
    private Evento evento;
    private LocalDateTime fechaCreacion;
    private double total;
    private EstadoCompra estadoCompra;
    private List<Entrada> entradas = new ArrayList<>();
    private List<ServicioAdicional> serviciosAdicionales = new ArrayList<>();

    public CompraBuilder setIdCompra(String idCompra) {
        this.idCompra = idCompra;
        return this;
    }

    public CompraBuilder setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public CompraBuilder setEvento(Evento evento) {
        this.evento = evento;
        return this;
    }

    public CompraBuilder setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public CompraBuilder setEstadoCompra(EstadoCompra estadoCompra) {
        this.estadoCompra = estadoCompra;
        return this;
    }

    public CompraBuilder addEntrada(Entrada entrada) {
        this.entradas.add(entrada);
        return this;
    }

    public CompraBuilder addServicioAdicional(ServicioAdicional servicio) {
        this.serviciosAdicionales.add(servicio);
        return this;
    }
    public Compra build() {
        Compra compra = new Compra(
                idCompra,
                usuario,
                evento,
                fechaCreacion != null ? fechaCreacion : LocalDateTime.now(),
                total,
                estadoCompra != null ? estadoCompra : EstadoCompra.CREADA,
                entradas,
                serviciosAdicionales
        );
        compra.calcularTotal(); // recalcula el total automáticamente
        return compra;
    }
}