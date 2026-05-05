package co.edu.uniquindio.concierto.model.clases;

import java.util.List;

public class Administrador extends Usuario{

    private String idAdministrador;
    private String nombre;
    private String correoElectronico;
    private String password;
    private String telefono;
    private List<Evento> eventos;
    private List<Usuario> usuarios;

    public Administrador(String idAdministrador, String nombre, String correoElectronico, String password, String telefono) {
        this.idAdministrador = idAdministrador;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.telefono = telefono;


    }

    public Administrador(String idAdministrador, String nombre, String correoElectronico, String password, String telefono,
                         List<Evento> eventos, List<Usuario> usuarios) {
        this.idAdministrador = idAdministrador;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.telefono = telefono;
        this.eventos = eventos;
        this.usuarios = usuarios;

    }
    public String getIdAdministrador() {
        return idAdministrador;
    }
    public void setIdAdministrador(String idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<Evento> getEventos() {
        return eventos;
    }
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


}
