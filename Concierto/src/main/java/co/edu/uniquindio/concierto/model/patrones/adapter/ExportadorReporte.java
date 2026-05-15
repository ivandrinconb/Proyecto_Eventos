package co.edu.uniquindio.concierto.model.patrones.adapter;

import java.util.List;

public interface ExportadorReporte {
    <T> void exportar(List<T> datos);
}
