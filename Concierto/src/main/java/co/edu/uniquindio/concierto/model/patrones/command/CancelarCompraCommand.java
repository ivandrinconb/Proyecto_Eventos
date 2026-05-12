package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.clases.Compra;

public class CancelarCompraCommand implements Command {
    private final SistemaController sistemaController;
    private final Compra compra;

    public CancelarCompraCommand(SistemaController sistemaController, Compra compra) {
        this.sistemaController = sistemaController;
        this.compra = compra;
    }

    @Override
    public void execute() {
        compra.setEstadoCompra(EstadoCompra.CANCELADA);
        sistemaController.actualizarCompra(compra);
    }
}