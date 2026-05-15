package co.edu.uniquindio.concierto.model.patrones.adapter;

import java.util.List;

public class GestorReporte {
    public <T> void exportarReporte(ExportadorReporte exportador, List<T> datos) {
        if (exportador == null || datos == null || datos.isEmpty()) {
            System.out.println("No hay datos para exportar o exportador inválido.");
            return;
        }
        exportador.exportar(datos);
    }
}
