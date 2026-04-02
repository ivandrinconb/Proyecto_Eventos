package co.edu.uniquindio.concierto.model.patrones;

import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.ServicioAdicional;
import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;

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
