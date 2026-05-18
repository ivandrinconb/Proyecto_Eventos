package co.edu.uniquindio.concierto.viewController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.EstadoIncidencia;
import co.edu.uniquindio.concierto.model.Enums.TipoIncidencia;
import co.edu.uniquindio.concierto.model.clases.Evento;
import co.edu.uniquindio.concierto.model.clases.Incidencia;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionIncidenciaViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnResolver;

    @FXML
    private ComboBox<EstadoIncidencia> cbEstado;

    @FXML
    private ComboBox<Evento> cbEvento;

    @FXML
    private ComboBox<TipoIncidencia> cbTipoIncidencia;

    @FXML
    private ComboBox<Usuario> cbUsuario;

    @FXML
    private DatePicker dateFechaReporte;

    @FXML
    private TableView<Incidencia> tableIncidencias;

    @FXML
    private Button tbtModificar;

    @FXML
    private TableColumn<Incidencia, String> tcDescripcion;

    @FXML
    private TableColumn<Incidencia, EstadoIncidencia> tcEstado;

    @FXML
    private TableColumn<Incidencia, Evento> tcEvento;

    @FXML
    private TableColumn<Incidencia, LocalDate> tcFecha;

    @FXML
    private TableColumn<Incidencia, String> tcIdIncidencia;

    @FXML
    private TableColumn<Incidencia, TipoIncidencia> tcTipoIncidencia;

    @FXML
    private TableColumn<Incidencia, Usuario> tcUsuario;

    @FXML
    private TextField txtDescripcion;

    @FXML
    void OnActionCancelar(ActionEvent event) {
        limpiarCampos();
        mostrarAlerta("Acción cancelada", "Los campos fueron limpiados.", Alert.AlertType.INFORMATION);

    }

    @FXML
    void OnActionConsultar(ActionEvent event) {
        Incidencia seleccionada = tableIncidencias.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta("Selección requerida", "Debes seleccionar una incidencia para consultar.", Alert.AlertType.WARNING);
            return;
        }

        cbUsuario.setValue(seleccionada.getUsuario());
        cbEvento.setValue(seleccionada.getEvento());
        cbTipoIncidencia.setValue(seleccionada.getTipoIncidencia());
        cbEstado.setValue(seleccionada.getEstado());
        txtDescripcion.setText(seleccionada.getDescripcion());
        dateFechaReporte.setValue(seleccionada.getFechaReporte());

    }

    @FXML
    void OnActionCrear(ActionEvent event) {
        if (cbUsuario.getValue() == null ||
                cbEvento.getValue() == null ||
                cbTipoIncidencia.getValue() == null ||
                cbEstado.getValue() == null ||
                txtDescripcion.getText().isEmpty() ||
                dateFechaReporte.getValue() == null) {

            mostrarAlerta("Campos incompletos", "Por favor completa todos los datos de la incidencia.",Alert.AlertType.INFORMATION);
            return;
        }


        Incidencia nueva = new Incidencia(
                generarIdCorto(),
                cbUsuario.getValue(),
                cbEvento.getValue(),
                txtDescripcion.getText(),
                cbEstado.getValue(),
                dateFechaReporte.getValue(),
                cbTipoIncidencia.getValue()
        );

        // Agregar a la lista del sistema y a la tabla
        SistemaController.getInstance().getListIncidencias().add(nueva);
        tableIncidencias.getItems().add(nueva);

        mostrarAlerta("Incidencia creada", "La incidencia fue registrada correctamente.", Alert.AlertType.CONFIRMATION);
        limpiarCampos();

    }
    private String generarIdCorto() {
        return UUID.randomUUID().toString().substring(0, 5);
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private void limpiarCampos() {
        cbUsuario.setValue(null);
        cbEvento.setValue(null);
        cbTipoIncidencia.setValue(null);
        cbEstado.setValue(null);
        txtDescripcion.clear();
        dateFechaReporte.setValue(null);
    }

    @FXML
    void OnActionModificar(ActionEvent event) {
        Incidencia seleccionada = tableIncidencias.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta("Selección requerida", "Debes seleccionar una incidencia para modificar.", Alert.AlertType.WARNING);
            return;
        }

        // Actualizar con los valores del formulario
        seleccionada.setUsuario(cbUsuario.getValue());
        seleccionada.setEvento(cbEvento.getValue());
        seleccionada.setTipoIncidencia(cbTipoIncidencia.getValue());
        seleccionada.setDescripcion(txtDescripcion.getText());
        seleccionada.setEstado(cbEstado.getValue());
        seleccionada.setFechaReporte(dateFechaReporte.getValue());

        tableIncidencias.refresh();
        mostrarAlerta("Incidencia modificada", "Los datos fueron actualizados correctamente.", Alert.AlertType.CONFIRMATION);
        limpiarCampos();

    }

    @FXML
    void OnActionResolver(ActionEvent event) {
        Incidencia seleccionada = tableIncidencias.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta("Selección requerida", "Debes seleccionar una incidencia para resolver.", Alert.AlertType.WARNING);
            return;
        }

        seleccionada.setEstado(EstadoIncidencia.RESUELTA);
        tableIncidencias.refresh();
        mostrarAlerta("Incidencia resuelta", "La incidencia fue marcada como resuelta.", Alert.AlertType.INFORMATION);

    }

    @FXML
    void initialize() {

        cbEstado.setItems(FXCollections.observableArrayList(EstadoIncidencia.values()));
        cbTipoIncidencia.setItems(FXCollections.observableArrayList(TipoIncidencia.values()));
        cbUsuario.setItems(FXCollections.observableArrayList(
                SistemaController.getInstance().getListUsuarios()
        ));

        // Cargar eventos
        cbEvento.setItems(FXCollections.observableArrayList(
                SistemaController.getInstance().getListEventos()
        ));

        tcIdIncidencia.setCellValueFactory(new PropertyValueFactory<>("idIncidencia"));
        tcUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcEvento.setCellValueFactory(new PropertyValueFactory<>("evento"));
        tcTipoIncidencia.setCellValueFactory(new PropertyValueFactory<>("tipoIncidencia"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaReporte"));


        tableIncidencias.setItems(FXCollections.observableArrayList(SistemaController.getInstance().getListIncidencias()));
    }





}

