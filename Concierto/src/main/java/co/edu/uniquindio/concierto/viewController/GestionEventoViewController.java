package co.edu.uniquindio.concierto.viewController;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.CategoriaEvento;
import co.edu.uniquindio.concierto.model.Enums.EstadoEvento;
import co.edu.uniquindio.concierto.model.clases.Evento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionEventoViewController {
    private SistemaController sistemaController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCambiarEstado;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private ComboBox<EstadoEvento> cbEstado;

    @FXML
    private TableView<Evento> tableEvento;
    @FXML private ObservableList<Evento> listaEventos = FXCollections.observableArrayList();

    @FXML
    private Button tbtEditar;

    @FXML
    private TableColumn<Evento, String> tcCategoria;

    @FXML
    private TableColumn<Evento, String> tcCiudad;

    @FXML
    private TableColumn<Evento, EstadoEvento> tcEstado;

    @FXML
    private TableColumn<Evento, LocalDateTime> tcFecha;

    @FXML
    private TableColumn<Evento, String> tcNombre;

    @FXML
    private ComboBox<CategoriaEvento> cbCategoria;

    @FXML
    private TextField txtCiudad;

    @FXML
    private DatePicker dateFechaHora;

    @FXML
    private Spinner<Integer> spinnerHora;

    @FXML
    private Spinner<Integer> spinnerMinuto;

    @FXML
    private TextField txtNombre;

    @FXML
    void OnActionBuscar(ActionEvent event) {
        ObservableList<Evento> filtrados = listaEventos.filtered(e -> {
            boolean coincideNombre = txtNombre.getText().isEmpty() ||
                    e.getNombre().equalsIgnoreCase(txtNombre.getText());
            boolean coincideCiudad = txtCiudad.getText().isEmpty() ||
                    e.getCiudad().equalsIgnoreCase(txtCiudad.getText());
            boolean coincideCategoria = cbCategoria.getValue() == null ||
                    e.getCategoria() == cbCategoria.getValue();
            boolean coincideEstado = cbEstado.getValue() == null ||
                    e.getEstadoEvento() == cbEstado.getValue();
            boolean coincideFecha = dateFechaHora.getValue() == null ||
                    e.getFechaHora().toLocalDate().equals(dateFechaHora.getValue());
            return coincideNombre && coincideCiudad && coincideCategoria && coincideEstado && coincideFecha;
        });
        tableEvento.setItems(filtrados);

    }

    @FXML
    void OnActionCambiarEstado(ActionEvent event) {
        Evento seleccionado = tableEvento.getSelectionModel().getSelectedItem();
        EstadoEvento nuevoEstado = cbEstado.getValue();

        if (seleccionado != null && nuevoEstado != null) {
            seleccionado.setEstadoEvento(nuevoEstado);


            tableEvento.refresh();


            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Estado actualizado");
            alerta.setHeaderText(null);
            alerta.setContentText("El evento '" + seleccionado.getNombre() +
                    "' ahora está en estado: " + nuevoEstado);
            alerta.showAndWait();
        } else {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Selección inválida");
            alerta.setHeaderText(null);
            alerta.setContentText("Debes seleccionar un evento en la tabla y un estado.");
            alerta.showAndWait();
        }
    }

    @FXML
    void OnActionCrear(ActionEvent event) {
        String nombre = txtNombre.getText();
        CategoriaEvento categoria = cbCategoria.getValue();
        String ciudad = txtCiudad.getText();
        EstadoEvento estado = cbEstado.getValue();
        LocalDate fecha = dateFechaHora.getValue();
        Integer hora = spinnerHora.getValue();
        Integer minuto = spinnerMinuto.getValue();

        if (fecha != null && hora != null && minuto != null
                && estado != null && categoria != null
                && !nombre.isEmpty() && !ciudad.isEmpty()) {

            LocalDateTime fechaHora = LocalDateTime.of(fecha, LocalTime.of(hora, minuto));

            // 🔎 Validación de duplicados (ciudad + fecha/hora)
            boolean existe = listaEventos.stream().anyMatch(e ->
                    e.getCiudad().equalsIgnoreCase(ciudad) &&
                            e.getFechaHora().equals(fechaHora)
            );

            if (existe) {
                mostrarAlerta("Duplicado",
                        "Ya existe un evento en la ciudad " + ciudad +
                                " en la fecha y hora seleccionada.",
                        Alert.AlertType.ERROR);
                return;
            }

            Evento nuevo = new Evento(nombre, categoria, ciudad, fechaHora, estado);
            listaEventos.add(nuevo);
            limpiarCampos();
            mostrarAlerta("Registro exitoso",
                    "Evento registrado: " + nuevo.getNombre(),
                    Alert.AlertType.INFORMATION);

        } else {
            mostrarAlerta("Campos vacíos",
                    "Debes llenar todos los campos.",
                    Alert.AlertType.WARNING);
        }

    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private void limpiarCampos() {
        txtNombre.setText("");
        cbCategoria.setValue(null);
        txtCiudad.setText("");
        dateFechaHora.setValue(null);
        spinnerHora.getValueFactory().setValue(0);
        spinnerMinuto.getValueFactory().setValue(0);
        cbEstado.setValue(null);
    }

    @FXML
    void OnActionEditar(ActionEvent event) {
        Evento seleccionado = tableEvento.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            String nombre = txtNombre.getText();
            CategoriaEvento categoria = cbCategoria.getValue();
            String ciudad = txtCiudad.getText();
            EstadoEvento estado = cbEstado.getValue();
            LocalDate fecha = dateFechaHora.getValue();
            Integer hora = spinnerHora.getValue();
            Integer minuto = spinnerMinuto.getValue();

            if (fecha != null && hora != null && minuto != null
                    && estado != null && categoria != null
                    && !nombre.isEmpty() && !ciudad.isEmpty()) {

                LocalDateTime fechaHora = LocalDateTime.of(fecha, LocalTime.of(hora, minuto));

                boolean existe = listaEventos.stream().anyMatch(e ->
                        e != seleccionado && // ignorar el mismo evento que estamos editando
                                e.getCiudad().equalsIgnoreCase(ciudad) &&
                                e.getFechaHora().equals(fechaHora)
                );

                if (existe) {
                    mostrarAlerta("Duplicado",
                            "Ya existe un evento en la ciudad " + ciudad +
                                    " en la fecha y hora seleccionada.",
                            Alert.AlertType.ERROR);
                    return;
                }
                seleccionado.setNombre(nombre);
                seleccionado.setCategoria(categoria);
                seleccionado.setCiudad(ciudad);
                seleccionado.setFechaHora(fechaHora);
                seleccionado.setEstadoEvento(estado);

                tableEvento.refresh();
                limpiarCampos();

                mostrarAlerta("Evento actualizado",
                        "El evento fue editado correctamente.",
                        Alert.AlertType.INFORMATION);

            } else {
                mostrarAlerta("Campos vacíos",
                        "Debes llenar todos los campos.",
                        Alert.AlertType.WARNING);
            }
        } else {
            mostrarAlerta("Selección inválida",
                    "Debes seleccionar un evento en la tabla.",
                    Alert.AlertType.WARNING);
        }

    }

    @FXML
    void OnActionEliminar(ActionEvent event) {
        Evento seleccionado = tableEvento.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {

            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar eliminación");
            confirmacion.setHeaderText(null);
            confirmacion.setContentText("¿Seguro que deseas eliminar el evento '"
                    + seleccionado.getNombre() + "'?");

            confirmacion.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    listaEventos.remove(seleccionado);
                    sistemaController.getEventos().remove(seleccionado);
                    tableEvento.refresh();
                    limpiarCampos();

                    mostrarAlerta("Evento eliminado",
                            "Se eliminó el evento: " + seleccionado.getNombre(),
                            Alert.AlertType.INFORMATION);
                }
            });
        } else {
            mostrarAlerta("Selección inválida",
                    "Debes seleccionar un evento en la tabla.",
                    Alert.AlertType.WARNING);
        }

    }

    @FXML
    void initialize() {
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        tcFecha.setCellFactory(column -> new TableCell<Evento, LocalDateTime>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.format(formatter));
            }
        });
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estadoEvento"));
        spinnerHora.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12));
        spinnerMinuto.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));

        tableEvento.setItems(listaEventos);
        cbEstado.setItems(FXCollections.observableArrayList(EstadoEvento.values()));
        cbCategoria.setItems(FXCollections.observableArrayList(CategoriaEvento.values()));

        tableEvento.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtNombre.setText(newSel.getNombre());
                cbCategoria.setValue(newSel.getCategoria());
                txtCiudad.setText(newSel.getCiudad());
                dateFechaHora.setValue(newSel.getFechaHora().toLocalDate());
                spinnerHora.getValueFactory().setValue(newSel.getFechaHora().getHour());
                spinnerMinuto.getValueFactory().setValue(newSel.getFechaHora().getMinute());
                cbEstado.setValue(newSel.getEstadoEvento());
            }
        });


    }
    public void setSistemaController(SistemaController sistemaController) {
        this.sistemaController = sistemaController;
    }

}

