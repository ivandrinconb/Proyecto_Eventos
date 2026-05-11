package co.edu.uniquindio.concierto.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.patrones.composite.Recinto;
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

public class GestionRecintoViewController {
    private ObservableList<Recinto> listaRecintos = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button btnMostrarTodos;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Spinner<Integer> spinnerCapacidad;

    @FXML
    private TableView<Recinto> tableRecinto;

    @FXML
    private Button tbtEditar;

    @FXML
    private TableColumn<Recinto, Void> tcZonas;

    @FXML
    private TableColumn<Recinto, String> tcId;

    @FXML
    private TableColumn<Recinto, String> tcCapacidad;

    @FXML
    private TableColumn<Recinto, String> tcCiudad;

    @FXML
    private TableColumn<Recinto, String> tcDireccion;

    @FXML
    private TableColumn<Recinto, String> tcNombre;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    void OnActionBuscar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        String ciudad = txtCiudad.getText();
        Integer capacidad = spinnerCapacidad.getValue();

        boolean algunCampoLleno = !nombre.isEmpty() || !direccion.isEmpty() || !ciudad.isEmpty() ||
                (capacidad != null && capacidad > 0);

        if (!algunCampoLleno) {
            mostrarAlerta("Campos vacíos", "Debes llenar al menos un campo para realizar la búsqueda.");
            return;
        }



        ObservableList<Recinto> filtrados = listaRecintos.filtered(r -> {
            boolean coincideNombre = nombre.isEmpty() || r.getNombre().equalsIgnoreCase(nombre);
            boolean coincideDireccion = direccion.isEmpty() || r.getDireccion().equalsIgnoreCase(direccion);
            boolean coincideCiudad = ciudad.isEmpty() || r.getCiudad().equalsIgnoreCase(ciudad);
            boolean coincideCapacidad = capacidad == null || capacidad == 0 || r.getCapacidad() == capacidad;

            return coincideNombre && coincideDireccion && coincideCiudad && coincideCapacidad;
        });


        tableRecinto.setItems(filtrados);


        if (!filtrados.isEmpty()) {
            Recinto encontrado = filtrados.get(0);
            txtNombre.setText(encontrado.getNombre());
            txtDireccion.setText(encontrado.getDireccion());
            txtCiudad.setText(encontrado.getCiudad());
            spinnerCapacidad.getValueFactory().setValue(encontrado.getCapacidad());
        } else {
            mostrarAlerta("Sin resultados", "No se encontró ningún recinto con esos criterios.");
        }

    }

    @FXML
    void OnActionCrear(ActionEvent event) {
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        String ciudad = txtCiudad.getText();
        Integer capacidad = spinnerCapacidad.getValue();


        if (nombre.isEmpty() || direccion.isEmpty() || ciudad.isEmpty() || capacidad == null || capacidad <= 0) {
            mostrarAlerta("Error", "Todos los campos son obligatorios y la capacidad debe ser mayor a 0.");
            return;
        }


        boolean existe = listaRecintos.stream()
                .anyMatch(r -> r.getNombre().equalsIgnoreCase(nombre)
                        && r.getCiudad().equalsIgnoreCase(ciudad));

        if (existe) {
            mostrarAlerta("Duplicado", "Ya existe un recinto con ese nombre en la ciudad " + ciudad + ".");
            return;
        }


        Recinto nuevoRecinto = new Recinto(nombre, direccion, ciudad, capacidad);
        listaRecintos.add(nuevoRecinto);
        tableRecinto.refresh();

        mostrarAlerta("Éxito", "Recinto agregado correctamente con ID: " + nuevoRecinto.getIdRecinto());
        limpiarCampos();

    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtDireccion.clear();
        txtCiudad.clear();
        spinnerCapacidad.getValueFactory().setValue(0);
    }

    @FXML
    void OnActionEditar(ActionEvent event) {
        Recinto seleccionado = tableRecinto.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String ciudad = txtCiudad.getText();
            Integer capacidad = spinnerCapacidad.getValue();

            if (!nombre.isEmpty() && !direccion.isEmpty() && !ciudad.isEmpty()
                    && capacidad != null && capacidad > 0) {


                boolean existe = listaRecintos.stream().anyMatch(r ->
                        r != seleccionado &&
                                r.getNombre().equalsIgnoreCase(nombre) &&
                                r.getCiudad().equalsIgnoreCase(ciudad)
                );

                if (existe) {
                    mostrarAlerta("Duplicado",
                            "Ya existe un recinto con el nombre " + nombre +
                                    " en la ciudad " + ciudad);
                    return;
                }
                seleccionado.setNombre(nombre);
                seleccionado.setDireccion(direccion);
                seleccionado.setCiudad(ciudad);
                seleccionado.setCapacidad(capacidad);

                tableRecinto.refresh();
                limpiarCampos();

                mostrarAlerta("Recinto actualizado",
                        "El recinto fue editado correctamente (ID: " + seleccionado.getIdRecinto() + ")");

            } else {
                mostrarAlerta("Campos vacíos",
                        "Debes llenar todos los campos y la capacidad debe ser mayor a 0.");
            }
        } else {
            mostrarAlerta("Selección inválida",
                    "Debes seleccionar un recinto en la tabla o buscarlo primero.");
        }

    }


    @FXML
    void OnActionEliminar(ActionEvent event) {
        Recinto seleccionado = tableRecinto.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar eliminación");
            confirmacion.setHeaderText("¿Eliminar recinto?");
            confirmacion.setContentText("Se eliminará el recinto con ID: " + seleccionado.getIdRecinto());


            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                listaRecintos.remove(seleccionado);
                SistemaController.getInstance().getListRecintos().remove(seleccionado);
                tableRecinto.refresh();
                limpiarCampos();

                mostrarAlerta("Recinto eliminado",
                        "El recinto fue eliminado correctamente.");
            }
        } else {
            mostrarAlerta("Selección inválida",
                    "Debes seleccionar un recinto en la tabla o buscarlo primero.");
        }

    }
    @FXML
    void OnActionMostrarTodos(ActionEvent event) {
        tableRecinto.setItems(listaRecintos);
    }



    @FXML
    void initialize() {
        tcId.setCellValueFactory(new PropertyValueFactory<>("idRecinto"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tcCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        tcCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        spinnerCapacidad.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000, 0)
        );
        spinnerCapacidad.setEditable(true);
        tableRecinto.setItems(listaRecintos);
        tableRecinto.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtNombre.setText(newSel.getNombre());
                txtDireccion.setText(newSel.getDireccion());
                txtCiudad.setText(newSel.getCiudad());
                spinnerCapacidad.getValueFactory().setValue(newSel.getCapacidad());
            }
        });
        tcZonas.setCellFactory(param -> new TableCell<Recinto, Void>() {
            private final Button btn = new Button("Gestionar Zonas");

            {
                btn.setOnAction(event -> {
                    Recinto recinto = getTableView().getItems().get(getIndex());
                    if (recinto != null) {
                        // 🔹 Aquí abres la ventana de Zonas con el recinto seleccionado
                        abrirVentanaZonas(recinto);
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
    }
    private void abrirVentanaZonas(Recinto recinto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionarZonas.fxml"));
            Parent root = loader.load();

            GestionZonasViewController controller = loader.getController();
            controller.setRecinto(recinto);

            Stage stage = new Stage();
            stage.setTitle("Gestionar Zonas - " + recinto.getNombre());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana de Zonas.");
        }
    }


}
