package co.edu.uniquindio.concierto.model.patrones.strategy;

import co.edu.uniquindio.concierto.model.clases.MetodoPago;
import javafx.scene.control.Alert;

public class PagoPaypal implements IMetodoPago {

    @Override
    public boolean procesarPago(double monto) {

        mostrarMensaje("Pago con PayPal realizado por: $" + monto, Alert.AlertType.INFORMATION);
        return true;
    }

    @Override
    public boolean reembolsarPago(double monto) {
        mostrarMensaje("Reembolso con PayPal por: $" + monto, Alert.AlertType.INFORMATION);
        return true;
    }

    @Override
    public boolean validarMetodo() {

        mostrarMensaje("Validando cuenta PayPal...", Alert.AlertType.CONFIRMATION);
        return true;
    }

    @Override
    public String consultarEstadoPago(String idTransaccion) {
        String estado = "Transacción " + idTransaccion + " confirmada en PayPal.";
        mostrarMensaje(estado, Alert.AlertType.INFORMATION);
        return estado;
    }


    private void mostrarMensaje(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("PayPal");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
