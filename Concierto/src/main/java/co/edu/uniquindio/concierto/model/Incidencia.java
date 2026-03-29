package co.edu.uniquindio.concierto.model;

import java.time.LocalDateTime;
import java.util.List;

public class Incidencia implements IIncidencia {
    private String idIncidencia;
    private String tipoIncidencia;
    private String descripcionIncidencia;
    private LocalDateTime fechaIncidencia;
    private List<Usuario> usuarios;
    private List<Evento> eventos;
    private List<Compra> compras;

    public Incidencia(String idIncidencia, String tipoIncidencia, String descripcionIncidencia, LocalDateTime fechaIncidencia,
                      List<Usuario> usuarios, List<Evento> eventos, List<Compra> compras) {
        this.idIncidencia = idIncidencia;
        this.tipoIncidencia = tipoIncidencia;
        this.descripcionIncidencia = descripcionIncidencia;
        this.fechaIncidencia = fechaIncidencia;
        this.usuarios = usuarios;
        this.eventos = eventos;
        this.compras = compras;
    }
    public String getIdIncidencia() {
        return idIncidencia;
    }
    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }
    public String getTipoIncidencia() {
        return tipoIncidencia;
    }
    public void setTipoIncidencia(String tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }
    public String getDescripcionIncidencia() {
        return descripcionIncidencia;
    }
    public void setDescripcionIncidencia(String descripcionIncidencia) {
        this.descripcionIncidencia = descripcionIncidencia;
    }
    public LocalDateTime getFechaIncidencia() {
        return fechaIncidencia;
    }
    public void setFechaIncidencia(LocalDateTime fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public List<Evento> getEventos() {
        return eventos;
    }
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    public List<Compra> getCompras() {
        return compras;
    }
    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }


    @Override
    public void registrarIncidencia(TipoIncidencia tipo, String descripcion) {

    }

    @Override
    public void consultarIncidenciasPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {

    }

    @Override
    public void consultarIncidenciasPorTipo(TipoIncidencia tipo) {

    }
}
