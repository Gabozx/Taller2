package logica;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import dominio.*;

public class SistemaVentasCoquimboImpl implements SistemaVentasCoquimbo{

    public boolean iniciarSesion(String nickname, String contrasena, ListaUsuarios listaUsuarios) {
        Usuario usuario = listaUsuarios.buscarCliente(nickname);
        if(usuario!=null) {
            if(usuario.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false; 
    }
    
    public Usuario registrarUsuario(String nickname, String nombre, String correo, int contacto, String contrasena, ListaUsuarios listaUsuarios) {
        if(listaUsuarios.buscarCliente(nickname)==null) {
            Usuario usuario = new Usuario(nickname, nombre, correo, contacto, contrasena);
            listaUsuarios.insertarPrimero(usuario);
            return usuario;
        }
        return null;
    }
    
    public void publicarProducto(String nombre, String categoria, int precio, String descripcion, ImageIcon imagen, Usuario vendedor, ListaProductos listaP) {
        Producto producto = new Producto(nombre, categoria, precio, descripcion, imagen, vendedor);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String fecha = formatter.format(date);
        producto.setFecha(fecha);
        listaP.insertarPrimero(producto);
    }
    
    public void editarPublicacion(Producto producto, String nombre, String categoria, int precio, String descripcion, ImageIcon imagen, Usuario vendedor) {
    	int auxID = producto.getID();
    	producto = new Producto(nombre, categoria, precio, descripcion, imagen, vendedor);
    	Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String fecha = formatter.format(date);
        producto.setFecha(fecha);
        producto.setID(auxID);
    }
    
    public void comprarProducto(Producto producto, Usuario usuario) {
        producto.addComprador(usuario);
        usuario.setCantCompras(usuario.getCantCompras()+1);
        producto.getVendedor().setCantVentas(producto.getVendedor().getCantVentas()+1);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String fecha = formatter.format(date);
        producto.addFechaComprado(fecha);
        producto.setVendido(true);
    }
    
    public ArrayList<Producto> desplegarPublicaciones(ListaProductos listaProductos, Usuario usuario) {
    	ArrayList<Producto>  publicaciones = new ArrayList<Producto>();
    	for(int i=1; i<=listaProductos.getCantidad(); i++) {
        	if(listaProductos.buscarProducto(i).getVendedor()==usuario) {
        		publicaciones.add(listaProductos.buscarProducto(i));
        	}
        }
    	return publicaciones;
    }
    
    public ArrayList<Producto> desplegarPDisponibles(ListaProductos listaProductos) {
    	ArrayList<Producto>  prodDisponibles = new ArrayList<Producto>();
    	for(int i=1; i<=listaProductos.getCantidad(); i++) {
        	prodDisponibles.add(listaProductos.buscarProducto(i));
        }
    	return prodDisponibles;
    }
    public ArrayList<Producto> filtrarPDisponibles(ArrayList<Producto> productosD, String categoria){
    	ArrayList<Producto> filtrar = new ArrayList<Producto>();
    	for(int i=0; i<=productosD.size()-1; i++) {
        	if(productosD.get(i).getCategoria().equals(categoria)) {
        		filtrar.add(productosD.get(i));
        	}
        }
    	return filtrar;
    }
        
}