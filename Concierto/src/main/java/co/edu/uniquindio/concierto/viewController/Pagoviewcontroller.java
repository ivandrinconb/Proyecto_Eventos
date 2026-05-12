package co.edu.uniquindio.concierto.viewController;

import co.edu.uniquindio.concierto.controller.CompraController;
import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.clases.*;
import co.edu.uniquindio.concierto.model.Enums.EstadoAsiento;
import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class PagoViewController {

    @FXML private Label lblEvento;
    @FXML private Label lblZonaAsiento;
    @FXML private Label lblServicios;
    @FXML private Label lblTotalPagar;
    @FXML private ComboBox<String> cbTipoPago;
    @FXML private TextField txtNumeroTarjeta;
    @FXML private TextField txtTitular;

    private Evento evento;
    private Zona zona;
    private Asiento asiento;
    private String servicios;
    private Usuario usuarioActual;
    private SistemaController sistema;
    private CompraController compraController;

    @FXML
    public void initialize() {
        sistema = SistemaController.getInstance();
        compraController = new CompraController();
        cbTipoPago.setItems(FXCollections.observableArrayList("CREDITO", "DEBITO", "PSE"));
    }

    public void setDatosCompra(Evento evento, Zona zona, Asiento asiento,
                               String total, Usuario usuario, String servicios) {
        this.evento = evento;
        this.zona = zona;
        this.asiento = asiento;
        this.usuarioActual = usuario;
        this.servicios = servicios;

        lblEvento.setText(evento.getNombre() != null ? evento.getNombre() : "");
        lblZonaAsiento.setText(zona.getNombre() +
                (asiento != null ? " / Fila " + asiento.getFila() +
                        " - Asiento " + asiento.getNumero() : " / General"));
        lblServicios.setText(servicios.isEmpty() ? "Ninguno" : servicios);
        lblTotalPagar.setText(total);
    }

    @FXML
    void OnActionSeleccionarTipo(ActionEvent event) {
        String tipo = cbTipoPago.getValue();
        if (tipo != null && tipo.equals("PSE")) {
            txtNumeroTarjeta.setPromptText("Número de cuenta");
            txtTitular.setPromptText("Nombre del banco");
        } else {
            txtNumeroTarjeta.setPromptText("Número de tarjeta");
            txtTitular.setPromptText("Nombre del titular");
        }
    }

    @FXML
    void OnActionPagar(ActionEvent event) {
        if (cbTipoPago.getValue() == null ||
                txtNumeroTarjeta.getText().isEmpty() ||
                txtTitular.getText().isEmpty()) {
            mostrarAlerta("Datos incompletos", "Por favor completa todos los datos de pago.");
            return;
        }

        // Crear la compra con los datos reales
        Compra compra = new Compra(
                UUID.randomUUID().toString(),
                usuarioActual,
                evento,
                LocalDateTime.now(),
                0,
                EstadoCompra.PAGADA,
                new ArrayList<>(),
                new ArrayList<>()
        );
        compraController.crearCompra(compra);

        // Cambiar estado del asiento a VENDIDO si aplica
        if (asiento != null) {
            asiento.setEstadoAsiento(EstadoAsiento.VENDIDO);
        }

        mostrarInfo("¡Pago exitoso!",
                "Tu compra fue confirmada.\nRevisa tu historial de compras.");

        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/co/edu/uniquindio/concierto/historialCompras.fxml"));
            Stage stage = (Stage) cbTipoPago.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionCancelar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/co/edu/uniquindio/concierto/compra.fxml"));
            Stage stage = (Stage) cbTipoPago.getScene().getWindow();
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
