package co.edu.uniquindio.concierto.model.clases;

import co.edu.uniquindio.concierto.model.Enums.EstadoCompra;
import co.edu.uniquindio.concierto.model.Enums.EstadoEntrada;
import co.edu.uniquindio.concierto.model.Enums.TipoMetodoPago;
import co.edu.uniquindio.concierto.model.Enums.TipoServicioAdicional;
import co.edu.uniquindio.concierto.model.interfaces.ICompra;
import co.edu.uniquindio.concierto.model.patrones.composite.Asiento;
import co.edu.uniquindio.concierto.model.patrones.composite.Zona;
import co.edu.uniquindio.concierto.model.patrones.decorator.EntradaBase;
import co.edu.uniquindio.concierto.model.patrones.decorator.IEntrada;
import co.edu.uniquindio.concierto.model.patrones.factoryMethod.MetodoPagoFactory;
import co.edu.uniquindio.concierto.model.patrones.observer.Usuario;
import co.edu.uniquindio.concierto.model.patrones.strategy.IMetodoPago;
import co.edu.uniquindio.concierto.model.patrones.strategy.ProcesadorPago;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Compra implements ICompra {
    private String idCompra;
    private Usuario usuario;
    private Evento evento;
    private IEntrada entrada;
    private int cantidad;
    private MetodoPago metodoPago;
    private TipoMetodoPago tipoMetodoPago;
    private LocalDateTime fechaCreacion;
    private double total;
    private EstadoCompra estadoCompra;
    private List<Entrada> entradas;
    private List<ServicioAdicional> serviciosAdicionales;

    public Compra(double precioBase) {
        this.entrada = new EntradaBase(precioBase);
        this.entradas = (entradas != null) ? entradas : new ArrayList<>();
        this.serviciosAdicionales = (serviciosAdicionales != null) ? serviciosAdicionales : new ArrayList<>();
    }

    public Compra(String idCompra, Usuario usuario, Evento evento,
                  int cantidad,MetodoPago metodoPago,
                  LocalDateTime fechaCreacion, double total, EstadoCompra estadoCompra, List<Entrada> entradas,
                  List<ServicioAdicional> serviciosAdicionales) {
        this.idCompra = generarIdCorto();
        this.usuario = usuario;
        this.evento = evento;
        this.cantidad = cantidad;
        this.metodoPago = metodoPago;
        this.fechaCreacion = fechaCreacion;
        this.total = total;
        this.estadoCompra = estadoCompra;
        this.entradas = entradas != null ? entradas : new ArrayList<>();
        this.serviciosAdicionales = serviciosAdicionales != null ? serviciosAdicionales : new ArrayList<>();
        this.idCompra = generarIdCorto();
        this.evento = evento;
        this.fechaCreacion = LocalDateTime.now();
        this.estadoCompra = EstadoCompra.PENDIENTE;



    }



    public Compra(String idEntrada, Zona zona, Asiento asiento, double precioBase, EstadoEntrada estado) {

        this.entrada = new Entrada(idEntrada, zona, asiento, precioBase, estado);
    }

    public Compra(String idCompra, Usuario usuario, Evento evento, int cantidad, TipoMetodoPago tipoMetodoPago,
                  LocalDateTime fechaCreacion, double total, EstadoCompra estadoCompra, List<Entrada> entradas,
                  List<ServicioAdicional> serviciosAdicionales) {

        this.idCompra = generarIdCorto();
        this.usuario = usuario;
        this.evento = evento;
        this.cantidad = cantidad;
        this.tipoMetodoPago = tipoMetodoPago;
        this.fechaCreacion = fechaCreacion;
        this.total = total;
        this.estadoCompra = estadoCompra;
        this.entradas = entradas;
        this.entradas = (entradas != null) ? entradas : new ArrayList<>();
        this.serviciosAdicionales = (serviciosAdicionales != null) ? serviciosAdicionales : new ArrayList<>();



    }



    public Compra(String string, Usuario usuarioActual, Evento evento, LocalDateTime now, int i, EstadoCompra estadoCompra, ArrayList<Object> objects, ArrayList<Object> objects1) {
    }

    public Compra(Evento eventoSeleccionado, ObservableList<Entrada> listaEntradas) {
        this.idCompra = generarIdCorto();
        this.evento = eventoSeleccionado;
        this.entradas = (listaEntradas != null) ? new ArrayList<>(listaEntradas) : new ArrayList<>();
        this.serviciosAdicionales = new ArrayList<>();
        this.fechaCreacion = LocalDateTime.now();
        this.estadoCompra = EstadoCompra.PENDIENTE;
        this.total = 0;
    }


    public IEntrada getEntrada() {
        return entrada;
    }

    public void setEntrada(IEntrada entrada) {
        this.entrada = entrada;
    }

    private String generarIdCorto() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    public TipoMetodoPago getTipoMetodoPago() {
        return tipoMetodoPago;
    }

    public void setTipoMetodoPago(TipoMetodoPago tipoMetodoPago) {
        this.tipoMetodoPago = tipoMetodoPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    }
    public void calcularTotal() {
        double subtotalEntradas = entradas.stream()
                .mapToDouble(Entrada::getPrecioFinal) // ✅ usa precioFinal
                .sum();

        // Sumar el costo de todos los servicios adicionales
        double subtotalServicios = serviciosAdicionales.stream()
                .mapToDouble(ServicioAdicional::getCosto)
                .sum();

        // Guardar el total en el atributo de la compra
        this.total = subtotalEntradas + subtotalServicios;
    }



    @Override
    public void modificarCompra() {

    }


    @Override
    public void cancelarCompra() {

    }



    @Override
    public void pagarCompra() {

    }

    @Override
    public void consultarDetalleCompra() {


    }

    @Override
    public void agregarServicioAdicional(TipoServicioAdicional tipoServicio) {



    }

    @Override
    public void reembolsarCompra() {


    }


}
