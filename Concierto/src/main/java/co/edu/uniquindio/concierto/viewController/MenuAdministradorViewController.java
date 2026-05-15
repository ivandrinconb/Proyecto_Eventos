package co.edu.uniquindio.concierto.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.concierto.controller.SistemaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MenuAdministradorViewController {
    private SistemaController sistemaController;

    @FXML
    private Button btnCerrarSesion;




    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGestionarCompras;

    @FXML
    private Button btnGestionarEventos;

    @FXML
    private Button btnGestionarIncidencias;

    @FXML
    private Button btnGestionarRecintos;

    @FXML
    private Button btnGestionarUsuarios;

    @FXML
    private Button btnVerReportes;

    @FXML
    void OnActionCerrarSesion(ActionEvent event) {
        try {
            List<Window> ventanas = new ArrayList<>(Window.getWindows());
            for (Window window : ventanas) {
                if (window instanceof Stage) {
                    ((Stage) window).close();
                }
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/concierto/InicioSesion.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Inicio");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo volver al Inicio.", Alert.AlertType.ERROR);
        }


    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


    @FXML
    void OnActionGenerarIncidencias(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/GestionIncidencia.fxml")
            );
            Parent root = loader.load();
            GestionIncidenciaViewController controller = (GestionIncidenciaViewController) loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Incidencias");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir la ventana de gestión de Incidencias.");
            alert.showAndWait();
        }


    }

    @FXML
    void OnActionGestionarCompras(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/GestionCompra.fxml")
            );
            Parent root = loader.load();
            GestionCompraViewController controller = (GestionCompraViewController) loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Compras");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir la ventana de gestión de Compras.");
            alert.showAndWait();
        }

    }

    @FXML
    void OnActionGestionarEventos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/GestionEvento.fxml")
            );
            Parent root = loader.load();
            GestionEventoViewController controller = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Evento");
            stage.show();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir la ventana de gestión de Eventos.");
            alert.showAndWait();
        }

    }

    @FXML
    void OnActionGestionarRecintos(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/GestionRecinto.fxml")
            );
            Parent root = loader.load();

            GestionRecintoViewController controller = loader.getController();

            // 🔹 Obtener la ventana actual (Menú Administrador) y cerrarla
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.close();

            // 🔹 Abrir la nueva ventana de Recintos
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Recintos");
            stage.show();

        } catch (
                Exception e) {
            e.printStackTrace(); // muestra el error exacto en consola
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir la ventana de gestión de recintos.");
            alert.showAndWait();
        }
    }



    @FXML
    void OnActionGestionarUsuarios(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/CrearUsuario.fxml")
            );
            Parent root = loader.load();

            CrearUsuarioViewController controller = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Usuarios");
            stage.show();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir la ventana de gestión de usuarios.");
            alert.showAndWait();
        }

    }

    @FXML
    void OnActionVerReportes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/VerReportes.fxml")
            );
            Parent root = loader.load();
            VerReportesViewController controller = (VerReportesViewController) loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reportes");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir la ventana de Reportes.");
            alert.showAndWait();
        }

    }

    @FXML
    void initialize() {

    }

}
