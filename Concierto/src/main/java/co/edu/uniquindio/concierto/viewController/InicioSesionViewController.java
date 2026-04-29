package co.edu.uniquindio.concierto.viewController;


import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.clases.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private TextField TxtPassword;

    @FXML
    void OnActionIngresar(ActionEvent event) {
        String correo = TxtCorreo.getText();
        String password = TxtPassword.getText();

        if (TxtCorreo.getText().isEmpty() || TxtPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese correo y contraseña.");
            alert.showAndWait();
            return;
        }


        Usuario usuario = SistemaController.getInstance().buscarUsuarioPorCredenciales(correo, password);

        if (usuario != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inicio de sesión exitoso");
            alert.setHeaderText(null);
            alert.setContentText("Bienvenido, " + usuario.getNombre());
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de autenticación");
            alert.setHeaderText(null);
            alert.setContentText("Correo o contraseña incorrectos.");
            alert.showAndWait();
        }



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