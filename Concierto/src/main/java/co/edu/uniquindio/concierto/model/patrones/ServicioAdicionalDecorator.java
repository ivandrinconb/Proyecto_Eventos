package co.edu.uniquindio.concierto.model.patrones;

import co.edu.uniquindio.concierto.model.clases.Compra;

public abstract class ServicioAdicionalDecorator extends Compra {
    protected Compra compra;

    public ServicioAdicionalDecorator() {}

    public ServicioAdicionalDecorator(Compra compra) {
        this.compra = compra;
    }

    @Override
    public double getTotal() {
        return compra.getTotal();
    }
}
