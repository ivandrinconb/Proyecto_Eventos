package co.edu.uniquindio.concierto.viewController;

import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuUsuarioViewController {

    @FXML
    private Label lblBienvenida;

    private Usuario usuarioActual;

    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        if (usuario != null) {
            lblBienvenida.setText("¡Bienvenido, " + usuario.getNombre() + "! ¿Qué deseas hacer hoy?");
        }
    }

    @FXML
    void onVerEventos(MouseEvent event) {
        navegarA("/co/edu/uniquindio/concierto/explorarEventos.fxml", null);
    }

    @FXML
    void onComprarEntradas(MouseEvent event) {
        // Va directo a explorar eventos para elegir un evento y comprar
        navegarA("/co/edu/uniquindio/concierto/Compra.fxml", null);
    }

    @FXML
    void onMisCompras(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/historialCompras.fxml"));
            Parent root = loader.load();
            HistorialComprasViewController ctrl = loader.getController();
            ctrl.setUsuario(usuarioActual);
            Stage stage = (Stage) lblBienvenida.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onServiciosAdicionales(MouseEvent event) {
        // Los servicios adicionales se agregan dentro del flujo de Compra (RF-009, Decorator)
        // Redirigir a explorar eventos para iniciar una compra con servicios
        mostrarInfo("Servicios adicionales",
                "Los servicios adicionales (VIP, Seguro, Merchandising, Parqueadero, " +
                        "Acceso Preferencial) se seleccionan al momento de crear tu compra.\n" +
                        "Selecciona un evento para comenzar.");
        navegarA("/co/edu/uniquindio/concierto/explorarEventos.fxml", null);
    }

    @FXML
    void onReportarIncidencia(MouseEvent event) {
        // RF-017/041: Registrar incidencias — pendiente de implementar pantalla dedicada
        mostrarInfo("Reportar Incidencia",
                "Módulo en construcción.\nPor favor contacta al administrador si tienes un problema con tu compra.");
    }

    @FXML
    void onMiPerfil(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/concierto/crearUsuario.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) lblBienvenida.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCerrarSesion(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/co/edu/uniquindio/concierto/inicioSesion.fxml"));
            Stage stage = (Stage) lblBienvenida.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void navegarA(String fxml, Usuario usuario) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) lblBienvenida.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
