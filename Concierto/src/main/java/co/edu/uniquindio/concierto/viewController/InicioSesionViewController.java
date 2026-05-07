package co.edu.uniquindio.concierto.viewController;


import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.clases.Administrador;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioSesionViewController {



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BtnIngresar;

    @FXML
    private TextField TxtCorreo;

    @FXML
    private PasswordField TxtPassword;

    @FXML
    void OnActionIngresar(ActionEvent event) {
        String correo = TxtCorreo.getText();
        String password = TxtPassword.getText();

        if (correo.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor ingrese correo y contraseña.", Alert.AlertType.WARNING);
            return;
        }

        Usuario usuario = SistemaController.getInstance().buscarUsuarioPorCredenciales(correo, password);
        Administrador admin = SistemaController.getInstance().buscarAdministradorPorCredenciales(correo, password);

        Object persona = (usuario != null) ? usuario : admin;

        if (persona != null) {
            mostrarAlerta("Inicio de sesión exitoso", "Bienvenido, " +
                            (persona instanceof Administrador ? ((Administrador) persona).getNombre() : ((Usuario) persona).getNombre()),
                    Alert.AlertType.INFORMATION);

            try {
                String fxml = (persona instanceof Administrador)
                        ? "/co/edu/uniquindio/concierto/menuAdministrador.fxml"
                        : "/co/edu/uniquindio/concierto/menuUsuario.fxml";

                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
                Parent root = loader.load();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Menú Principal");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error de autenticación", "Correo o contraseña incorrectos.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    @FXML
    void OnActionCrearCuenta(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/CrearUsuario.fxml")
            );
            Parent root = loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            stage.setTitle("Registro de Usuario");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir la ventana de registros.");
            alert.showAndWait();
        }



    }


    @FXML
    void initialize() {

    }

}