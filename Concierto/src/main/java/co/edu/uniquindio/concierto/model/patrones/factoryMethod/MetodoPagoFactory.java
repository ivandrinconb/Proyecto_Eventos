package co.edu.uniquindio.concierto.model.patrones.factoryMethod;

import co.edu.uniquindio.concierto.model.Enums.TipoMetodoPago;
import co.edu.uniquindio.concierto.model.patrones.strategy.IMetodoPago;
import co.edu.uniquindio.concierto.model.patrones.strategy.PagoPaypal;
import co.edu.uniquindio.concierto.model.patrones.strategy.PagoPse;
import co.edu.uniquindio.concierto.model.patrones.strategy.PagoTarjeta;

public class MetodoPagoFactory {
    public static IMetodoPago crearMetodo(TipoMetodoPago tipo) {
        switch (tipo) {
            case TARJETA: return new PagoTarjeta();
            case PSE: return new PagoPse();
            case PAYPAL: return new PagoPaypal();
            default: throw new IllegalArgumentException("Método de pago no soportado: " + tipo);
            }
        }

}
