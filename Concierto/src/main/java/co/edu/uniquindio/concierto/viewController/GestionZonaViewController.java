package co.edu.uniquindio.concierto.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.TipoZona;
import co.edu.uniquindio.concierto.model.patrones.composite.Recinto;
import co.edu.uniquindio.concierto.model.patrones.composite.Zona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GestionZonaViewController {
    private Recinto recinto;
    @FXML private ObservableList<Zona> listaZonas = FXCollections.observableArrayList();
    private SistemaController sistemaController;

    public void setSistemaController(SistemaController sistemaController) {
        this.sistemaController = sistemaController;

    }





        @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnMostrarTodos;

    @FXML
    private TableView<Zona> tableZona;

    @FXML
    private ComboBox<TipoZona> cbTipoZona;

    @FXML
    private Spinner<Integer> spinnerCapacidad;

    @FXML
    private Button tbtEditar;

    @FXML
    private TableColumn<Zona, Void> tcAsientos;

    @FXML
    private TableColumn<Zona, String> tcCapacidad;

    @FXML
    private TableColumn<Zona, String> tcIdZona;

    @FXML
    private TableColumn<Zona, String> tcPrecioBase;

    @FXML
    private TableColumn<Zona, TipoZona> tcTipo;

    @FXML
    private TextField txtPrecioBase;



    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }

    @FXML
    void OnActionBuscar(ActionEvent event) {
        TipoZona tipo = cbTipoZona.getValue();
        Integer capacidad = spinnerCapacidad.getValue();
        String precioTexto = txtPrecioBase.getText();

        final Double precioBase;
        if (precioTexto != null && !precioTexto.isEmpty()) {
            try {
                precioBase = Double.parseDouble(precioTexto);
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El precio base debe ser un número válido.", Alert.AlertType.WARNING);
                return;
            }
        } else {
            precioBase = null; // 🔹 inicialización por defecto
        }

        // Validar que al menos un campo esté lleno
        boolean algunCampoLleno = tipo != null || (capacidad != null && capacidad > 0) || precioBase != null;
        if (!algunCampoLleno) {
            mostrarAlerta("Campos vacíos", "Debes llenar al menos un campo para realizar la búsqueda.", Alert.AlertType.INFORMATION);
            return;
        }

        // Filtrar las zonas del recinto
        ObservableList<Zona> filtradas = FXCollections.observableArrayList(
                recinto.getZonas().stream().filter(z -> {
                    boolean coincideTipo = (tipo == null) || z.getTipoZona() == tipo;
                    boolean coincideCapacidad = (capacidad == null || capacidad == 0) || z.getCapacidad() == capacidad;
                    boolean coincidePrecio = (precioBase == null) || z.getPrecioBase() == precioBase;
                    return coincideTipo && coincideCapacidad && coincidePrecio;
                }).toList()
        );

        tableZona.setItems(filtradas);

        if (filtradas.isEmpty()) {
            mostrarAlerta("Sin resultados", "No se encontró ninguna zona con esos criterios.", Alert.AlertType.INFORMATION);
        }
    }


    @FXML
    void OnActionCrear(ActionEvent event) {
        TipoZona tipo = cbTipoZona.getValue();
        Integer capacidad = spinnerCapacidad.getValue();
        double precioBase;

        try {
            precioBase = Double.parseDouble(txtPrecioBase.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio base debe ser un número válido.", Alert.AlertType.WARNING);
            return;
        }

        if (tipo == null || capacidad == null || capacidad <= 0) {
            mostrarAlerta("Campos vacíos", "Debes seleccionar un tipo de zona y una capacidad válida.", Alert.AlertType.WARNING);
            return;
        }

        Zona nuevaZona = new Zona(tipo, capacidad, precioBase);
        recinto.agregarComponente(nuevaZona);
        cargarZonas();
        limpiarCampos();

        mostrarAlerta("Éxito", "Zona creada correctamente.", Alert.AlertType.INFORMATION);
    }

    private void cargarZonas() {
        if (recinto != null) {
            tableZona.setItems(FXCollections.observableArrayList(recinto.getZonas()));
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
        cbTipoZona.setValue(null);
        spinnerCapacidad.getValueFactory().setValue(0);
        txtPrecioBase.clear();
    }


    @FXML
    void OnActionEditar(ActionEvent event) {
        Zona seleccionada = tableZona.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            TipoZona tipo = cbTipoZona.getValue();
            Integer capacidad = spinnerCapacidad.getValue();
            String precioTexto = txtPrecioBase.getText();

            if (tipo == null || capacidad == null || capacidad <= 0 || precioTexto.isEmpty()) {
                mostrarAlerta("Campos vacíos", "Debes llenar todos los campos y la capacidad debe ser mayor a 0.", Alert.AlertType.ERROR);
                return;
            }

            double precioBase;
            try {
                precioBase = Double.parseDouble(precioTexto);
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El precio base debe ser un número válido.", Alert.AlertType.WARNING);
                return;
            }

            // 🔹 Validar duplicados (ejemplo: mismo tipo y capacidad)
            boolean existe = recinto.getZonas().stream().anyMatch(z ->
                    z != seleccionada &&
                            z.getTipoZona() == tipo &&
                            z.getCapacidad() == capacidad
            );

            if (existe) {
                mostrarAlerta("Duplicado", "Ya existe una zona con ese tipo y capacidad.", Alert.AlertType.INFORMATION);
                return;
            }

            // 🔹 Actualizar atributos
            seleccionada.setTipoZona(tipo);
            seleccionada.setCapacidad(capacidad);
            seleccionada.setPrecioBase(precioBase);

            tableZona.refresh();
            limpiarCampos();

            mostrarAlerta("Zona actualizada",
                    "La zona fue editada correctamente (ID: " + seleccionada.getIdZona() + ")", Alert.AlertType.CONFIRMATION);

        } else {
            mostrarAlerta("Selección inválida",
                    "Debes seleccionar una zona en la tabla o buscarla primero.", Alert.AlertType.ERROR);
        }




    }

    @FXML
    void OnActionEliminar(ActionEvent event) {
        Zona seleccionada = tableZona.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar eliminación");
            confirmacion.setHeaderText("¿Eliminar zona?");
            confirmacion.setContentText("Se eliminará la zona con ID: " + seleccionada.getIdZona());

            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // 🔹 Eliminar de la lista del recinto
                recinto.getZonas().remove(seleccionada);

                // 🔹 Eliminar también del SistemaController
                SistemaController.getInstance().eliminarZona(seleccionada);

                // 🔹 Refrescar la tabla
                tableZona.setItems(FXCollections.observableArrayList(recinto.getZonas()));
                tableZona.refresh();

                // 🔹 Limpiar campos
                limpiarCampos();

                mostrarAlerta("Zona eliminada",
                        "La zona fue eliminada correctamente.", Alert.AlertType.CONFIRMATION);
            }
        } else {
            mostrarAlerta("Selección inválida",
                    "Debes seleccionar una zona en la tabla o buscarla primero.", Alert.AlertType.WARNING);
        }

    }

    @FXML
    void OnActionMostrarTodos(ActionEvent event) {
        if (recinto != null) {
            tableZona.setItems(FXCollections.observableArrayList(recinto.getZonas()));
            tableZona.refresh();
        } else {
            mostrarAlerta("Error", "No hay un recinto seleccionado.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void initialize() {
        cbTipoZona.setItems(FXCollections.observableArrayList(TipoZona.values()));
        spinnerCapacidad.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0)
        );
        spinnerCapacidad.setEditable(true);

        tcIdZona.setCellValueFactory(new PropertyValueFactory<>("idZona"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipoZona"));
        tcTipo.setCellFactory(column -> new TableCell<Zona, TipoZona>() {
            @Override
            protected void updateItem(TipoZona item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.name());
                }
            }
        });
        tcCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        tcPrecioBase.setCellValueFactory(new PropertyValueFactory<>("precioBase"));

        tcAsientos.setCellFactory(param -> new TableCell<Zona, Void>() {
            private final Button btn = new Button("Gestionar Asientos");

            {
                btn.setOnAction(event -> {
                    Zona zona = getTableView().getItems().get(getIndex());
                    if (zona != null) {

                       abrirVentanaAsientos(zona);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });
        tableZona.setItems(listaZonas);

        tableZona.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cbTipoZona.setValue(newSelection.getTipoZona());
                spinnerCapacidad.getValueFactory().setValue(newSelection.getCapacidad());
                txtPrecioBase.setText(String.valueOf(newSelection.getPrecioBase()));
            }
        });
    }

    private void abrirVentanaAsientos(Zona zona) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/concierto/GestionAsientos.fxml"));
            Parent root = loader.load();

            GestionAsientoViewController controlador = loader.getController();
            controlador.setZona(zona);

            Stage stage = new Stage();
            stage.setTitle("Gestión de Asientos - Recinto: " + recinto.getNombre()
                    + " | Zona: " + zona.getTipoZona()
                    + " (" + zona.getCapacidad() + " asientos)");

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana de Asientos.", Alert.AlertType.ERROR);
        }
    }
}
