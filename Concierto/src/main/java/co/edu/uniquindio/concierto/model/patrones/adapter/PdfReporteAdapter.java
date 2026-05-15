package co.edu.uniquindio.concierto.model.patrones.adapter;

import javafx.scene.control.TextArea;

import java.util.List;

public class PdfReporteAdapter implements ExportadorReporte {
    private PdfExporter pdfExporter;
    private TextArea textArea;

    public PdfReporteAdapter(PdfExporter pdfExporter, TextArea textArea) {
        this.pdfExporter = pdfExporter;
        this.textArea = textArea;
    }

    @Override
    public <T> void exportar(List<T> datos) {
        pdfExporter.exportarPDF(datos, textArea);
    }
}
