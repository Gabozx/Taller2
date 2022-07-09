package logica;
import java.util.ArrayList;
import dominio.Usuario;

public class ListaUsuarios {

    private NodoUsuario primero;
    
    public ListaUsuarios() {
        primero = null;
    }

    public void insertarPrimero(Usuario usuario) {
        NodoUsuario nodo = new NodoUsuario(usuario);
        nodo.setSiguiente(primero);
        primero = nodo;
    }
    
    public Usuario buscarCliente(String nickname) {
        NodoUsuario actual = primero;
        while(actual!=null && !actual.getUsuario().getNickname().equals(nickname)) {
            actual = actual.getSiguiente();
        }
        if(actual != null) {
            return actual.getUsuario();
        }
        return null;
    }
    
    public ArrayList<Usuario> obtenerUsuarios() {
    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    	NodoUsuario actual = primero;
    	while(actual!=null) {
    		usuarios.add(actual.getUsuario());
    		actual = actual.getSiguiente();
    	}
    	return usuarios;
    }
    
    public NodoUsuario getPrimero() {
        return primero;
    }
    
}