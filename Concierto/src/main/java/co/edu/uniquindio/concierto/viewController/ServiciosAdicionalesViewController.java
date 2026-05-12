package co.edu.uniquindio.concierto.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.EstadoEntrada;
import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;
import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Entrada;
import co.edu.uniquindio.concierto.model.clases.ServicioAdicional;
import co.edu.uniquindio.concierto.model.patrones.composite.Asiento;
import co.edu.uniquindio.concierto.model.patrones.composite.Zona;
import co.edu.uniquindio.concierto.model.patrones.decorator.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServiciosAdicionalesViewController {
    private Zona zona;
    private Asiento asiento;
    private final SistemaController sistemaController = SistemaController.getInstance();
    private Compra compra;

    public void setCompra(Compra compra) {
        this.compra = compra;
        tableServicioAdicional.setItems(FXCollections.observableArrayList(compra.getServiciosAdicionales()));
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAgregarServicio;

    @FXML
    private ComboBox<TipoServicioAdicional> cbTipoServicio;
    @FXML
    private TableView<ServicioAdicional> tableServicioAdicional;
    @FXML
    private TableColumn<ServicioAdicional, Double> tcCosto;

    @FXML
    private TableColumn<ServicioAdicional, String> tcServicio;


    @FXML
    void OnActionAgregarServicio(ActionEvent event) {
        TipoServicioAdicional tipo = cbTipoServicio.getValue();

        if (tipo != null && compra != null) {
            if (compra.getEntrada() == null) {
                compra.setEntrada(new EntradaBase(30000));
            }

            IEntrada entradaDecorada = compra.getEntrada();

            switch (tipo) {
                case VIP -> entradaDecorada = new ConVIP(entradaDecorada);
                case PARQUEADERO -> entradaDecorada = new ConEstacionamiento(entradaDecorada);
                case MERCHANDISING -> entradaDecorada = new ConMerchandising(entradaDecorada);
                case SEGURO -> entradaDecorada = new ConSeguro(entradaDecorada);
                case ACCESOPREFERENCIAL -> entradaDecorada = new ConAccesoPreferencial(entradaDecorada);
            }

            compra.setEntrada(entradaDecorada);

            ServicioAdicional nuevoServicio = new ServicioAdicional(tipo, entradaDecorada);
            compra.getServiciosAdicionales().add(nuevoServicio);

            tableServicioAdicional.getItems().add(nuevoServicio);
        }
    }


    @FXML
    void initialize() {
        tcServicio.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        cbTipoServicio.setItems(FXCollections.observableArrayList(TipoServicioAdicional.values()));

    }

}
