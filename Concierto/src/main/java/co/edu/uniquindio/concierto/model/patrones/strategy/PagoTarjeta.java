package co.edu.uniquindio.concierto.model.patrones.strategy;

import javafx.scene.control.Alert;

public class PagoTarjeta implements IMetodoPago{
    public boolean procesarPago(double monto) {

        mostrarMensaje("Pago con Tarjeta realizado por: $" + monto, Alert.AlertType.INFORMATION);
        return true;
    }

    @Override
    public boolean reembolsarPago(double monto) {
        mostrarMensaje("Reembolso con Tarjeta por: $" + monto, Alert.AlertType.INFORMATION);
        return true;
    }

    @Override
    public boolean validarMetodo() {

        mostrarMensaje("Validando cuenta Tarjeta...", Alert.AlertType.CONFIRMATION);
        return true;
    }

    @Override
    public String consultarEstadoPago(String idTransaccion) {
        String estado = "Transacción " + idTransaccion + " confirmada en la Tarjeta.";
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
