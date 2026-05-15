package co.edu.uniquindio.concierto.viewController;

import co.edu.uniquindio.concierto.controller.CompraController;
import co.edu.uniquindio.concierto.controller.SistemaController;

import co.edu.uniquindio.concierto.model.Enums.EstadoAsiento;
import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Entrada;
import co.edu.uniquindio.concierto.model.clases.Evento;
import co.edu.uniquindio.concierto.model.clases.ServicioAdicional;
import co.edu.uniquindio.concierto.model.patrones.composite.Asiento;
import co.edu.uniquindio.concierto.model.patrones.composite.Zona;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private Compra compra;
    private Zona zona;
    private Asiento asiento;
    private String servicios;
    private Usuario usuarioActual;
    private SistemaController sistema;
    private CompraController compraController;
    private ObservableList<Entrada> Entradas = FXCollections.observableArrayList();
    private ServicioAdicional s;
    private ObservableList<ServicioAdicional> listaServicios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        sistema = SistemaController.getInstance();
        compraController = new CompraController();
        cbTipoPago.setItems(FXCollections.observableArrayList("CREDITO", "DEBITO", "PSE"));
    }
    public void setCompra(Compra compra) {
        this.compra = compra;

        // Evento
        if (compra.getEvento() != null) {
            lblEvento.setText(compra.getEvento().getNombre());
        }

        // Zona / Asiento (tomamos la primera entrada como referencia)
        if (!compra.getEntradas().isEmpty()) {
            Entrada entrada = compra.getEntradas().get(0);
            Zona zona = entrada.getZona();
            Asiento asiento = entrada.getAsiento();
            lblZonaAsiento.setText(
                    (zona != null ? zona.getTipoZona().toString() : "N/A") +
                            (asiento != null ? " / Fila " + asiento.getFila() +
                                    " - Asiento " + asiento.getNumero() : "")
            );
        }

        // Servicios adicionales
        String servicios;
        if (!compra.getServiciosAdicionales().isEmpty()) {
            servicios = compra.getServiciosAdicionales().stream()
                    .map(ServicioAdicional::getDescripcion)
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse("Ninguno");
        } else {
            servicios = compra.getEntradas().stream()
                    .map(Entrada::getServicios)
                    .filter(s -> !s.equals("Ninguno"))
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse("Ninguno");
        }
        lblServicios.setText(servicios);
    }

    private double calcularTotalCompra() {
        return compra.getEntradas()
                .stream()
                .mapToDouble(Entrada::getPrecioFinal)
                .sum();
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

        // Usar la compra existente
        compra.setEstadoCompra(EstadoCompra.PAGADA);
        compra.calcularTotal();
        SistemaController.getInstance().agregarCompra(compra);

        // Cambiar estado del asiento a VENDIDO si aplica
        if (!compra.getEntradas().isEmpty()) {
            compra.getEntradas().forEach(e -> e.getAsiento().setEstadoAsiento(EstadoAsiento.VENDIDO));
        }

        mostrarInfo("¡Pago exitoso!",
                "Tu compra fue confirmada.\nRevisa tu historial de compras.");

        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/co/edu/uniquindio/concierto/HistorialCompras.fxml"));
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
