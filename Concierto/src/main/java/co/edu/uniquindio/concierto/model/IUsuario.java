package co.edu.uniquindio.concierto.model;

public interface IUsuario {
    void registrarse(String nombre, String correo, String telefono);
    void iniciarSesion(String correo, String password);
    void modificarPerfil(String nombre, String correo, String telefono);
    void agregarMetodoPago(String metodo);
    void eliminarMetodoPago(String metodo);
    void consultarCompras();

}
