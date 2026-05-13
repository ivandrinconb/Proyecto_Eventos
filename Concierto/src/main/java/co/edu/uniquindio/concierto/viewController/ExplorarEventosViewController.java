package co.edu.uniquindio.concierto.viewController;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.clases.Evento;
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

import java.util.List;
import java.util.stream.Collectors;

public class ExplorarEventosViewController {

    @FXML private TextField txtCiudad;
    @FXML private ComboBox<String> cbCategoria;
    @FXML private TextField txtPrecioMax;
    @FXML private TextField txtFecha;

    @FXML private TableView<Evento> tableEventos;
    @FXML private TableColumn<Evento, String> tcNombreEvento;
    @FXML private TableColumn<Evento, String> tcCategoriaEvento;
    @FXML private TableColumn<Evento, String> tcCiudadEvento;
    @FXML private TableColumn<Evento, String> tcFechaEvento;
    @FXML private TableColumn<Evento, String> tcPrecioEvento;
    @FXML private TableColumn<Evento, String> tcEstadoEvento;

    private SistemaController sistema;
    private final ObservableList<Evento> eventos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        sistema = SistemaController.getInstance();

        cbCategoria.setItems(FXCollections.observableArrayList(
                "Todos", "CONCIERTO", "TEATRO", "CONFERENCIA"
        ));
        cbCategoria.getSelectionModel().selectFirst();

        tcNombreEvento.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getNombre() != null ?
                        cell.getValue().getNombre() : ""));
        tcCategoriaEvento.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getCategoria() != null ?
                        cell.getValue().getCategoria().toString() : ""));
        tcCiudadEvento.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getCiudad() != null ?
                        cell.getValue().getCiudad() : ""));
        // LocalDateTime -> String con toString()
        tcFechaEvento.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getFechaHora() != null ?
                        cell.getValue().getFechaHora().toString() : ""));
        tcEstadoEvento.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getEstadoEvento() != null ?
                        cell.getValue().getEstadoEvento().toString() : ""));

        cargarTodosLosEventos();
    }

    private void cargarTodosLosEventos() {
        eventos.setAll(sistema.getEventos());
        tableEventos.setItems(eventos);
    }

    @FXML
    void OnActionFiltrar(ActionEvent event) {
        List<Evento> filtrados = sistema.getEventos().stream()
                .filter(e -> {
                    boolean pasa = true;
                    if (!txtCiudad.getText().isEmpty()) {
                        pasa = e.getCiudad() != null &&
                                e.getCiudad().toLowerCase()
                                        .contains(txtCiudad.getText().toLowerCase());
                    }
                    if (pasa && cbCategoria.getValue() != null
                            && !cbCategoria.getValue().equals("Todos")) {
                        pasa = e.getCategoria() != null &&
                                e.getCategoria().toString().equals(cbCategoria.getValue());
                    }
                    if (pasa && !txtFecha.getText().isEmpty()) {
                        pasa = e.getFechaHora() != null &&
                                e.getFechaHora().toString().contains(txtFecha.getText());
                    }
                    return pasa;
                })
                .collect(Collectors.toList());

        eventos.setAll(filtrados);
        tableEventos.setItems(eventos);
    }

    @FXML
    void OnActionLimpiar(ActionEvent event) {
        txtCiudad.clear();
        txtPrecioMax.clear();
        txtFecha.clear();
        cbCategoria.getSelectionModel().selectFirst();
        cargarTodosLosEventos();
    }

    @FXML
    void OnActionVerDetalle(ActionEvent event) {
        Evento seleccionado = tableEventos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Selección requerida", "Por favor selecciona un evento.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/detalleEvento.fxml"));
            Parent root = loader.load();
            DetalleEventoViewController ctrl = loader.getController();
            ctrl.setEvento(seleccionado);
            Stage stage = (Stage) tableEventos.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/co/edu/uniquindio/concierto/MenuUsuario.fxml"));
            Stage stage = (Stage) tableEventos.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

