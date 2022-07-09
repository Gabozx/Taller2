package logica;
import java.util.ArrayList;
import dominio.Producto;
import dominio.Usuario;

public class ListaProductos {

    private NodoProducto primero;
    private int cantidad;
    
    public ListaProductos() {
        primero = null;
        cantidad = 0;
    }
    
    public void insertarPrimero(Producto producto) {
        NodoProducto nodo = new NodoProducto(producto);
        nodo.setSiguiente(primero);
        primero = nodo;
        cantidad++;
        if(producto.getID()==0) {
        	producto.setID(cantidad);
        }
    }
    
    public Producto buscarProducto(int ID) {
        NodoProducto actual = primero;
        while(actual!=null && actual.getProducto().getID() != ID) {
            actual = actual.getSiguiente();
        }
        
        if(actual != null) {
            return actual.getProducto();
        }
        return null;
    }
    
    public boolean eliminarProducto(Producto producto) {
        NodoProducto actual = primero;
        NodoProducto previo = primero;
        
        while(actual != null && actual.getProducto()!=producto) {
            previo = actual;
            actual = actual.getSiguiente();
        }
        if(actual!=null) {
            if(actual == primero) {
                primero = primero.getSiguiente();
            }
            else {
                previo.setSiguiente(actual.getSiguiente());
            }
            return true;
        }
        return false;
    }
    
    public ArrayList<Producto> obtenerProductos() {
    	ArrayList<Producto> productos = new ArrayList<Producto>();
    	NodoProducto actual = primero;
    	while(actual!=null) {
    		productos.add(actual.getProducto());
    		actual = actual.getSiguiente();
    	}
    	return productos;
    }
    
    public int getCantidad() {
    	return cantidad;
    }
    
    public NodoProducto getPrimero() {
        return primero;
    }
}