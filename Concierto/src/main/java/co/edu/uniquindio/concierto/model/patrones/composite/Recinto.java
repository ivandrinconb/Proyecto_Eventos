package co.edu.uniquindio.concierto.model.patrones.composite;

import java.util.List;
import java.util.UUID;

public class Recinto implements ComponenteRecinto {
    private String idRecinto;
    private String nombre;
    private String direccion;
    private String ciudad;
    private int Capacidad;
    private List<Zona> zonas;

    public Recinto( String nombre, String direccion, String ciudad, int capacidad) {
        this.idRecinto = generarIdCorto();
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.Capacidad = capacidad;

    }

    public Recinto(String nombre, String direccion,
                   String ciudad,  List<Zona> zonas) {
        this.idRecinto = generarIdCorto();
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.zonas = zonas;

    }
    @Override
    public void mostrar() {
        String detalle = "Recinto: " + nombre + " (" + ciudad + ")";
        System.out.println(detalle); // luego lo reemplazas por actualización en JavaFX
        for (Zona zona : zonas) {
            zona.mostrar();
        }
    }

    private String generarIdCorto() {
        return UUID.randomUUID().toString().substring(0, 5);
    }












    public String getIdRecinto() {
        return idRecinto;
    }
    public void setIdRecinto(String idRecinto) {
        this.idRecinto = idRecinto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public List<Zona> getZonas() {
        return zonas;
    }
    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int capacidad) {
        Capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Recinto{" +
                "idRecinto='" + idRecinto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", Capacidad=" + Capacidad +
                ", zonas=" + zonas +
                '}';
    }
}
