package co.edu.uniquindio.concierto.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.clases.Usuario;
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

public class CrearUsuarioViewController {



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBuscar;


    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;


    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private TableView<Usuario> tableUsuario;


    @FXML
    private TableColumn<Usuario, String> tcCorreoElectronico;

    @FXML
    private TableColumn<Usuario, String> tcIdentificacion;

    @FXML
    private TableColumn<Usuario, String> tcNombre;

    @FXML
    private TableColumn<Usuario, String> tcPassword;

    @FXML
    private TableColumn<Usuario, String> tcTelefono;

    @FXML
    private TextField txtCorreoElectronico;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtTelefono;

    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    private SistemaController sistemaController = SistemaController.getInstance();
    private Usuario usuarioEditando = null;



    @FXML
    void OnActionBuscar(ActionEvent event) {
            String id = txtIdentificacion.getText();
            Usuario encontrado = sistemaController.getUsuarios()
                    .stream()
                    .filter(u -> u.getIdUsuario().equals(id))
                    .findFirst()
                    .orElse(null);

            if (encontrado != null) {
                // Selecciona el usuario en la tabla
                tableUsuario.getSelectionModel().select(encontrado);

                // Rellena los campos con la información del usuario
                txtNombre.setText(encontrado.getNombre());
                txtIdentificacion.setText(encontrado.getIdUsuario());
                txtCorreoElectronico.setText(encontrado.getCorreoElectronico());
                txtPassword.setText(encontrado.getPassword());
                txtTelefono.setText(encontrado.getTelefono());

                alerta(Alert.AlertType.INFORMATION, "Usuario encontrado",
                        "Se encontró el usuario: " + encontrado.getNombre());
            } else {
                alerta(Alert.AlertType.WARNING, "No encontrado",
                        "No existe un usuario con esa identificación.");
            }


    }







    @FXML
    void OnActionRegistrar(ActionEvent event) {
        if (txtNombre.getText().isEmpty() ||
                txtIdentificacion.getText().isEmpty() ||
                txtCorreoElectronico.getText().isEmpty() ||
                txtPassword.getText().isEmpty() ||
                txtTelefono.getText().isEmpty()) {

            alerta(Alert.AlertType.ERROR, "Campos incompletos",
                    "Debes llenar todos los campos antes de registrar.");
            return;
        }
        String nuevaIdentificacion = txtIdentificacion.getText();


        if (usuarioEditando == null) {
            boolean existe = SistemaController.getInstance().getUsuarios()
                    .stream()
                    .anyMatch(u -> u.getIdUsuario().equals(nuevaIdentificacion));

            if (existe) {
                alerta(Alert.AlertType.ERROR, "Identificación duplicada",
                        "Ya existe un usuario con la identificación: " + nuevaIdentificacion);
                return;
            }
        }

        Usuario nuevoUsuario = new Usuario(
                txtNombre.getText(),
                txtIdentificacion.getText(),
                txtCorreoElectronico.getText(),
                txtPassword.getText(),
                txtTelefono.getText()

        );
        listaUsuarios.add(nuevoUsuario);
        sistemaController.agregarUsuario(nuevoUsuario);




        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro exitoso");
        alert.setHeaderText(null);
        alert.setContentText("Usuario registrado: " + nuevoUsuario.getCorreoElectronico());
        alert.showAndWait();

        txtIdentificacion.clear();
        txtCorreoElectronico.clear();
        txtPassword.clear();
        txtNombre.clear();
        txtTelefono.clear();

            }
    private void alerta(Alert.AlertType type, String titulo, String msg) {
        Alert a = new Alert(type);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    @FXML
    void OnActionEliminar(ActionEvent event) {
        Usuario seleccionado = tableUsuario.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {

            listaUsuarios.remove(seleccionado);
            sistemaController.getUsuarios().remove(seleccionado);

            alerta(Alert.AlertType.INFORMATION, "Usuario eliminado",
                    "Se eliminó el usuario: " + seleccionado.getCorreoElectronico());
        } else {
            alerta(Alert.AlertType.WARNING, "Selección inválida",
                    "Debes seleccionar un usuario en la tabla.");
        }
        txtIdentificacion.clear();
        txtCorreoElectronico.clear();
        txtPassword.clear();
        txtNombre.clear();
        txtTelefono.clear();


    }

    @FXML
    void OnActionEditar(ActionEvent event) {
        Usuario seleccionado = tableUsuario.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Crear un nuevo usuario con los datos de los TextField
            Usuario usuarioEditado = new Usuario(
                    txtNombre.getText(),
                    txtIdentificacion.getText(),
                    txtCorreoElectronico.getText(),
                    txtPassword.getText(),
                    txtTelefono.getText()
            );

            // Eliminar el usuario viejo de ambas listas
            listaUsuarios.remove(seleccionado);
            sistemaController.getUsuarios().remove(seleccionado);

            // Agregar el usuario editado
            listaUsuarios.add(usuarioEditado);
            sistemaController.agregarUsuario(usuarioEditado);

            alerta(Alert.AlertType.INFORMATION, "Usuario actualizado",
                    "El usuario fue editado correctamente.");

            // Limpiar campos
            txtNombre.clear();
            txtIdentificacion.clear();
            txtCorreoElectronico.clear();
            txtPassword.clear();
            txtTelefono.clear();

        } else {
            alerta(Alert.AlertType.WARNING, "Selección inválida",
                    "Debes seleccionar un usuario en la tabla.");
        }
    }

    @FXML
    void OnActionIniciarSesion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/InicioSesion.fxml")
            );
            Parent root = loader.load();


            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();


            stage.setScene(new Scene(root));
            stage.setTitle("Inicio de Sesión");
            stage.show();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir la ventana de inicio de sesión.");
            alert.showAndWait();
        }



    }

    @FXML
    void initialize() {
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcIdentificacion.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        tcCorreoElectronico.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        tcPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcPassword.setCellFactory(column -> new TableCell<Usuario, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText("•".repeat(item.length())); // puntos en vez de la contraseña
                }
            }
        });
        tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tableUsuario.setItems(listaUsuarios);

    }






}


