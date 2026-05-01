package co.edu.uniquindio.concierto.model.clases;

import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;

public class ServicioAdicional {
    private String idServicio;
    private TipoServicioAdicional tipoServicio;
    private String descripcion;
    private double costo;

    public ServicioAdicional(String idServicio, TipoServicioAdicional tipoServicio, String descripcion, double costo) {
        this.idServicio = idServicio;
        this.tipoServicio= tipoServicio;
        this.descripcion = descripcion;
        this.costo = costo;

    }
    public String getIdServicio() {
        return idServicio;
    }
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public TipoServicioAdicional getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicioAdicional tipoServicio) {
        this.tipoServicio = tipoServicio;
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
