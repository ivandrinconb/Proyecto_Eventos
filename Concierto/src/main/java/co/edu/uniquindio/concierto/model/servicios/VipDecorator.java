package co.edu.uniquindio.concierto.model.servicios;

import co.edu.uniquindio.concierto.model.Compra;
import co.edu.uniquindio.concierto.model.ServicioAdicional;
import co.edu.uniquindio.concierto.model.TipoServicioAdicional;

public class VipDecorator extends ServicioAdicionalDecorator {
    private ServicioAdicional servicioAdicional;

    public VipDecorator(Compra compra) {
        super(compra);
        this.servicioAdicional = new ServicioAdicional("1111", TipoServicioAdicional.VIP,"...",1.000);

    }
    @Override
    public double getTotal() {
        return super.getTotal() + servicioAdicional.getCosto();
    }

}
