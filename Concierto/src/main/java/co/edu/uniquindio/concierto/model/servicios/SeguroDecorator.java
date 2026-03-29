package co.edu.uniquindio.concierto.model.servicios;

import co.edu.uniquindio.concierto.model.Compra;
import co.edu.uniquindio.concierto.model.ServicioAdicional;
import co.edu.uniquindio.concierto.model.TipoServicioAdicional;

public class SeguroDecorator extends ServicioAdicionalDecorator {
    private ServicioAdicional servicioAdicional;

    public SeguroDecorator(Compra compra) {
        super(compra);
        this.servicioAdicional= new ServicioAdicional("2222", TipoServicioAdicional.SEGURO, "...",650);

    }
    @Override
    public double getTotal() {
        return super.getTotal() + servicioAdicional.getCosto();
    }
}
