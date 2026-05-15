package co.edu.uniquindio.concierto.model.patrones.adapter;

import javafx.scene.control.TextArea;

import java.util.List;

public class CsvReporteAdapter implements ExportadorReporte {
    private CsvExporter csvExporter;
    private TextArea textArea;

    public CsvReporteAdapter(CsvExporter csvExporter, TextArea textArea) {
        this.csvExporter = csvExporter;
        this.textArea = textArea;
    }

    @Override
    public <T> void exportar(List<T> datos) {
        csvExporter.exportarCSV(datos, textArea);
    }
}
