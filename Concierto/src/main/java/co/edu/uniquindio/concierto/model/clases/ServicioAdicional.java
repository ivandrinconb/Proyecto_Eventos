package co.edu.uniquindio.concierto.model.clases;

import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;
import co.edu.uniquindio.concierto.model.patrones.decorator.IEntrada;

import java.util.UUID;

public class ServicioAdicional {
    private String idServicio;
    private IEntrada entradaDecorada;
    private TipoServicioAdicional tipoServicio;

    public ServicioAdicional(TipoServicioAdicional tipoServicio, IEntrada entradaDecorada) {
        this.idServicio = generarIdCorto();
        this.tipoServicio = tipoServicio;
        this.entradaDecorada = entradaDecorada;
    }

    public ServicioAdicional(String accesoPreferencial, int i) {
    }

    public String getIdServicio() {
        return idServicio;
    }

    public TipoServicioAdicional getTipoServicio() {
        return tipoServicio;
    }


    public String getDescripcion() {
        return tipoServicio.toString(); // muestra el enum directamente
    }

    public double getCosto() {

        return entradaDecorada.getCosto() ; // muestra el costo acumulado
    }

    private String generarIdCorto() {
        return UUID.randomUUID().toString().substring(0, 5);
    }
}