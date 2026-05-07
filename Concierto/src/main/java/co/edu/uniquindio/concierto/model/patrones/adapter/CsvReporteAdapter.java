package co.edu.uniquindio.concierto.model.patrones.adapter;

public class CsvReporteAdapter implements ExportadorReporte {
    private CsvExporter csvExporter;

    public CsvReporteAdapter(CsvExporter csvExporter) {
        this.csvExporter = csvExporter;
    }

    @Override
    public void exportar(String contenido) {
        csvExporter.exportCsv(contenido);
    }
}
