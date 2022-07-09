package logica;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import dominio.*;

public interface SistemaVentasCoquimbo{

    /***
     * 
     * @param nickname
     * @param contrasena
     * @param listaUsuarios
     * @return
     */
    public boolean iniciarSesion(String nickname, String contrasena, ListaUsuarios listaUsuarios);
    /***
     * 
     * @param nickname
     * @param nombre
     * @param correo
     * @param contacto
     * @param contrasena
     * @param listaUsuarios
     * @return
     */
    public Usuario registrarUsuario(String nickname, String nombre, String correo, int contacto, String contrasena, ListaUsuarios listaUsuarios);
    /***
     * 
     * @param ID
     * @param nombre
     * @param categoria
     * @param precio
     * @param descripcion
     * @param imagen
     * @param vendedor
     * @param listaP
     */
    public void publicarProducto(String nombre, String categoria, int precio, String descripcion, ImageIcon imagen, Usuario vendedor, ListaProductos listaP);
    /***
     * 
     * @param producto
     * @param nombre
     * @param categoria
     * @param precio
     * @param descripcion
     * @param imagen
     * @param vendedor
     */
    public void editarPublicacion(Producto producto, String nombre, String categoria, int precio, String descripcion, ImageIcon imagen, Usuario vendedor);
    /***
     * 
     * @param producto
     * @param usuario
     */
    public void comprarProducto(Producto producto, Usuario usuario);

    /***
     * 
     * @param listaProductos
     * @param usuario
     * @return
     */
    public ArrayList<Producto> desplegarPublicaciones(ListaProductos listaProductos, Usuario usuario);
    /***
     * 
     * @param listaProductos
     * @return
     */
    public ArrayList<Producto> desplegarPDisponibles(ListaProductos listaProductos);
    /***
     * 
     * @param productosD
     * @param categoria
     * @return
     */
    public ArrayList<Producto> filtrarPDisponibles(ArrayList<Producto> productosD, String categoria);
    
}