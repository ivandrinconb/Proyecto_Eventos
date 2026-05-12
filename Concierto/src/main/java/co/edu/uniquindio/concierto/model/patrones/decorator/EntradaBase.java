package co.edu.uniquindio.concierto.model.patrones.decorator;

public class EntradaBase implements IEntrada{
    private double costoBase;

    public EntradaBase(double costoBase) {
        this.costoBase = costoBase;
    }

    @Override
    public String getDescripcion() {
        return "Entrada Basica";
    }

    @Override
    public double getCosto() {
        return costoBase;
    }
}
