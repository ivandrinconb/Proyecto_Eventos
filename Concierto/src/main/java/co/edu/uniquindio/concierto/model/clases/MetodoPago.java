package co.edu.uniquindio.concierto.model.clases;

import co.edu.uniquindio.concierto.model.Enums.TipoMetodoPago;

public class MetodoPago {
    private String idMetodo;
    private TipoMetodoPago tipo;
    private String numero;

    public MetodoPago(String idMetodo, TipoMetodoPago tipo, String numero) {
        this.idMetodo = idMetodo;
        this.tipo = tipo;
        this.numero = numero;

    }
    public String getIdMetodo() {
        return idMetodo;
    }
    public void setIdMetodo(String idMetodo) {
        this.idMetodo = idMetodo;
    }
    public TipoMetodoPago getTipo() {
        return tipo;
    }
    public void setTipo(TipoMetodoPago tipo) {
        this.tipo = tipo;

    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

}
