package co.edu.uniquindio.concierto.viewController;

import co.edu.uniquindio.concierto.controller.SistemaController;

import co.edu.uniquindio.concierto.model.Enums.EstadoAsiento;
import co.edu.uniquindio.concierto.model.clases.Evento;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CompraViewController {
    private Zona zonaSeleccionada;
    public void setZona(Zona zona) {
        this.zonaSeleccionada = zona;

        // Mostrar solo la zona seleccionada en el ComboBox
        cbZona.setItems(FXCollections.observableArrayList(zona));
        cbZona.getSelectionModel().select(zona);

        // Llenar asientos disponibles
        if (zona.getAsientos() != null) {
            List<Asiento> disponibles = zona.getAsientos().stream()
                    .filter(a -> a.getEstadoAsiento() == EstadoAsiento.DISPONIBLE)
                    .toList();
            cbAsiento.setItems(FXCollections.observableArrayList(disponibles));
        }

        // Mostrar precio base
        lblTotal.setText("Precio base: $ " + zona.getPrecioBase());
    }

    @FXML private ComboBox<Zona> cbZona;
    @FXML private ComboBox<Asiento> cbAsiento;
    @FXML private CheckBox chkVIP;
    @FXML private CheckBox chkSeguro;
    @FXML private CheckBox chkMerchandising;
    @FXML private CheckBox chkParqueadero;
    @FXML private CheckBox chkAccesoPreferencial;
    @FXML private Label lblTotal;

    @FXML private TableView<Asiento> tableEntradas;
    @FXML private TableColumn<Asiento, String> tcZonaEntrada;
    @FXML private TableColumn<Asiento, String> tcAsientoEntrada;
    @FXML private TableColumn<Asiento, String> tcServiciosEntrada;
    @FXML private TableColumn<Asiento, String> tcPrecioEntrada;

    private Evento evento;
    private Usuario usuarioActual;
    private SistemaController sistema;
    private final ObservableList<Asiento> asientosSeleccionados = FXCollections.observableArrayList();
    private double totalActual = 0;

    @FXML
    public void initialize() {
        sistema = SistemaController.getInstance();



        tcZonaEntrada.setCellValueFactory(cell ->
                new SimpleStringProperty(cbZona.getValue() != null ?
                        String.valueOf(cbZona.getValue().getTipoZona()) : ""));
        tcAsientoEntrada.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getFila() + "-" +
                        cell.getValue().getNumero()));
        tcServiciosEntrada.setCellValueFactory(cell ->
                new SimpleStringProperty(getServiciosSeleccionados()));
        tcPrecioEntrada.setCellValueFactory(cell ->
                new SimpleStringProperty("$ " + totalActual));

        tableEntradas.setItems(asientosSeleccionados);
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
        // Zonas directo del evento
        if (evento.getZonas() != null) {
            cbZona.setItems(FXCollections.observableArrayList(evento.getZonas()));
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    @FXML
    void OnActionSeleccionarZona(ActionEvent event) {
        Zona zonaSeleccionada = cbZona.getSelectionModel().getSelectedItem();
        if (zonaSeleccionada != null && zonaSeleccionada.getAsientos() != null) {
            List<Asiento> disponibles = new ArrayList<>();
            for (Asiento a : zonaSeleccionada.getAsientos()) {
                if (a.getEstadoAsiento() == EstadoAsiento.DISPONIBLE) {
                    disponibles.add(a);
                }
            }
            cbAsiento.setItems(FXCollections.observableArrayList(disponibles));
        }
    }

    @FXML
    void OnActionCalcular(ActionEvent event) {
        Zona zona = cbZona.getSelectionModel().getSelectedItem();
        if (zona == null) {
            mostrarAlerta("Zona requerida", "Por favor selecciona una zona.");
            return;
        }
        totalActual = zona.getPrecioBase();
        if (chkVIP.isSelected()) totalActual += 50000;
        if (chkSeguro.isSelected()) totalActual += 20000;
        if (chkMerchandising.isSelected()) totalActual += 30000;
        if (chkParqueadero.isSelected()) totalActual += 15000;
        if (chkAccesoPreferencial.isSelected()) totalActual += 25000;
        lblTotal.setText("$ " + totalActual);
    }

    private String getServiciosSeleccionados() {
        StringBuilder sb = new StringBuilder();
        if (chkVIP.isSelected()) sb.append("VIP ");
        if (chkSeguro.isSelected()) sb.append("Seguro ");
        if (chkMerchandising.isSelected()) sb.append("Merchandising ");
        if (chkParqueadero.isSelected()) sb.append("Parqueadero ");
        if (chkAccesoPreferencial.isSelected()) sb.append("AccesoPreferencial ");
        return sb.toString().trim();
    }

    @FXML
    void OnActionConfirmar(ActionEvent event) {
        Zona zona = cbZona.getSelectionModel().getSelectedItem();
        if (zona == null) {
            mostrarAlerta("Zona requerida", "Por favor selecciona una zona.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/pago.fxml"));
            Parent root = loader.load();
            PagoViewController ctrl = loader.getController();
            ctrl.setDatosCompra(evento, zona,
                    cbAsiento.getSelectionModel().getSelectedItem(),
                    lblTotal.getText(), usuarioActual,
                    getServiciosSeleccionados());
            Stage stage = (Stage) tableEntradas.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionCancelar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/co/edu/uniquindio/concierto/explorarEventos.fxml"));
            Stage stage = (Stage) tableEntradas.getScene().getWindow();
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
