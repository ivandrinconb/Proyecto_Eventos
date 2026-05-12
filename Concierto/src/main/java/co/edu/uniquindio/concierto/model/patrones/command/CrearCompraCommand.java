package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.Enums.TipoMetodoPago;
import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Evento;
import co.edu.uniquindio.concierto.model.patrones.builder.CompraBuilder;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;

public class CrearCompraCommand implements Command {
    private SistemaController sistemaController;
    private Usuario usuario;
    private Evento evento;
    private int cantidad;
    private TipoMetodoPago tipoMetodoPago;
    private EstadoCompra estadoCompra;

    public CrearCompraCommand(SistemaController sistemaController, Usuario usuario, Evento evento, int cantidad, TipoMetodoPago tipoMetodoPago, EstadoCompra estado) {
        this.sistemaController = sistemaController;
        this.usuario = usuario;
        this.evento = evento;
        this.cantidad = cantidad;
        this.tipoMetodoPago = tipoMetodoPago;
        this.estadoCompra = estado;
    }

    @Override
    public void execute() {

        Compra nuevaCompra = new CompraBuilder()
                .setUsuario(usuario)
                .setEvento(evento)
                .setCantidad(cantidad)
                .setTipoMetodoPago(tipoMetodoPago)
                .setEstadoCompra(estadoCompra)
                .build();

        sistemaController.agregarCompra(nuevaCompra);
    }
}
