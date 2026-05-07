package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.model.clases.Compra;

public class CrearCompraCommand implements Command {
    private Compra compra;
    public CrearCompraCommand(Compra compra) {
        this.compra = compra;
    }
    @Override
    public void execute() {
        compra.crearCompra();
    }
}
