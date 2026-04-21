package co.edu.uniquindio.concierto.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.concierto.controller.UsuarioController;
import co.edu.uniquindio.concierto.model.clases.MetodoPago;
import co.edu.uniquindio.concierto.model.clases.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CrearUsuarioViewController {

    private UsuarioController usuarioController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVolver;

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


    @FXML
    void OnActionRegistrar(ActionEvent event) {
        Usuario nuevoUsuario = new Usuario(
                txtNombre.getText(),
                txtIdentificacion.getText(),
                txtCorreoElectronico.getText(),
                txtPassword.getText(),
                txtTelefono.getText());

        usuarioController.registrarUsuario(nuevoUsuario);
        listaUsuarios.add(nuevoUsuario);
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
    void OnActionVolver(ActionEvent event) {

    }

    @FXML
    void initialize() {



        Usuario usuario = new Usuario();

        tcNombre.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getNombre()));
        tcIdentificacion.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getIdUsuario()));
        tcCorreoElectronico.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCorreoElectronico()));
        tcPassword.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getPassword()));
        tcTelefono.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getTelefono()));



    }




}


