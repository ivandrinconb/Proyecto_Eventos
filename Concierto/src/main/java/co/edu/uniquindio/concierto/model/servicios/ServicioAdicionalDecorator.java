package co.edu.uniquindio.concierto.model.servicios;

import co.edu.uniquindio.concierto.model.Compra;

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
