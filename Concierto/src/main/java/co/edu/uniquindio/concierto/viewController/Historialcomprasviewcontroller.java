package co.edu.uniquindio.concierto.viewController;

import co.edu.uniquindio.concierto.controller.CompraController;
import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Usuario;
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

public class HistorialComprasViewController {

    @FXML private ComboBox<String> cbEstado;
    @FXML private TextField txtFiltroEvento;
    @FXML private TextField txtFiltroFecha;

    @FXML private TableView<Compra> tableCompras;
    @FXML private TableColumn<Compra, String> tcIdCompra;
    @FXML private TableColumn<Compra, String> tcEventoCompra;
    @FXML private TableColumn<Compra, String> tcFechaCompra;
    @FXML private TableColumn<Compra, String> tcTotalCompra;
    @FXML private TableColumn<Compra, String> tcEstadoCompra;

    private SistemaController sistema;
    private CompraController compraController;
    private Usuario usuarioActual;
    private final ObservableList<Compra> compras = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        sistema = SistemaController.getInstance();
        compraController = new CompraController();

        cbEstado.setItems(FXCollections.observableArrayList(
                "Todos", "CREADA", "PAGADA", "CONFIRMADA", "CANCELADA", "REEMBOLSADA"
        ));
        cbEstado.getSelectionModel().selectFirst();

        tcIdCompra.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getIdCompra() != null ?
                        cell.getValue().getIdCompra() : ""));
        tcEventoCompra.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getEvento() != null ?
                        cell.getValue().getEvento().getNombre() : ""));
        // fechaCreacion es LocalDateTime -> toString()
        tcFechaCompra.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getFechaCreacion() != null ?
                        cell.getValue().getFechaCreacion().toString() : ""));
        tcTotalCompra.setCellValueFactory(cell ->
                new SimpleStringProperty("$ " + cell.getValue().getTotal()));
        tcEstadoCompra.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getEstadoCompra() != null ?
                        cell.getValue().getEstadoCompra().toString() : ""));

        cargarCompras();
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        cargarCompras();
    }

    private void cargarCompras() {
        List<Compra> todas = sistema.getCompras();
        if (usuarioActual != null) {
            todas = todas.stream()
                    .filter(c -> c.getUsuario() != null &&
                            c.getUsuario().getIdUsuario()
                                    .equals(usuarioActual.getIdUsuario()))
                    .collect(Collectors.toList());
        }
        compras.setAll(todas);
        tableCompras.setItems(compras);
    }

    @FXML
    void OnActionFiltrar(ActionEvent event) {
        List<Compra> filtradas = sistema.getCompras().stream()
                .filter(c -> {
                    boolean pasa = true;
                    if (cbEstado.getValue() != null && !cbEstado.getValue().equals("Todos")) {
                        pasa = c.getEstadoCompra() != null &&
                                c.getEstadoCompra().toString().equals(cbEstado.getValue());
                    }
                    if (pasa && !txtFiltroEvento.getText().isEmpty()) {
                        pasa = c.getEvento() != null &&
                                c.getEvento().getNombre().toLowerCase()
                                        .contains(txtFiltroEvento.getText().toLowerCase());
                    }
                    if (pasa && !txtFiltroFecha.getText().isEmpty()) {
                        pasa = c.getFechaCreacion() != null &&
                                c.getFechaCreacion().toString()
                                        .contains(txtFiltroFecha.getText());
                    }
                    return pasa;
                })
                .collect(Collectors.toList());
        compras.setAll(filtradas);
        tableCompras.setItems(compras);
    }

    @FXML
    void OnActionCancelarCompra(ActionEvent event) {
        Compra seleccionada = tableCompras.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta("Selección requerida",
                    "Por favor selecciona una compra para cancelar.");
            return;
        }
        compraController.cancelarCompra(seleccionada.getIdCompra());
        cargarCompras();
        mostrarInfo("Compra cancelada", "La compra fue cancelada exitosamente.");
    }

    @FXML
    void OnActionVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/co/edu/uniquindio/concierto/explorarEventos.fxml"));
            Stage stage = (Stage) tableCompras.getScene().getWindow();
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

    private void mostrarInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

