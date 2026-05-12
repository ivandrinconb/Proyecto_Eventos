package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.Enums.EstadoEntrada;
import co.edu.uniquindio.concierto.model.clases.Compra;

public class ReembolsarCompraCommand implements Command {
    private final SistemaController sistemaController;
    private final Compra compra;

    public ReembolsarCompraCommand(SistemaController sistemaController, Compra compra) {
        this.sistemaController = sistemaController;
        this.compra = compra;
    }

    @Override
    public void execute() {
        compra.setEstadoCompra(EstadoCompra.REEMBOLSADA);
        compra.getEntradas().forEach(e -> e.setEstadoEntrada(EstadoEntrada.ANULADA));
        sistemaController.actualizarCompra(compra);
    }
}
