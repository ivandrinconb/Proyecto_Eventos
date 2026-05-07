package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.model.clases.Compra;

public class ReembolsarCompraCommand implements Command {
    private Compra compra;

    public ReembolsarCompraCommand(Compra compra) {
        this.compra = compra;
    }
    @Override
    public void execute() {
        compra.reembolsarCompra();

    }
}
