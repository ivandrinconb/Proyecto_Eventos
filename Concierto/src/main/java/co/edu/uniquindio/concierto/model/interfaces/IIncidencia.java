package co.edu.uniquindio.concierto.model.interfaces;

import co.edu.uniquindio.concierto.model.Enums.TipoIncidencia;

import java.time.LocalDateTime;

public interface IIncidencia {
    void registrarIncidencia(TipoIncidencia tipo, String descripcion);
    void consultarIncidenciasPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    void consultarIncidenciasPorTipo(TipoIncidencia tipo);
}
