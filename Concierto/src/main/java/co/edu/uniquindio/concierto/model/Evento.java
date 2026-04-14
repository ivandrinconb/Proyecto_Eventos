package co.edu.uniquindio.concierto.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Evento {
    private String idEvento;
    private String nombre;
    private CategoriaEvento categoria;
    private String descripcion;
    private String ciudad;
    private LocalDateTime fechaHora;
    private EstadoEvento estadoEvento;
    private String politicas;
    private Recinto recinto;
    private List<Usuario> usuariosAsistentes;
    private List<Zona> zonas;

}
