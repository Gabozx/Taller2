package dominio;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Producto {
	private int ID;
	private String nombre;
	private String categoria;
	private int precio;
	private String descripcion;
	private ImageIcon imagen;
	private String fecha;
	private ArrayList<String> fechaComprado;
	private boolean vendido;
	private ArrayList<Usuario> compradores;
    private Usuario vendedor;
    private boolean disponible;
    
    public Producto(String nombre, String categoria, int precio, String descripcion, ImageIcon imagen, Usuario vendedor) {
    	compradores = new ArrayList<>();
    	fechaComprado = new ArrayList<>();
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.vendedor = vendedor;
        ID = 0;
        vendido = false;
        disponible = true;
    }

    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
       return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public ImageIcon getImagen() {
		return imagen;
	}
	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public ArrayList<String> getFechaComprado() {
		return fechaComprado;
	}
	public void addFechaComprado(String fecha) {
		fechaComprado.add(fecha);
	}
	public boolean isVendido() {
        return vendido;
    }
    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }
    public ArrayList<Usuario> getCompradores() {
        return compradores;
    }
    public void setCompradores(ArrayList<Usuario> compradores) {
    	this.compradores = compradores;
    }
    public void addComprador(Usuario comprador) {
        compradores.add(comprador);
    }
    public Usuario getVendedor() {
        return vendedor;
    }
    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }
    public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public void setFechaComprado(ArrayList<String> fechaComprado) {
		this.fechaComprado = fechaComprado;
	}
	public String toString() {
        return ID + ", " + nombre + "," + categoria + "," + precio
                + "," + descripcion + "," + imagen + "," + fecha + "," + vendido + "," + compradores + ", "
                + vendedor;
    }
	
}