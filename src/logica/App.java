package logica;
import dominio.*;
import java.io.*;
import java.util.ArrayList;
import gui.*;

public class App {

	public static ListaUsuarios listaUsuarios;
	public static Usuario usuario;
	public static ListaProductos listaProductos;
	public static SistemaVentasCoquimbo sistema= new SistemaVentasCoquimboImpl();
	
	public static void main(String[] args) throws IOException {
		listaUsuarios = new ListaUsuarios();
		listaProductos = new ListaProductos();
		leerUsuarios(listaUsuarios);
		Login log= new Login();
		log.setVisible(true);
	}
	
	public static void leerUsuarios(ListaUsuarios listaUsuarios) throws IOException{
		try {
			BufferedReader arch = new BufferedReader(new FileReader("Usuarios.txt"));
			String linea;
			while((linea=arch.readLine())!=null) {
				String[] partes = linea.split(",");
				Usuario usuario = new Usuario(partes[0],partes[1],partes[2],Integer.parseInt(partes[3]), partes[4]);
				listaUsuarios.insertarPrimero(usuario);
			}
			arch.close();
		}catch(FileNotFoundException e) {
			BufferedWriter arch = new BufferedWriter(new FileWriter("Usuarios.txt"));
		}catch(NullPointerException ex) {
			System.out.println();
		}
	}
	
	public static void guardarUsuario(String archivo, ListaUsuarios listaUsuarios) {
		try {
			BufferedWriter arch = new BufferedWriter(new FileWriter(archivo));
			ArrayList<Usuario> usuarios = listaUsuarios.obtenerUsuarios();
			String linea;
			for(int i=0; i<usuarios.size(); i++) {
				if(i==usuarios.size()-1) {
					linea = usuarios.get(i).toString();
				}else {
					linea = usuarios.get(i).toString() + "\n" ;
				}
				arch.write(linea);
			}
			arch.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println();
			
		}
		catch(IOException e) {
			System.out.println();
		}
		catch(NullPointerException er) {
			System.out.println();
		}
	}	
	
}