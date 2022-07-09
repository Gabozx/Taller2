package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JSpinner;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import logica.*;
import dominio.*;
import java.util.*;

public class Perfil extends JFrame {
	
	private JPanel panel;
	private JTable table;
	private DefaultTableModel tabla;
	private int seleccionar;
	private Usuario usuario;
	private ArrayList<Producto> productosD = App.sistema.desplegarPDisponibles(App.listaProductos);

	public Perfil(Usuario usuario){
		this.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 500);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 82, 509, 251);
		panel.add(scrollPane);  
		
		tablas(scrollPane);
		setDatoss();
		
		JLabel bienvenida = new JLabel("Bienvenido "+usuario.getNickname());
		bienvenida.setBounds(55, 21, 250, 14);
		panel.add(bienvenida);
		
		JLabel listaprod = new JLabel("Lista de productos disponibles");
		listaprod.setBounds(82, 46, 200, 14);
		panel.add(listaprod);
		
		JButton Bvender = new JButton("Vender Producto");
		Bvender.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Bvender.setBounds(55, 358, 137, 25);
		panel.add(Bvender);
		Bvender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PublicarProd pub = new PublicarProd(usuario);
				pub.setVisible(true);
				dispose();
			}
		});
		
		JButton Bpubli = new JButton("Mis Publicaciones");
		Bpubli.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Bpubli.setBounds(55, 394, 137, 25);
		panel.add(Bpubli);
		Bpubli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Publicaciones mispub = new Publicaciones(usuario);
				mispub.setVisible(true);
				dispose();
			}
		});
		
		JButton Binfo = new JButton("Informaci\u00F3n Producto");
		Binfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Binfo.setBounds(240, 358, 160, 25);
		panel.add(Binfo);
		Binfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccionar = table.getSelectedRow();
				Producto producto = App.listaProductos.buscarProducto(Integer.parseInt(String.valueOf(table.getValueAt(seleccionar, 0))));
				InfoProd infoprod = new InfoProd(producto,usuario);
				infoprod.setVisible(true);
				dispose();
			}
		});
		
		JButton Bcompras = new JButton("Mis Compras");
		Bcompras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Bcompras.setBounds(258, 396, 127, 25);
		panel.add(Bcompras);
		Bcompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoCompra compras = new InfoCompra(usuario);
				compras.setVisible(true);
				dispose();
			}
		});
		
		JComboBox box = new JComboBox();
		box.setBounds(468, 358, 126, 24);
		box.addItem("Juegos");
		box.addItem("Hogar");
		box.addItem("Electrodoméstico");
		box.addItem("etc");
		panel.add(box);	
		
		JButton Bfiltrar = new JButton("Filtrar");
		Bfiltrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Bfiltrar.setBounds(468, 396, 127, 25);
		panel.add(Bfiltrar);
		Bfiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] categorias= new String[4];
				categorias[0]="Juegos";categorias[1]="Hogar";categorias[2]="Electrodoméstico";categorias[3]="etc";
				String categoria=categorias[(box.getSelectedIndex())];
				ArrayList<Producto> filtrados = App.sistema.filtrarPDisponibles(productosD, categoria);
				Object[] datos = new Object[5];
				tablas(scrollPane);
		        for(Producto producto : filtrados) {
		            if(producto.isDisponible()) {
		                datos[0] = producto.getID();
		                datos[1] = producto.getNombre();
		                datos[2] = producto.getCategoria();
		                datos[3] = producto.getFecha();
		                datos[4] = producto.isVendido();
		                if(producto.isVendido()) {
		                    datos[4] = "Si";
		                }else {
		                    datos[4] = "No";
		                }
		                tabla.addRow(datos);
		            }
		        }
		        table.setModel(tabla);
			}
		});
		
		JButton cerrar = new JButton("Cerrar Sesi\u00F3n");
		cerrar.setBounds(446, 17, 160, 25);
		panel.add(cerrar);
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		});
	}
	
	public void setModelo() {
		String[] cabecera = {"ID", "Nombre", "Categor\u00EDa", "Fecha", "Visto"};
		tabla.setColumnIdentifiers(cabecera);
		table.setModel(tabla);
	}
	
	
	public void setDatoss() {
        Object[] datos = new Object[5];
        for(Producto producto : productosD) {
            if(producto.isDisponible()) {
                datos[0] = producto.getID();
                datos[1] = producto.getNombre();
                datos[2] = producto.getCategoria();
                datos[3] = producto.getFecha();
                if(producto.isVendido()) {
                    datos[4] = "Si";
                }else {
                    datos[4] = "No";
                }
                
                tabla.addRow(datos);
            }
        }
	}
	
	public void tablas(JScrollPane scrollPane) {
		table = new JTable();
		scrollPane.setViewportView(table);
		tabla = new DefaultTableModel() {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		};
		setModelo();
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}