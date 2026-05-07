package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.model.clases.Compra;

public class ModificarCompraCommand implements Command {
    private Compra compra;

    public ModificarCompraCommand(Compra compra) {
        this.compra = compra;
    }
    @Override
    public void execute() {
        compra.modificarCompra();
    }

}
