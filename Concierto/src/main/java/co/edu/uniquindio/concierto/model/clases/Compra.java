package co.edu.uniquindio.concierto.model.clases;

import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.Enums.TipoMetodoPago;
import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;
import co.edu.uniquindio.concierto.model.interfaces.ICompra;
import co.edu.uniquindio.concierto.model.patrones.factoryMethod.MetodoPagoFactory;
import co.edu.uniquindio.concierto.model.patrones.strategy.IMetodoPago;
import co.edu.uniquindio.concierto.model.patrones.strategy.ProcesadorPago;

import java.time.LocalDateTime;
import java.util.List;

public class Compra implements ICompra {
    private String idCompra;
    private Usuario usuario;
    private Evento evento;
    private LocalDateTime fechaCreacion;
    private double total;
    private EstadoCompra estadoCompra;
    private List<Entrada> entradas;
    private List<ServicioAdicional> serviciosAdicionales;

    public Compra() {}

    public Compra(String idCompra, Usuario usuario, Evento evento,
                  LocalDateTime fechaCreacion, double total, EstadoCompra estadoCompra, List<Entrada> entradas,
                  List<ServicioAdicional> serviciosAdicionales) {
        this.idCompra = idCompra;
        this.usuario = usuario;
        this.evento = evento;
        this.fechaCreacion = fechaCreacion;
        this.total = total;
        this.estadoCompra = estadoCompra;
        this.entradas = entradas;
        this.serviciosAdicionales = serviciosAdicionales;

    }
    public String getIdCompra() {
        return idCompra;
    }
    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public EstadoCompra getEstadoCompra() {
        return estadoCompra;
    }
    public void setEstadoCompra(EstadoCompra estadoCompra) {
        this.estadoCompra = estadoCompra;
    }
    public List<Entrada> getEntradas() {
        return entradas;
    }
    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    @Override
    public String toString() {
        return "Compra=" +
                "idCompra=" + idCompra + '\'' +
                " usuario=" + usuario +
                " evento=" + evento +
                " fechaCreacion=" + fechaCreacion +
                " total=" + total +
                " estadoCompra=" + estadoCompra +
                " entradas=" + entradas +
                " serviciosAdicionales=" + serviciosAdicionales ;
    }


    @Override
    public void crearCompra() {
        this.fechaCreacion = LocalDateTime.now();
        this.estadoCompra = EstadoCompra.CREADA;
        calcularTotal();
    }
    public void calcularTotal() {
        double subtotalEntradas = entradas.stream().mapToDouble(Entrada::getCosto).sum();
        double subtotalServicios = serviciosAdicionales.stream().mapToDouble(ServicioAdicional::getCosto).sum();
        this.total = subtotalEntradas + subtotalServicios;
    }



    @Override
    public void modificarCompra() {
        if (estadoCompra == EstadoCompra.CREADA) {
            // permitir cambios antes del pago
            calcularTotal();
        } else {
            System.out.println("No se puede modificar una compra ya pagada o cancelada.");
        }
    }


    @Override
    public void cancelarCompra() {
        if (estadoCompra == EstadoCompra.CREADA || estadoCompra == EstadoCompra.PAGADA) {
            this.estadoCompra = EstadoCompra.CANCELADA;
        }
    }



    @Override
    public void pagarCompra() {
        IMetodoPago metodo = MetodoPagoFactory.crearMetodo(TipoMetodoPago.TARJETA);

        ProcesadorPago procesador = new ProcesadorPago();
        procesador.setMetodoPago(metodo);
        procesador.ejecutarPago(total);

        this.estadoCompra = EstadoCompra.PAGADA;
    }

    @Override
    public void consultarDetalleCompra() {
        System.out.println(this.toString());

    }

    @Override
    public void agregarServicioAdicional(TipoServicioAdicional tipoServicio) {
        ServicioAdicional servicio = new ServicioAdicional(
                "S-" + tipoServicio,
                tipoServicio,
                "Servicio " + tipoServicio,
                10000
        );
        this.serviciosAdicionales.add(servicio);
        calcularTotal();


    }


}
