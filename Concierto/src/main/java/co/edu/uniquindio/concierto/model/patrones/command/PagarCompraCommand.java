package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.model.clases.Compra;

public class PagarCompraCommand implements Command {
    private Compra compra;

    public PagarCompraCommand(Compra compra) {
        this.compra = compra;
    }
    @Override
    public void execute() {
        compra.pagarCompra();
    }
}
