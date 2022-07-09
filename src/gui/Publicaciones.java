package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dominio.Producto;
import dominio.Usuario;
import logica.*;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Publicaciones extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tabla;
	private Usuario usuario;
	private ArrayList<Producto> productosD = App.sistema.desplegarPDisponibles(App.listaProductos);

	public Publicaciones(Usuario usuario) {
		this.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 86, 421, 207);
		contentPane.add(scrollPane);
		tablas(scrollPane);
		try {
			setDatos();
		}
		catch(Exception e) {
			System.out.println();
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(76);
		table.getColumnModel().getColumn(3).setPreferredWidth(44);
		table.getColumnModel().getColumn(4).setPreferredWidth(44);
		scrollPane.setViewportView(table);
		
		JButton Beditar = new JButton("Editar Publicaci\u00F3n");
		Beditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccionar = table.getSelectedRow();
				InfoProd prod = new InfoProd(App.listaProductos.buscarProducto(Integer.parseInt(String.valueOf(table.getValueAt(seleccionar, 0)))),usuario);
				prod.setVisible(true);
				dispose();
			}
		});
		
		Beditar.setBounds(62, 304, 131, 23);
		contentPane.add(Beditar);
		
		JButton Bborrar = new JButton("Borrar Producto");
		Bborrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int seleccionar = table.getSelectedRow();
					App.listaProductos.buscarProducto(Integer.parseInt(String.valueOf(table.getValueAt(seleccionar, 0)))).setDisponible(false);
					tablas(scrollPane);
					try {
						setDatos();
					}
					catch(Exception ex) {
						System.out.println();
					}
				}
				catch(Exception er) {
					System.out.println();
				}
			}
		});
		Bborrar.setBounds(352, 326, 131, 23);
		contentPane.add(Bborrar);
		
		JLabel DisplayInfo = new JLabel("Informacion Personal");
		DisplayInfo.setBounds(62, 30, 131, 14);
		contentPane.add(DisplayInfo);
		
		JLabel DisplayPubli = new JLabel("Publicaciones");
		DisplayPubli.setBounds(231, 61, 93, 14);
		contentPane.add(DisplayPubli);
		
		JLabel nombre = new JLabel(usuario.getNombre());
		nombre.setBounds(372, 30, 111, 14);
		contentPane.add(nombre);
		
		JLabel cantVentas = new JLabel("Cantidad de ventas: "+App.usuario.getCantVentas());
		cantVentas.setBounds(197, 376, 253, 14);
		contentPane.add(cantVentas);
		
		JButton Back = new JButton("volver");
		Back.setBounds(62, 338, 131, 23);
		contentPane.add(Back);
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Perfil perf = new Perfil(usuario);
				perf.setVisible(true);
				dispose();
			}
		});
	}
	
	public void setModelo() {
		String[] cabecera = {"ID", "Nombre", "Categor\u00EDa", "Fecha", "Vendidas"};
		tabla.setColumnIdentifiers(cabecera);
		table.setModel(tabla);
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
	
	public void setDatos() {
        Object[] datos = new Object[5];
        try {
            for(Producto producto : productosD) {
                if(producto.getVendedor()==usuario && producto.isDisponible()) {
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
            table.setModel(tabla);
        }catch(Exception er) {
            System.out.println("Lista vacía");
        }
    }
	
}