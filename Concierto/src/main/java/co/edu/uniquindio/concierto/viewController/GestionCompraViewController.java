package co.edu.uniquindio.concierto.viewController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.Enums.EstadoEntrada;
import co.edu.uniquindio.concierto.model.Enums.TipoMetodoPago;
import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Entrada;
import co.edu.uniquindio.concierto.model.clases.Evento;
import co.edu.uniquindio.concierto.model.clases.MetodoPago;
import co.edu.uniquindio.concierto.model.patrones.command.*;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GestionCompraViewController {
    private final SistemaController sistemaController = SistemaController.getInstance();

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
    private Button btnModificar;

    @FXML
    private Button btnPagar;

    @FXML
    private Button btnReembolsar;

    @FXML
    private ComboBox<EstadoCompra> cbEstado;

    @FXML
    private ComboBox<Evento> cbEvento;

    @FXML
    private ComboBox<TipoMetodoPago> cbMetodoPago;

    @FXML
    private ComboBox<Usuario> cbUsuario;

    @FXML
    private Spinner<Integer> spinnerCantidad;

    @FXML
    private TableView<Compra> tableCompras;

    @FXML
    private TableColumn<Compra, Integer> tcCantidad;

    @FXML
    private TableColumn<Compra, EstadoCompra> tcEstado;

    @FXML
    private TableColumn<Compra, Evento> tcEvento;

    @FXML
    private TableColumn<Compra, TipoMetodoPago> tcMetodoPago;

    @FXML
    private TableColumn<Compra, Usuario> tcUsuario;
    @FXML
    private TableColumn<Compra, Void> tcServicios;

    @FXML
    void OnActionCancelar(ActionEvent event) {
        Compra seleccionada = tableCompras.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar cancelación");
            confirmacion.setHeaderText("¿Cancelar la compra seleccionada?");
            confirmacion.setContentText("Evento: " + seleccionada.getEvento().getNombre() +
                    "\nUsuario: " + seleccionada.getUsuario().getNombre());

            confirmacion.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Command cancelarCompra = new CancelarCompraCommand(sistemaController, seleccionada);
                    Invoker invoker = new Invoker();
                    invoker.setCommand(cancelarCompra);
                    invoker.executeCommand();

                    tableCompras.refresh();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Compra cancelada correctamente.");
                    alert.showAndWait();
                }
            });
        }
    }

    @FXML
    void OnActionConsultar(ActionEvent event) {
        Usuario usuario = cbUsuario.getValue();
        Evento evento = cbEvento.getValue();
        EstadoCompra estado = cbEstado.getValue();
        TipoMetodoPago metodoPago = cbMetodoPago.getValue();

        Command consultarCompra = new ConsultarCompraCommand(
                sistemaController, usuario, evento, estado, metodoPago);

        Invoker invoker = new Invoker();
        invoker.setCommand(consultarCompra);
        invoker.executeCommand();

        ObservableList<Compra> resultados = ((ConsultarCompraCommand) consultarCompra).getResultado();

        if (resultados.isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "No se encontraron compras con esos criterios.").showAndWait();
        } else {
            tableCompras.setItems(resultados);
        }

    }

    @FXML
    void OnActionCrear(ActionEvent event) {
        Usuario usuario = cbUsuario.getValue();
        Evento evento = cbEvento.getValue();
        Integer cantidad = spinnerCantidad.getValue();
        TipoMetodoPago metodoPago = cbMetodoPago.getValue();
        EstadoCompra estado = cbEstado.getValue();

        if (usuario != null && evento != null && cantidad != null && metodoPago != null && estado != null) {
            Command crearCompra = new CrearCompraCommand(sistemaController, usuario, evento, cantidad, metodoPago, estado);
            Invoker invoker = new Invoker();
            invoker.setCommand(crearCompra);
            invoker.executeCommand();

            tableCompras.setItems(FXCollections.observableArrayList(sistemaController.getListCompras()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Compra creada correctamente.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Debes llenar todos los campos.");
            alert.showAndWait();
        }
    }

    @FXML
    void OnActionModificar(ActionEvent event) {
        Compra seleccionada = tableCompras.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            Integer nuevaCantidad = spinnerCantidad.getValue();
            TipoMetodoPago nuevoMetodo = cbMetodoPago.getValue();
            EstadoCompra nuevoEstado = cbEstado.getValue();

            if (nuevaCantidad != null && nuevoMetodo != null && nuevoEstado != null) {
                Command modificarCompra = new ModificarCompraCommand(sistemaController, seleccionada,
                        nuevaCantidad, nuevoMetodo, nuevoEstado);
                Invoker invoker = new Invoker();
                invoker.setCommand(modificarCompra);
                invoker.executeCommand();

                tableCompras.refresh();
                new Alert(Alert.AlertType.INFORMATION, "Compra modificada correctamente.").showAndWait();
            } else {
                new Alert(Alert.AlertType.WARNING, "Debes llenar todos los campos.").showAndWait();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Debes seleccionar una compra en la tabla.").showAndWait();
        }

    }

    @FXML
    void OnActionPagar(ActionEvent event) {
        Compra seleccionada = tableCompras.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar pago");
            confirmacion.setHeaderText("¿Deseas marcar esta compra como pagada?");
            confirmacion.setContentText("Evento: " + seleccionada.getEvento().getNombre() +
                    "\nUsuario: " + seleccionada.getUsuario().getNombre());

            confirmacion.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Command pagarCompra = new PagarCompraCommand(sistemaController, seleccionada);
                    Invoker invoker = new Invoker();
                    invoker.setCommand(pagarCompra);
                    invoker.executeCommand();

                    tableCompras.refresh();
                    new Alert(Alert.AlertType.INFORMATION, "Compra pagada y entradas generadas.").showAndWait();
                }
            });
        } else {
            new Alert(Alert.AlertType.WARNING, "Debes seleccionar una compra en la tabla.").showAndWait();
        }
    }

    @FXML
    void OnActionReembolsar(ActionEvent event) {
        Compra seleccionada = tableCompras.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar reembolso");
            confirmacion.setHeaderText("¿Deseas reembolsar esta compra?");
            confirmacion.setContentText("Evento: " + seleccionada.getEvento().getNombre() +
                    "\nUsuario: " + seleccionada.getUsuario().getNombre());

            confirmacion.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Command reembolsarCompra = new ReembolsarCompraCommand(sistemaController, seleccionada);
                    Invoker invoker = new Invoker();
                    invoker.setCommand(reembolsarCompra);
                    invoker.executeCommand();

                    tableCompras.refresh();
                    new Alert(Alert.AlertType.INFORMATION, "Compra reembolsada y entradas anuladas.").showAndWait();
                }
            });
        } else {
            new Alert(Alert.AlertType.WARNING, "Debes seleccionar una compra en la tabla.").showAndWait();
        }

    }

    @FXML
    void initialize() {

            // Cargar listas desde el SistemaController
            cbUsuario.setItems(FXCollections.observableArrayList(sistemaController.getListUsuarios()));
            cbEvento.setItems(FXCollections.observableArrayList(sistemaController.getListEventos()));

            cbMetodoPago.setItems(FXCollections.observableArrayList(TipoMetodoPago.values()));
            cbEstado.setItems(FXCollections.observableArrayList(EstadoCompra.values()));

            // Configurar Spinner de cantidad
            SpinnerValueFactory<Integer> valueFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
            spinnerCantidad.setValueFactory(valueFactory);

            // Configurar columnas de la tabla
            tcUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            tcEvento.setCellValueFactory(new PropertyValueFactory<>("evento"));
            tcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            tcMetodoPago.setCellValueFactory(new PropertyValueFactory<>("tipoMetodoPago"));
            tcEstado.setCellValueFactory(new PropertyValueFactory<>("estadoCompra"));

            // Cargar compras iniciales
            tableCompras.setItems(FXCollections.observableArrayList(sistemaController.getListCompras()));

        tableCompras.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cbUsuario.setValue(newSel.getUsuario());
                cbEvento.setValue(newSel.getEvento());
                spinnerCantidad.getValueFactory().setValue(newSel.getCantidad());
                cbMetodoPago.setValue(newSel.getTipoMetodoPago());
                cbEstado.setValue(newSel.getEstadoCompra());
            }
        });

            // 🔹 Opcional: mostrar nombre en ComboBox en vez de toString raro
            cbUsuario.setCellFactory(param -> new ListCell<Usuario>() {
                @Override
                protected void updateItem(Usuario usuario, boolean empty) {
                    super.updateItem(usuario, empty);
                    setText(empty || usuario == null ? null : usuario.getNombre());
                }
            });
            cbUsuario.setButtonCell(new ListCell<Usuario>() {
                @Override
                protected void updateItem(Usuario usuario, boolean empty) {
                    super.updateItem(usuario, empty);
                    setText(empty || usuario == null ? null : usuario.getNombre());
                }
            });

            cbEvento.setCellFactory(param -> new ListCell<Evento>() {
                @Override
                protected void updateItem(Evento evento, boolean empty) {
                    super.updateItem(evento, empty);
                    setText(empty || evento == null ? null : evento.getNombre());
                }
            });
            cbEvento.setButtonCell(new ListCell<Evento>() {
                @Override
                protected void updateItem(Evento evento, boolean empty) {
                    super.updateItem(evento, empty);
                    setText(empty || evento == null ? null : evento.getNombre());
                }
            });
        tcServicios.setCellFactory(param -> new TableCell<Compra, Void>() {
            private final Button btn = new Button("Servicios Adicionales");

            {
                btn.setOnAction(event -> {
                    Compra compra = getTableView().getItems().get(getIndex());
                    if (compra != null) {
                        abrirVentanaServicios(compra);
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
    private void abrirVentanaServicios(Compra compra) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/ServiciosAdicionales.fxml")
            );
            Parent root = loader.load();


            ServiciosAdicionalesViewController controller = loader.getController();



            controller.setCompra(compra);

            // Crear y mostrar la nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Gestión de Servicios Adicionales");
            stage.setScene(new Scene(root));
            stage.show();


            tableCompras.refresh();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir la ventana de servicios adicionales.");
            alert.showAndWait();
        }
    }

}