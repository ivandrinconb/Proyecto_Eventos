package co.edu.uniquindio.concierto.model.patrones.decorator;

public class ConMerchandising extends ServicioAdicionalDecorator {
    public ConMerchandising(IEntrada entrada) {
        super(entrada);
    }



    @Override
    public String getDescripcion() {
        return entrada.getDescripcion() +", Con Merchandising";
    }

    @Override
    public double getCosto() {
        return entrada.getCosto() + 10000;
    }
}
