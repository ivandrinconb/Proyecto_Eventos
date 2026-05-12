package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.Enums.EstadoEntrada;
import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Entrada;

public class PagarCompraCommand implements Command {
    private final SistemaController sistemaController;
    private final Compra compra;

    public PagarCompraCommand(SistemaController sistemaController, Compra compra) {
        this.sistemaController = sistemaController;
        this.compra = compra;
    }

    @Override
    public void execute() {
        compra.setEstadoCompra(EstadoCompra.PAGADA);

        for (int i = 0; i < compra.getCantidad(); i++) {
            Entrada entrada = new Entrada(compra.getEvento(), compra.getUsuario(), EstadoEntrada.VENDIDA);
            compra.getEntradas().add(entrada);
        }

        sistemaController.actualizarCompra(compra);
    }
}
