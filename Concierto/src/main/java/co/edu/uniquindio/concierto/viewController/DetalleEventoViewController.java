package co.edu.uniquindio.concierto.viewController;

import co.edu.uniquindio.concierto.model.clases.Evento;
import co.edu.uniquindio.concierto.model.patrones.composite.Zona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

public class DetalleEventoViewController {

    @FXML private Label lblNombre;
    @FXML private Label lblCategoria;
    @FXML private Label lblCiudad;
    @FXML private Label lblFechaHora;
    @FXML private TextArea txtDescripcion;

    @FXML private TableView<Zona> tableZonas;
    @FXML private TableColumn<Zona, String> tcNombreZona;
    @FXML private TableColumn<Zona, String> tcCapacidadZona;
    @FXML private TableColumn<Zona, String> tcPrecioZona;
    @FXML private TableColumn<Zona, String> tcDisponiblesZona;
    @FXML private TableColumn<Zona, String> tcNumeradaZona;

    private Evento evento;
    private final ObservableList<Zona> zonas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tcNombreZona.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getTipoZona() != null ?
                        String.valueOf(cell.getValue().getTipoZona()) : ""));
        tcCapacidadZona.setCellValueFactory(cell ->
                new SimpleStringProperty(String.valueOf(cell.getValue().getCapacidad())));
        tcPrecioZona.setCellValueFactory(cell ->
                new SimpleStringProperty("$ " + cell.getValue().getPrecioBase()));
        // Disponibilidad = capacidad - asientos ocupados
        tcDisponiblesZona.setCellValueFactory(cell -> {
            Zona z = cell.getValue();
            int ocupados = 0;
            if (z.getAsientos() != null) {
                ocupados = (int) z.getAsientos().stream()
                        .filter(a -> a.getEstadoAsiento() != null &&
                                !a.getEstadoAsiento().toString().equals("DISPONIBLE"))
                        .count();
            }
            return new SimpleStringProperty(String.valueOf(z.getCapacidad() - ocupados));
        });
        tcNumeradaZona.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getAsientos() != null &&
                        !cell.getValue().getAsientos().isEmpty() ? "Sí" : "No"));

        tableZonas.setItems(zonas);
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
        lblNombre.setText(evento.getNombre() != null ? evento.getNombre() : "");
        lblCategoria.setText(evento.getCategoria() != null ?
                evento.getCategoria().toString() : "");
        lblCiudad.setText(evento.getCiudad() != null ? evento.getCiudad() : "");
        // LocalDateTime -> String
        lblFechaHora.setText(evento.getFechaHora() != null ?
                evento.getFechaHora().toString() : "");
        txtDescripcion.setText(evento.getDescripcion() != null ?
                evento.getDescripcion() : "");

        // Zonas directo del Evento (no del recinto)
        if (evento.getZonas() != null) {
            zonas.setAll(evento.getZonas());
        }
    }

    @FXML
    void OnActionComprar(ActionEvent event) {
        if (this.evento == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/compra.fxml"));
            Parent root = loader.load();
            CompraViewController ctrl = loader.getController();
            ctrl.setEvento(this.evento);
            Stage stage = (Stage) tableZonas.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/co/edu/uniquindio/concierto/explorarEventos.fxml"));
            Stage stage = (Stage) tableZonas.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
