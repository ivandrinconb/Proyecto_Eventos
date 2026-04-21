package co.edu.uniquindio.concierto.controller;

import co.edu.uniquindio.concierto.model.clases.Usuario;
import co.edu.uniquindio.concierto.viewController.CrearUsuarioViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private final ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }


    public ObservableList<Usuario> getUsuarios() {
        return usuarios;
    }
}
