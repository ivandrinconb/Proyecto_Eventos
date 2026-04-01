package co.edu.uniquindio.concierto.model;

import java.util.List;

public class Usuario implements IUsuario{
    private String idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String password;
    private String telefono;
    private List<MetodoPago> metodosPago;
    private List<Compra> compras;

    public Usuario() {}

    public Usuario(String idUsuario, String nombreCompleto, String correoElectronico,String password,
                   String telefono, List<MetodoPago> metodosPago, List<Compra> compras) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.telefono = telefono;
        this.metodosPago = metodosPago;
        this.compras = compras;

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<MetodoPago> getMetodosPago() {
        return metodosPago;
    }

    public void setMetodosPago(List<MetodoPago> metodosPago) {
        this.metodosPago = metodosPago;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    @Override
    public String toString() {
        return "Usuario =" +
                "idUsuario=" + idUsuario + '\'' +
                " nombreCompleto=" + nombreCompleto + '\'' +
                " correoElectronico=" + correoElectronico + '\'' +
                " telefono=" + telefono + '\'' +
                " metodosPago=" + metodosPago +
                " compras=" + compras ;
    }



    public void registrarse(String correo, String password, String telefono) {

    }

    public void iniciarSesion(String correo, String password) {

    }

    @Override
    public void modificarPerfil(String nombre, String correo, String telefono) {

    }

    @Override
    public void agregarMetodoPago(TipoMetodoPago metodo) {

    }

    @Override
    public void eliminarMetodoPago(TipoMetodoPago metodo) {

    }

    @Override
    public void consultarCompras() {

    }


}
