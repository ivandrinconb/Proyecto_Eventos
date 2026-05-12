package co.edu.uniquindio.concierto.model.patrones.composite;

import co.edu.uniquindio.concierto.model.Enums.TipoZona;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Zona implements ComponenteRecinto {
    private String idZona;
    private TipoZona tipoZona;
    private int capacidad;
    private double precioBase;
    private List<Asiento> asientos;
    private Recinto recinto;


    public Zona(String idZona,TipoZona tipoZona,  int capacidad,
                double precioBase,  List<Asiento> asientos) {
        this.idZona = generarIdCorto(); // o usa el idZona recibido si lo prefieres
        this.tipoZona = tipoZona;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.asientos = (asientos != null) ? asientos : new ArrayList<>();
    }

    public Zona(TipoZona tipo, Integer capacidad, double precioBase) {
        this.idZona = generarIdCorto();
        this.tipoZona = tipo;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.asientos = new ArrayList<>();
    }

    @Override
    public void mostrar() {
        String detalle = "Zona: " +  " (Capacidad: " + capacidad + ", Precio base: " + precioBase + ")";
        System.out.println(detalle); // luego lo reemplazas por actualización en JavaFX
        for (Asiento asiento : asientos) {
            asiento.mostrar();
        }
    }
    private String generarIdCorto() {
        return UUID.randomUUID().toString().substring(0, 5);
    }
    public void agregarComponente(Asiento asiento) {
        this.asientos.add(asiento);
    }















    public String getIdZona() {
        return idZona;
    }
    public void setIdZona(String idZona) {
        this.idZona = idZona;
    }

    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public double getPrecioBase() {
        return precioBase;
    }
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
    public List<Asiento> getAsientos() {
        return asientos;
    }
    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    public TipoZona getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(TipoZona tipoZona) {
        this.tipoZona = tipoZona;
    }

    public Recinto getRecinto() {
        return recinto;
    }

    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }

    @Override
    public String toString() {
        return "Zona{" +
                "idZona='" + idZona + '\'' +
                ", tipoZona=" + tipoZona +
                ", capacidad=" + capacidad +
                ", precioBase=" + precioBase +
                ", asientos=" + asientos +
                ", recinto=" + recinto +
                '}';
    }
}
