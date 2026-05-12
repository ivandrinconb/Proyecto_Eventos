package co.edu.uniquindio.concierto.model.patrones.composite;

import co.edu.uniquindio.concierto.model.Enums.EstadoAsiento;

import java.util.UUID;

public class Asiento implements ComponenteRecinto {
    private String idAsiento;
    private String fila;
    private int numero;
    private EstadoAsiento estadoAsiento;


    public Asiento(String idAsiento, String fila, int numero, EstadoAsiento estadoAsiento) {
        this.idAsiento = generarIdCorto();
        this.fila = fila;
        this.numero = numero;
        this.estadoAsiento = estadoAsiento;

    }

    public Asiento(String fila, Integer numero, EstadoAsiento estado) {
        this.idAsiento = generarIdCorto();
        this.fila = fila;
        this.numero = numero;
        this.estadoAsiento = estado;
    }

    @Override
    public void mostrar() {
            System.out.println("Asiento " + idAsiento + " - Fila: " + fila +
                    ", Número: " + numero + ", Estado: " + estadoAsiento);

    }
    private String generarIdCorto() {
        return UUID.randomUUID().toString().substring(0, 5);
    }







    public String getIdAsiento() {
        return idAsiento;
    }
    public void setIdAsiento(String idAsiento) {
        this.idAsiento = idAsiento;
    }
    public String getFila() {
        return fila;
    }
    public void setFila(String fila) {
        if (fila != null && fila.matches("[A-Za-z]+")) { // solo letras
            this.fila = fila.toUpperCase(); // opcional: convertir a mayúsculas
        } else {
            throw new IllegalArgumentException("La fila debe contener solo letras.");
        }
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public EstadoAsiento getEstadoAsiento() {
        return estadoAsiento;
    }
    public void setEstadoAsiento(EstadoAsiento estadoAsiento) {
        if (estadoAsiento != null) {
            this.estadoAsiento = estadoAsiento;
        }
    }

    @Override
    public String toString() {
        return "Asiento =" +
                " idAsiento=" + idAsiento + '\'' +
                " fila=" + fila + '\'' +
                " numero=" + numero +
                " estadoAsiento=" + estadoAsiento ;
    }
}
