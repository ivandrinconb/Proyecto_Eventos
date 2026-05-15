package co.edu.uniquindio.concierto.model.clases;

import co.edu.uniquindio.concierto.model.Enums.EstadoEntrada;
import co.edu.uniquindio.concierto.model.patrones.decorator.IEntrada;
import co.edu.uniquindio.concierto.model.patrones.composite.Asiento;
import co.edu.uniquindio.concierto.model.patrones.composite.Zona;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class Entrada implements IEntrada {
    private String idEntrada;
    private Zona zona;
    private Asiento asiento;
    private double precioFinal;
    private EstadoEntrada estadoEntrada;
    private List<ServicioAdicional> servicios; // ✅ nuevo atributo

    // Constructor principal
    public Entrada(String idEntrada, Zona zona, Asiento asiento, double precioFinal,
                   EstadoEntrada estadoEntrada) {
        this.idEntrada = idEntrada;
        this.zona = zona;
        this.asiento = asiento;
        this.precioFinal = precioFinal;
        this.estadoEntrada = estadoEntrada;
    }

    // Constructor con evento y usuario (puedes completarlo luego si lo necesitas)
    public Entrada(Evento evento, Usuario usuario, EstadoEntrada estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }

    // Constructor con servicios adicionales
    public Entrada(Zona zona, Asiento asiento, List<ServicioAdicional> servicios, double precioFinal) {
        this.idEntrada = java.util.UUID.randomUUID().toString().substring(0, 5);
        this.zona = zona;
        this.asiento = asiento;
        this.servicios = servicios;
        this.precioFinal = precioFinal;
        this.estadoEntrada = EstadoEntrada.DISPONIBLE;
    }


    public String getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(String idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public EstadoEntrada getEstadoEntrada() {
        return estadoEntrada;
    }

    public void setEstadoEntrada(EstadoEntrada estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }

    public List<ServicioAdicional> getServiciosList() {
        return servicios;
    }

    public void setServicios(List<ServicioAdicional> servicios) {
        this.servicios = servicios;
    }


    public String getServicios() {
        if (servicios == null || servicios.isEmpty()) {
            return "Ninguno";
        }
        return servicios.stream()
                .map(ServicioAdicional::getDescripcion)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getDescripcion() {
        return "Entrada " + idEntrada +
                " | Zona: " + (zona != null ? zona.getTipoZona() : "N/A") +
                " | Asiento: " + (asiento != null ? asiento.getIdAsiento() : "N/A") +
                " | Estado: " + estadoEntrada +
                " | Precio: " + precioFinal +
                " | Servicios: " + getServicios();
    }

    @Override
    public double getCosto() {
        return precioFinal;
    }

}