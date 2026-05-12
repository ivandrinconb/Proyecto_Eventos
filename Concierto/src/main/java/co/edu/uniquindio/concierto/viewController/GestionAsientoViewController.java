package co.edu.uniquindio.concierto.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.EstadoAsiento;
import co.edu.uniquindio.concierto.model.patrones.composite.Asiento;
import co.edu.uniquindio.concierto.model.patrones.composite.Zona;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GestionAsientoViewController {
    private SistemaController sistemaController;

    public void setSistemaController(SistemaController sistemaController) {
        this.sistemaController = sistemaController;
    }



    private Zona zona;

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnMostrarTodos;

    @FXML
    private ComboBox<EstadoAsiento> cbEstado;

    @FXML
    private Spinner<Integer> spinnerNumeroAsiento;

    @FXML
    private Button tbtEditar;
    @FXML
    private TableView<Asiento> tableAsiento;


    @FXML
    private TableColumn<Asiento,EstadoAsiento > tcEstado;

    @FXML
    private TableColumn<Asiento, String> tcFila;

    @FXML
    private TableColumn<Asiento, String> tcIdAsiento;

    @FXML
    private TableColumn<Asiento, Integer> tcNumeroAsiento;

    @FXML
    private TextField txtFila;

    @FXML
    void OnActionVolver(ActionEvent event) {
        try {
            List<Window> ventanas = new ArrayList<>(Window.getWindows());
            for (Window window : ventanas) {
                if (window instanceof Stage) {
                    ((Stage) window).close();
                }
            }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/concierto/menuAdministrador.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Menú Administrador");
        stage.setScene(new Scene(root));
        stage.show();

    } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo volver al menú del administrador.", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void OnActionBuscar(ActionEvent event) {
        String filaBuscada = txtFila.getText();
        EstadoAsiento estadoBuscado = cbEstado.getValue();


        if ((filaBuscada == null || filaBuscada.isEmpty()) && estadoBuscado == null) {
            mostrarAlerta("Error", "Debes ingresar al menos un criterio de búsqueda (Fila o Estado).", Alert.AlertType.WARNING);
            return;
        }

        List<Asiento> resultados = zona.getAsientos().stream()
                .filter(a -> (filaBuscada == null || filaBuscada.isEmpty() || a.getFila().equalsIgnoreCase(filaBuscada)) &&
                        (estadoBuscado == null || a.getEstadoAsiento() == estadoBuscado))
                .collect(Collectors.toList());

        if (resultados.isEmpty()) {
            mostrarAlerta("Sin resultados", "No se encontraron asientos con esos criterios.", Alert.AlertType.INFORMATION);
        } else {

            tableAsiento.setItems(FXCollections.observableArrayList(resultados));
        }
    }

    @FXML
    void OnActionCrear(ActionEvent event) {
        try {
            String fila = txtFila.getText();
            Integer numero = spinnerNumeroAsiento.getValue();
            EstadoAsiento estado = cbEstado.getValue();

            if (fila == null || fila.isEmpty() || numero == null || estado == null) {
                mostrarAlerta("Campos vacíos", "Debes llenar todos los campos.", Alert.AlertType.WARNING);
                return;
            }

            boolean existe = zona.getAsientos().stream().anyMatch(a ->
                    a.getFila().equalsIgnoreCase(fila) && a.getNumero() == numero
            );

            if (existe) {
                mostrarAlerta("Duplicado", "Ya existe un asiento con esa fila y número.", Alert.AlertType.ERROR);
                return;
            }

            Asiento nuevo = new Asiento(fila, numero, estado);
            zona.agregarComponente(nuevo);
            tableAsiento.getItems().add(nuevo);

            limpiarCampos();
            mostrarAlerta("Éxito", "Asiento creado correctamente... Ya puedes volver a la Gestion de Eventos", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un problema al crear el asiento.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }


    @FXML
    void OnActionEditar(ActionEvent event) {
        Asiento seleccionado = tableAsiento.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            String fila = txtFila.getText();
            Integer numero = spinnerNumeroAsiento.getValue();
            EstadoAsiento estado = cbEstado.getValue();

            if (fila == null || fila.isEmpty() || numero == null || numero <= 0 || estado == null) {
                mostrarAlerta("Campos vacíos", "Debes llenar todos los campos y el número debe ser mayor a 0.", Alert.AlertType.ERROR);
                return;
            }

            boolean existe = zona.getAsientos().stream().anyMatch(a ->
                    a != seleccionado &&
                            a.getFila().equalsIgnoreCase(fila) &&
                            a.getNumero() == numero
            );

            if (existe) {
                mostrarAlerta("Duplicado", "Ya existe un asiento con esa fila y número.", Alert.AlertType.INFORMATION);
                return;
            }

            seleccionado.setFila(fila);
            seleccionado.setNumero(numero);
            seleccionado.setEstadoAsiento(estado);

            tableAsiento.refresh();
            limpiarCampos();

            mostrarAlerta("Asiento actualizado",
                    "El asiento fue editado correctamente (ID: " + seleccionado.getIdAsiento() + ")", Alert.AlertType.CONFIRMATION);

        } else {
            mostrarAlerta("Selección inválida",
                    "Debes seleccionar un asiento en la tabla o buscarlo primero.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void OnActionEliminar(ActionEvent event) {
        Asiento seleccionado = tableAsiento.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {

            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar eliminación");
            confirmacion.setHeaderText("¿Estás seguro de eliminar este asiento?");
            confirmacion.setContentText("ID: " + seleccionado.getIdAsiento() +
                    " | Fila: " + seleccionado.getFila() +
                    " | Número: " + seleccionado.getNumero());


            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {

                zona.getAsientos().remove(seleccionado);


                tableAsiento.getItems().remove(seleccionado);

                limpiarCampos();
                mostrarAlerta("Éxito", "El asiento fue eliminado correctamente.", Alert.AlertType.INFORMATION);
            }

        } else {
            mostrarAlerta("Selección inválida", "Debes seleccionar un asiento en la tabla.", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void OnActionMostrarTodos(ActionEvent event) {
        if (zona != null) {
            tableAsiento.setItems(FXCollections.observableArrayList(zona.getAsientos()));
            tableAsiento.refresh();
        } else {
            mostrarAlerta("Error", "No hay una zona seleccionada.", Alert.AlertType.WARNING);
        }

    }

    @FXML
    void initialize() {
        tcIdAsiento.setCellValueFactory(new PropertyValueFactory<>("idAsiento"));
        tcFila.setCellValueFactory(new PropertyValueFactory<>("fila"));
        tcNumeroAsiento.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estadoAsiento"));


        cbEstado.getItems().setAll(EstadoAsiento.values());


        spinnerNumeroAsiento.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1)
        );
        spinnerNumeroAsiento.setEditable(true);

        tableAsiento.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtFila.setText(newSel.getFila());
                spinnerNumeroAsiento.getValueFactory().setValue(newSel.getNumero());
                cbEstado.setValue(newSel.getEstadoAsiento());
            }
        });


    }
    private void limpiarCampos() {
        txtFila.clear();
        spinnerNumeroAsiento.getValueFactory().setValue(0);
        cbEstado.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


}
