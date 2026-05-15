package co.edu.uniquindio.concierto.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.clases.Incidencia;
import co.edu.uniquindio.concierto.model.patrones.adapter.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class VerReportesViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnExportarCSV;

    @FXML
    private Button btnExportarPDF;

    @FXML
    private ComboBox<Incidencia> cbIncidencias;

    @FXML
    private ComboBox<String> cbTipoReporte;

    @FXML
    private TextArea txtReporte;

    @FXML
    void OnActionExportarCSV(ActionEvent event) {
        GestorReporte gestor = new GestorReporte();
        gestor.exportarReporte(
                new CsvReporteAdapter(new CsvExporter(), txtReporte),
                SistemaController.getInstance().getListUsuarios() // o Eventos, Compras, Incidencias
        );

    }

    @FXML
    void OnActionExportarPDF(ActionEvent event) {
        GestorReporte gestor = new GestorReporte();
        gestor.exportarReporte(
                new PdfReporteAdapter(new PdfExporter(), txtReporte),
                SistemaController.getInstance().getListEventos()
        );

    }

    @FXML
    void initialize() {
        cbTipoReporte.setItems(FXCollections.observableArrayList(
                "Usuarios", "Eventos", "Compras", "Incidencias"
        ));

        cbIncidencias.setItems(FXCollections.observableArrayList(
                SistemaController.getInstance().getListIncidencias()
        ));

    }

}
