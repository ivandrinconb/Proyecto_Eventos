package co.edu.uniquindio.concierto.model.patrones.decorator;

public class ConAccesoPreferencial extends ServicioAdicionalDecorator {
    public ConAccesoPreferencial(IEntrada entrada) {

        super(entrada);
    }



    @Override
    public String getDescripcion() {
        return entrada.getDescripcion() + ", Con Acceso Preferencial";
    }

    @Override
    public double getCosto() {
        return entrada.getCosto() + 25000;
    }
}
