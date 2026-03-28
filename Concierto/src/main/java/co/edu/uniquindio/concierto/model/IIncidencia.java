package co.edu.uniquindio.concierto.model;

public interface IIncidencia {
    void registrarIncidencia(String tipo, String descripcion);
    void consultarIncidenciasPorFecha(String fechaInicio, String fechaFin);
    void consultarIncidenciasPorTipo(String tipo);
}
