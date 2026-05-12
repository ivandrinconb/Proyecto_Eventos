package co.edu.uniquindio.concierto.controller;

import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;

public class CompraController {

    private SistemaController sistema;

    public CompraController() {
        sistema = SistemaController.getInstance();
    }

    public void crearCompra(Compra compra) {
        sistema.agregarCompra(compra);
    }

    public void cancelarCompra(String idCompra) {
        for (Compra c : sistema.getCompras()) {
            if (c.getIdCompra().equals(idCompra)) {
                c.setEstadoCompra(EstadoCompra.CANCELADA);
                break;
            }
        }
    }

    public void modificarCompra(Compra compra) {
        cancelarCompra(compra.getIdCompra());
        crearCompra(compra);
    }
}
