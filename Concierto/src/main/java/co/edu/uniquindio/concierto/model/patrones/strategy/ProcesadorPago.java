package co.edu.uniquindio.concierto.model.patrones.strategy;

public class ProcesadorPago {
    private IMetodoPago metodoPago;

    public void setMetodoPago(IMetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void ejecutarPago(double monto) {
        if (metodoPago != null && metodoPago.validarMetodo()) {
            metodoPago.procesarPago(monto);
        } else {
            System.out.println("Método de pago inválido.");
        }
    }
}
