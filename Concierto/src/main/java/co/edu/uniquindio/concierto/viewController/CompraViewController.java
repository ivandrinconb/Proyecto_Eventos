package co.edu.uniquindio.concierto.viewController;


import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;
import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Entrada;
import co.edu.uniquindio.concierto.model.clases.Evento;
import co.edu.uniquindio.concierto.model.clases.ServicioAdicional;
import co.edu.uniquindio.concierto.model.patrones.composite.Asiento;
import co.edu.uniquindio.concierto.model.patrones.composite.Zona;
import co.edu.uniquindio.concierto.model.patrones.decorator.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompraViewController {

    @FXML private ComboBox<Zona> cbZona;
    @FXML private ComboBox<Asiento> cbAsiento;
    @FXML private CheckBox chkVIP, chkSeguro, chkMerchandising, chkParqueadero, chkAccesoPreferencial;
    @FXML private Label lblTotal;
    @FXML private TableView<Entrada> tableEntradas;
    @FXML private TableColumn<Entrada, String> tcZonaEntrada;
    @FXML private TableColumn<Entrada, String> tcAsientoEntrada;
    @FXML private TableColumn<Entrada, String> tcServiciosEntrada;
    @FXML private TableColumn<Entrada, Double> tcPrecioEntrada;

    @FXML private Button btnCalcular, btnConfirmar, btnCancelar;

    // --- Data ---
    private Compra compra;
    private Zona zona;
    private Evento eventoSeleccionado;
    private ObservableList<Entrada> listaEntradas = FXCollections.observableArrayList();
    private ServicioAdicional s;
    private ObservableList<ServicioAdicional> listaServicios = FXCollections.observableArrayList();

    // --- Inicialización ---
    @FXML
    public void initialize() {
        tcZonaEntrada.setCellValueFactory(new PropertyValueFactory<>("zona"));
        tcAsientoEntrada.setCellValueFactory(new PropertyValueFactory<>("asiento"));
        tcServiciosEntrada.setCellValueFactory(new PropertyValueFactory<>("servicios"));
        tcPrecioEntrada.setCellValueFactory(new PropertyValueFactory<>("precioFinal"));

        tableEntradas.setItems(listaEntradas);
    }




    public void setEvento(Evento evento) {
        this.eventoSeleccionado = evento;
        this.compra = new Compra(eventoSeleccionado, FXCollections.observableArrayList());
        cbZona.getItems();
    }

    public void setAsiento(Asiento asiento) {
        cbAsiento.getItems();
    }

    // --- Acciones ---
    @FXML
    private void OnActionSeleccionarZona() {
        Zona zona = cbZona.getSelectionModel().getSelectedItem();
        if (zona != null) {
            cbAsiento.getItems();
        }
    }

    @FXML
    private void OnActionCalcular() {
        Zona zona = cbZona.getSelectionModel().getSelectedItem();
        Asiento asiento = cbAsiento.getSelectionModel().getSelectedItem();

        if (zona == null || asiento == null) {
            mostrarAlerta("Error", "Debe seleccionar una zona y un asiento.");
            return;
        }


        // 1. Crear entrada base
        IEntrada entradaDecorada = new EntradaBase(zona.getPrecioBase());

        // 2. Aplicar decoradores según checkboxes
        if (chkVIP.isSelected()) entradaDecorada = new ConVIP(entradaDecorada);
        if (chkSeguro.isSelected()) entradaDecorada = new ConSeguro(entradaDecorada);
        if (chkMerchandising.isSelected()) entradaDecorada = new ConMerchandising(entradaDecorada);
        if (chkParqueadero.isSelected()) entradaDecorada = new ConEstacionamiento(entradaDecorada);
        if (chkAccesoPreferencial.isSelected()) entradaDecorada = new ConAccesoPreferencial(entradaDecorada);

        // 3. Calcular precio final
        double precioFinal = entradaDecorada.getCosto();
        lblTotal.setText("$ " + precioFinal);


    }

    @FXML
    private void OnActionConfirmar() {
        Zona zona = cbZona.getSelectionModel().getSelectedItem();
        Asiento asiento = cbAsiento.getSelectionModel().getSelectedItem();

        if (zona == null || asiento == null) {
            mostrarAlerta("Error", "Debe seleccionar una zona y un asiento.");
            return;
        }


        IEntrada entradaDecorada = new EntradaBase(zona.getPrecioBase());

        if (chkVIP.isSelected()) entradaDecorada = new ConVIP(entradaDecorada);
        if (chkSeguro.isSelected()) entradaDecorada = new ConSeguro(entradaDecorada);
        if (chkMerchandising.isSelected()) entradaDecorada = new ConMerchandising(entradaDecorada);
        if (chkParqueadero.isSelected()) entradaDecorada = new ConEstacionamiento(entradaDecorada);
        if (chkAccesoPreferencial.isSelected()) entradaDecorada = new ConAccesoPreferencial(entradaDecorada);

        double precioFinal = entradaDecorada.getCosto();


        compra.getServiciosAdicionales().clear();
        if (chkVIP.isSelected()) compra.getServiciosAdicionales().add(new ServicioAdicional(TipoServicioAdicional.VIP, entradaDecorada));
        if (chkSeguro.isSelected()) compra.getServiciosAdicionales().add(new ServicioAdicional(TipoServicioAdicional.SEGURO, entradaDecorada));
        if (chkMerchandising.isSelected()) compra.getServiciosAdicionales().add(new ServicioAdicional(TipoServicioAdicional.MERCHANDISING, entradaDecorada));
        if (chkParqueadero.isSelected()) compra.getServiciosAdicionales().add(new ServicioAdicional(TipoServicioAdicional.PARQUEADERO, entradaDecorada));
        if (chkAccesoPreferencial.isSelected()) compra.getServiciosAdicionales().add(new ServicioAdicional(TipoServicioAdicional.ACCESOPREFERENCIAL, entradaDecorada));

        // Crear objeto Entrada con la lista de servicios ya llena
        Entrada entrada = new Entrada(zona, asiento, compra.getServiciosAdicionales(), precioFinal);
        listaEntradas.add(entrada);
        compra.getEntradas().add(entrada);

        // Abrir ventana de pago
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/concierto/Pago.fxml"));
            Parent root = loader.load();

            PagoViewController ctrl = loader.getController();
            ctrl.setCompra(compra); // ✅ ahora la compra tiene los servicios

            Stage stage = (Stage) btnConfirmar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Pago de Compra");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana de pago.");
        }
    }

    @FXML
    private void OnActionCancelar() {
        listaEntradas.clear();
        cbZona.getSelectionModel().clearSelection();
        cbAsiento.getSelectionModel().clearSelection();
        chkVIP.setSelected(false);
        chkSeguro.setSelected(false);
        chkMerchandising.setSelected(false);
        chkParqueadero.setSelected(false);
        chkAccesoPreferencial.setSelected(false);
        lblTotal.setText("");
    }

    // --- Utilidades ---
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setZona(Zona zona) {
        this.zona = zona;


        cbZona.setItems(FXCollections.observableArrayList(zona));
        cbZona.getSelectionModel().select(zona);

        // Llenar el ComboBox de asientos con los disponibles
        if (zona.getAsientos() != null) {
            List<Asiento> disponibles = zona.getAsientos().stream()
                    .filter(a -> a.getEstadoAsiento() != null &&
                            a.getEstadoAsiento().toString().equals("DISPONIBLE"))
                    .toList();
            cbAsiento.setItems(FXCollections.observableArrayList(disponibles));
        }

        // Mostrar precio base en el label
        lblTotal.setText("Precio base: $ " + zona.getPrecioBase());
    }
}