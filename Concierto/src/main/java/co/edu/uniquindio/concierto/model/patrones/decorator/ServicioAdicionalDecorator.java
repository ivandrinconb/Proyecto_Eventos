package co.edu.uniquindio.concierto.model.patrones.decorator;

import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.Entrada;
import co.edu.uniquindio.concierto.model.interfaces.IEntrada;

public abstract class ServicioAdicionalDecorator implements IEntrada {
    protected Entrada entrada;



    public ServicioAdicionalDecorator(Entrada entrada) {


    }


}
