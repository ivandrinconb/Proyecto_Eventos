package co.edu.uniquindio.concierto.model.patrones.decorator;

public class ConVIP extends ServicioAdicionalDecorator {

    public ConVIP(IEntrada entrada) {
        super(entrada);


    }

    @Override
    public String getDescripcion() {
        return entrada.getDescripcion() + ", Con VIP";
    }

    @Override
    public double getCosto() {
        return entrada.getCosto() + 30000;
    }
}
