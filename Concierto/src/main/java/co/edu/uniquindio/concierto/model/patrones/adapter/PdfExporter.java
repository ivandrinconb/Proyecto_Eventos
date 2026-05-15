package co.edu.uniquindio.concierto.model.patrones.adapter;

import javafx.scene.control.TextArea;

import java.util.List;

public class PdfExporter {
    public <T> void exportarPDF(List<T> datos, TextArea textArea) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Reporte PDF ===\n");
        for (T dato : datos) {
            sb.append(dato.toString()).append("\n");
        }
        textArea.setText(sb.toString());
    }
}
