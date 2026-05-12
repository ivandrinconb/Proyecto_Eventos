package co.edu.uniquindio.concierto.model.patrones.decorator;

public class ConSeguro extends ServicioAdicionalDecorator {


    public ConSeguro(IEntrada entrada) {
        super(entrada);

}



    @Override
    public String getDescripcion() {
        return entrada.getDescripcion() + ", Con Seguro";
    }

    @Override
    public double getCosto() {
        return entrada.getCosto() + 20000;
    }
}
