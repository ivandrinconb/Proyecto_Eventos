package co.edu.uniquindio.concierto.model.patrones;

import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.ServicioAdicional;
import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;

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
