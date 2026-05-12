package co.edu.uniquindio.concierto.model.patrones.decorator;

public abstract class ServicioAdicionalDecorator implements IEntrada {
    protected IEntrada entrada;

    public ServicioAdicionalDecorator(IEntrada entrada) {
        this.entrada = entrada;
    }

    @Override
    public double getCosto() {
        return entrada.getCosto();
    }

    @Override
    public String getDescripcion() {
        return entrada.getDescripcion();
    }

}
