package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.Enums.TipoMetodoPago;
import co.edu.uniquindio.concierto.model.clases.Compra;

public class ModificarCompraCommand implements Command {
    private final SistemaController sistemaController;
    private final Compra compra;
    private final int nuevaCantidad;
    private final TipoMetodoPago nuevoMetodo;
    private final EstadoCompra nuevoEstado;

    public ModificarCompraCommand(SistemaController sistemaController, Compra compra,
                                  int nuevaCantidad, TipoMetodoPago nuevoMetodo, EstadoCompra nuevoEstado) {
        this.sistemaController = sistemaController;
        this.compra = compra;
        this.nuevaCantidad = nuevaCantidad;
        this.nuevoMetodo = nuevoMetodo;
        this.nuevoEstado = nuevoEstado;
    }

    @Override
    public void execute() {
        compra.setCantidad(nuevaCantidad);
        compra.setTipoMetodoPago(nuevoMetodo);
        compra.setEstadoCompra(nuevoEstado);
        sistemaController.actualizarCompra(compra);
    }
}
