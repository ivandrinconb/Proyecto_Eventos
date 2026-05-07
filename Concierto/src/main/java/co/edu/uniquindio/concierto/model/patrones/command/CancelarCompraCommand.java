package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.model.clases.Compra;

public class CancelarCompraCommand implements Command {
    private Compra compra;

    public CancelarCompraCommand(Compra compra) {
        this.compra = compra;
    }
    @Override
    public void execute() {
        compra.cancelarCompra();
    }
}
