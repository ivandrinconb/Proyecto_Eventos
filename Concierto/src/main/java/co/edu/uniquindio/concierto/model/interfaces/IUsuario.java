package co.edu.uniquindio.concierto.model.interfaces;

import co.edu.uniquindio.concierto.model.Enums.TipoMetodoPago;

public interface IUsuario {
    void registrarse(String nombre, String correo, String telefono);
    void iniciarSesion(String correo, String password);
    void modificarPerfil(String nombre, String correo, String telefono);
    void agregarMetodoPago(TipoMetodoPago metodo);
    void eliminarMetodoPago(TipoMetodoPago metodo);
    void consultarCompras();

}
