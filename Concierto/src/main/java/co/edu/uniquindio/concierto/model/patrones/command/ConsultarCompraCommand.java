package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.model.clases.Compra;

public class ConsultarCompraCommand implements Command {
    private Compra compra;

    public ConsultarCompraCommand(Compra compra) {
        this.compra = compra;
    }
    @Override
    public void execute() {
        compra.consultarDetalleCompra();
    }

}
