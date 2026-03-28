package co.edu.uniquindio.concierto.model;

public class ServicioAdicional {
    private String idServicio;
    private String nombre;
    private String descripcion;
    private double costo;

    public ServicioAdicional(String idServicio, String nombre, String descripcion, double costo) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;

    }
    public String getIdServicio() {
        return idServicio;
    }
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getCosto() {
        return costo;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }

}
