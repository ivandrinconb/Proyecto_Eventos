package co.edu.uniquindio.concierto.model.patrones.decorator;

import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;
import co.edu.uniquindio.concierto.model.clases.Entrada;
import co.edu.uniquindio.concierto.model.clases.ServicioAdicional;

public class ConMerchandising extends ServicioAdicionalDecorator {
    public ConMerchandising(Entrada entrada) {
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
        return entrada.getDescripcion() +", Con Merchandising";
    }

    @Override
    public double getCosto() {
        return entrada.getCosto() + 10000;
    }
}
