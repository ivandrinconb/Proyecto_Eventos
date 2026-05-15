package co.edu.uniquindio.concierto.model.patrones.adapter;

import javafx.scene.control.TextArea;

import java.util.List;

public class CsvExporter {
    public <T> void exportarCSV(List<T> datos, TextArea textArea) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Reporte CSV ===\n");
        for (T dato : datos) {
            sb.append(dato.toString()).append("\n");
        }
        textArea.setText(sb.toString());
    }
}
