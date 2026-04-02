package co.edu.uniquindio.concierto.model.patrones;

import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.ServicioAdicional;
import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;

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
