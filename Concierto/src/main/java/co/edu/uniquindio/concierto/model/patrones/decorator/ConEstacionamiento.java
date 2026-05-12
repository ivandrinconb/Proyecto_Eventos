package co.edu.uniquindio.concierto.model.patrones.decorator;

public class ConEstacionamiento extends ServicioAdicionalDecorator {

    public ConEstacionamiento(IEntrada entrada) {
        super(entrada);

    }


    @Override
    public String getDescripcion() {
        return entrada.getDescripcion() + "Con Estacionamiento";
    }

    @Override
    public double getCosto() {
        return entrada.getCosto() + 15000;
    }
}

