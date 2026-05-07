package co.edu.uniquindio.concierto.model.patrones.composite;

import java.util.List;

public class Recinto implements ComponenteRecinto {
    private String idRecinto;
    private String nombre;
    private String direccion;
    private String ciudad;
    private List<Zona> zonas;

    public Recinto(String idRecinto, String nombre, String direccion,
                   String ciudad,  List<Zona> zonas) {
        this.idRecinto = idRecinto;
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

    @Override
    public String toString() {
        return "Recinto =" +
                "idRecinto=" + idRecinto + '\'' +
                " nombre=" + nombre + '\'' +
                " direccion=" + direccion + '\'' +
                " ciudad=" + ciudad + '\'' +
                " zonas=" + zonas ;
    }
}
