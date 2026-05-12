package co.edu.uniquindio.concierto.model.patrones.command;

import co.edu.uniquindio.concierto.controller.SistemaController;
import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.Enums.TipoMetodoPago;
import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Evento;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class ConsultarCompraCommand implements Command {
    private final SistemaController sistemaController;
    private final Usuario usuario;
    private final Evento evento;
    private final EstadoCompra estado;
    private final TipoMetodoPago metodoPago;

    private ObservableList<Compra> resultado;

    public ConsultarCompraCommand(SistemaController sistemaController,
                                  Usuario usuario, Evento evento,
                                  EstadoCompra estado, TipoMetodoPago metodoPago) {
        this.sistemaController = sistemaController;
        this.usuario = usuario;
        this.evento = evento;
        this.estado = estado;
        this.metodoPago = metodoPago;
    }

    @Override
    public void execute() {
        resultado = FXCollections.observableArrayList(
                sistemaController.getListCompras().stream()
                        .filter(c -> (usuario == null || c.getUsuario().equals(usuario)) &&
                                (evento == null || c.getEvento().equals(evento)) &&
                                (estado == null || c.getEstadoCompra() == estado) &&
                                (metodoPago == null || c.getTipoMetodoPago() == metodoPago))
                        .toList()
        );
    }

    public ObservableList<Compra> getResultado() {
        return resultado;
    }
}