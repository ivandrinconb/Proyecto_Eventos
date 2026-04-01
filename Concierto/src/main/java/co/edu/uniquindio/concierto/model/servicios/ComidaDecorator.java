package co.edu.uniquindio.concierto.model.servicios;

import co.edu.uniquindio.concierto.model.Compra;
import co.edu.uniquindio.concierto.model.ServicioAdicional;
import co.edu.uniquindio.concierto.model.TipoServicioAdicional;

public class ComidaDecorator extends ServicioAdicionalDecorator{
    private ServicioAdicional servicioAdicional;

    public ComidaDecorator(Compra compra) {
        this.servicioAdicional=new ServicioAdicional("4444", TipoServicioAdicional.COMIDA,"...",200);
    }
    @Override
    public double getTotal() {
        return super.getTotal() + servicioAdicional.getCosto();
    }

}
