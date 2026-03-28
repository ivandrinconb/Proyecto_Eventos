package co.edu.uniquindio.concierto.model;

import java.util.List;

public class Zona {
    private String idZona;
    private String nombre;
    private int capacidad;
    private double precioBase;
    private List<Asiento> asientos;

    public Zona(String idZona, String nombre, int capacidad,
                double precioBase,  List<Asiento> asientos) {
        this.idZona = idZona;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.asientos = asientos;

    }
    public String getIdZona() {
        return idZona;
    }
    public void setIdZona(String idZona) {
        this.idZona = idZona;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public double getPrecioBase() {
        return precioBase;
    }
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
    public List<Asiento> getAsientos() {
        return asientos;
    }
    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    @Override
    public String toString() {
        return "Zona =" +
                "idZona=" + idZona + '\'' +
                " nombre=" + nombre + '\'' +
                " capacidad=" + capacidad +
                " precioBase=" + precioBase +
                " asientos=" + asientos ;
    }
}
