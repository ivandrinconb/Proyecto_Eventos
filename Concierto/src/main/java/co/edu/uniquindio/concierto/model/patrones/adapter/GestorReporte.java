package co.edu.uniquindio.concierto.model.patrones.adapter;

public class GestorReporte {
    public void exportarReporte(String formato, String contenido) {
        ExportadorReporte exportador;

        switch (formato.toUpperCase()) {
            case "PDF":
                exportador = new PdfReporteAdapter(new PdfExporter());
                break;
            case "CSV":
                exportador = new CsvReporteAdapter(new CsvExporter());
                break;
            default:
                throw new IllegalArgumentException("Formato no soportado: " + formato);
        }

        exportador.exportar(contenido);
    }
}
