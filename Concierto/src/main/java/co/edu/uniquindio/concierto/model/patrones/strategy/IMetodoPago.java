package co.edu.uniquindio.concierto.model.patrones.strategy;

public interface IMetodoPago {
    boolean procesarPago(double monto);
    boolean reembolsarPago(double monto);
    boolean validarMetodo();
    String consultarEstadoPago(String idTransaccion);
}
