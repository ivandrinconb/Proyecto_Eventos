package co.edu.uniquindio.concierto.model;

public interface ICompra {
    void crearCompra();
    void modificarCompra();
    void cancelarCompra();
    void pagarCompra();
    void consultarDetalleCompra();
    void agregarServicioAdicional(TipoServicioAdicional tipoServicio);
}
