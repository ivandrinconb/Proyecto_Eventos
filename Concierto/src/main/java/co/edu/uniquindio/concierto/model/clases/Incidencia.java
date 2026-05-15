package co.edu.uniquindio.concierto.model.clases;

import co.edu.uniquindio.concierto.model.Enums.EstadoIncidencia;
import co.edu.uniquindio.concierto.model.interfaces.IIncidencia;
import co.edu.uniquindio.concierto.model.Enums.TipoIncidencia;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Incidencia  {
    private String idIncidencia;
    private Usuario usuario;
    private Evento evento;
    private String descripcion;
    private EstadoIncidencia estadoIncidencia;
    private LocalDate fechaReporte;
    private TipoIncidencia tipoIncidencia;

    public Incidencia(Usuario usuario, Evento evento, String descripcion,
                      EstadoIncidencia estado, LocalDate fechaReporte, TipoIncidencia tipoIncidencia) {
        this.idIncidencia = generarIdCorto();
        this.usuario = usuario;
        this.evento = evento;
        this.descripcion = descripcion;
        this.estadoIncidencia = estado;
        this.fechaReporte = fechaReporte;
        this.tipoIncidencia = tipoIncidencia;
    }
    public Incidencia(String idIncidencia, Usuario usuario, Evento evento, String descripcion,
                      EstadoIncidencia estado, LocalDate fechaReporte, TipoIncidencia tipoIncidencia) {
        this.idIncidencia = idIncidencia;
        this.usuario = usuario;
        this.evento = evento;
        this.descripcion = descripcion;
        this.estadoIncidencia = estado;
        this.fechaReporte = fechaReporte;
        this.tipoIncidencia = tipoIncidencia;
    }

    private String generarIdCorto() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    // Getters y setters
    public String getIdIncidencia() { return idIncidencia; }
    public Usuario getUsuario() { return usuario; }
    public Evento getEvento() { return evento; }
    public String getDescripcion() { return descripcion; }
    public EstadoIncidencia getEstado() { return estadoIncidencia; }
    public LocalDate getFechaReporte() { return fechaReporte; }
    public TipoIncidencia getTipoIncidencia() { return tipoIncidencia; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setEvento(Evento evento) { this.evento = evento; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setEstado(EstadoIncidencia estado) { this.estadoIncidencia = estado; }
    public void setFechaReporte(LocalDate fechaReporte) { this.fechaReporte = fechaReporte; }
    public void setTipoIncidencia(TipoIncidencia tipoIncidencia) { this.tipoIncidencia = tipoIncidencia; }

    @Override
    public String toString() {
        return idIncidencia + " - " + descripcion;
    }
}
