package co.edu.uniquindio.concierto.model.interfaces;

import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;

public interface ICompra {
    void crearCompra();
    void modificarCompra();
    void cancelarCompra();
    void pagarCompra();
    void consultarDetalleCompra();
    void agregarServicioAdicional(TipoServicioAdicional tipoServicio);
}
