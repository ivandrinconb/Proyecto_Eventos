package co.edu.uniquindio.concierto.model.servicios;

import co.edu.uniquindio.concierto.model.Compra;
import co.edu.uniquindio.concierto.model.ServicioAdicional;
import co.edu.uniquindio.concierto.model.TipoServicioAdicional;

public class EstacionamientoDecorator extends ServicioAdicionalDecorator{
    private ServicioAdicional servicioAdicional;

    public EstacionamientoDecorator(Compra compra) {
        this.servicioAdicional= new ServicioAdicional("3333", TipoServicioAdicional.ESTACIONAMIENTO,"...",300);

    }
    @Override
    public double getTotal() {
        return super.getTotal() + servicioAdicional.getCosto();
    }
}
