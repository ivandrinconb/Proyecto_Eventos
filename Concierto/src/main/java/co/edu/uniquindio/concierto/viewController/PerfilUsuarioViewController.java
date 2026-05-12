package co.edu.uniquindio.concierto.viewController;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.clases.MetodoPago;
import co.edu.uniquindio.concierto.model.clases.Usuario;
import co.edu.uniquindio.concierto.model.Enums.TipoMetodoPago;
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

import java.util.UUID;

public class PerfilUsuarioViewController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private ComboBox<TipoMetodoPago> cbTipoMetodo;
    @FXML private TextField txtNumeroMetodo;

    @FXML private TableView<MetodoPago> tableMetodos;
    @FXML private TableColumn<MetodoPago, String> tcIdMetodo;
    @FXML private TableColumn<MetodoPago, String> tcTipoMetodo;
    @FXML private TableColumn<MetodoPago, String> tcNumeroMetodo;

    private Usuario usuarioActual;
    private SistemaController sistema;
    private final ObservableList<MetodoPago> metodos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        sistema = SistemaController.getInstance();

        cbTipoMetodo.setItems(FXCollections.observableArrayList(TipoMetodoPago.values()));

        tcIdMetodo.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getIdMetodo() != null ?
                        cell.getValue().getIdMetodo() : ""));
        tcTipoMetodo.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getTipo() != null ?
                        cell.getValue().getTipo().toString() : ""));
        tcNumeroMetodo.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getNumero() != null ?
                        cell.getValue().getNumero() : ""));

        tableMetodos.setItems(metodos);
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        txtNombre.setText(usuario.getNombre() != null ? usuario.getNombre() : "");
        txtCorreo.setText(usuario.getCorreoElectronico() != null ?
                usuario.getCorreoElectronico() : "");
        txtTelefono.setText(usuario.getTelefono() != null ? usuario.getTelefono() : "");
        if (usuario.getMetodosPago() != null) {
            metodos.setAll(usuario.getMetodosPago());
        }
    }

    @FXML
    void OnActionActualizar(ActionEvent event) {
        if (usuarioActual == null) return;
        if (txtNombre.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
            mostrarAlerta("Datos incompletos", "Nombre y correo son obligatorios.");
            return;
        }
        usuarioActual.setNombre(txtNombre.getText());
        usuarioActual.setCorreoElectronico(txtCorreo.getText());
        usuarioActual.setTelefono(txtTelefono.getText());
        mostrarInfo("Perfil actualizado", "Tu perfil fue actualizado correctamente.");
    }

    @FXML
    void OnActionAgregarMetodo(ActionEvent event) {
        if (cbTipoMetodo.getValue() == null || txtNumeroMetodo.getText().isEmpty()) {
            mostrarAlerta("Datos incompletos", "Selecciona un tipo e ingresa el número.");
            return;
        }
        // Usa el constructor correcto de MetodoPago(String, TipoMetodoPago, String)
        MetodoPago nuevo = new MetodoPago(
                UUID.randomUUID().toString(),
                cbTipoMetodo.getValue(),
                txtNumeroMetodo.getText()
        );

        usuarioActual.agregarMetodoPago(nuevo.getTipo());
        metodos.setAll(usuarioActual.getMetodosPago());
        cbTipoMetodo.getSelectionModel().clearSelection();
        txtNumeroMetodo.clear();
        mostrarInfo("Método agregado", "El método de pago fue agregado correctamente.");
    }

    @FXML
    void OnActionEliminarMetodo(ActionEvent event) {
        MetodoPago seleccionado = tableMetodos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Selección requerida",
                    "Selecciona un método de pago para eliminar.");
            return;
        }
        usuarioActual.getMetodosPago().remove(seleccionado);
        metodos.setAll(usuarioActual.getMetodosPago());
    }

    @FXML
    void OnActionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/MenuUsuario.fxml"));
            Parent root = loader.load();
            MenuUsuarioViewController menuCtrl = loader.getController();
            menuCtrl.setUsuario(usuarioActual);
            Stage stage = (Stage) tableMetodos.getScene().getWindow();
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

