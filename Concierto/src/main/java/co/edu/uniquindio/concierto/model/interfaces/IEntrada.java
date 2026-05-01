package co.edu.uniquindio.concierto.model.interfaces;

public interface IEntrada {
    void generarEntrada();
    void consultarEntradasPorCompra();
    void consultarEntradasPorEvento();
    void anularEntrada();
    String getDescripcion();
    double getCosto();

}
