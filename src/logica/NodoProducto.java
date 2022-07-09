package logica;
import dominio.Producto;

public class NodoProducto {

    private Producto producto;
    private NodoProducto siguiente;
    
    public NodoProducto(Producto producto) {
        this.producto = producto;
        siguiente = null;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public NodoProducto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoProducto siguiente) {
        this.siguiente = siguiente;
    }
}