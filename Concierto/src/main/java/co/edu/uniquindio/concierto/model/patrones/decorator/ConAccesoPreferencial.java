package co.edu.uniquindio.concierto.model.patrones.decorator;

import co.edu.uniquindio.concierto.model.clases.Entrada;
import co.edu.uniquindio.concierto.model.interfaces.IEntrada;

public class ConAccesoPreferencial extends ServicioAdicionalDecorator {
    public ConAccesoPreferencial(IEntrada entrada) {

        super(entrada);
    }

    @Override
    public void generarEntrada() {

    }

    @Override
    public void consultarEntradasPorCompra() {

    }

    @Override
    public void consultarEntradasPorEvento() {

    }

    @Override
    public void anularEntrada() {

    }

    @Override
    public String getDescripcion() {
        return entrada.getDescripcion() + ", Con Acceso Preferencial";
    }

    @Override
    public double getCosto() {
        return entrada.getCosto() + 25000;
    }
}
