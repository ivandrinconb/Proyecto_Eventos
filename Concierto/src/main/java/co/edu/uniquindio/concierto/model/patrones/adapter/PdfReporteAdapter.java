package co.edu.uniquindio.concierto.model.patrones.adapter;

public class PdfReporteAdapter implements ExportadorReporte {
    private PdfExporter pdfExporter;

    public PdfReporteAdapter(PdfExporter pdfExporter) {
        this.pdfExporter = pdfExporter;
    }

    @Override
    public void exportar(String contenido) {
        pdfExporter.exportPdf(contenido);
    }
}
