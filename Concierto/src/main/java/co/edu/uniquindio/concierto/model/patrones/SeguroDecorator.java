package co.edu.uniquindio.concierto.model.patrones;

import co.edu.uniquindio.concierto.model.clases.Compra;
import co.edu.uniquindio.concierto.model.clases.ServicioAdicional;
import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;

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
