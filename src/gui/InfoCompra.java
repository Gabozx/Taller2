package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dominio.Producto;
import dominio.Usuario;
import logica.App;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import logica.*;

public class InfoCompra extends JFrame {

	public String name;
	private JPanel contentPane;
	private JTable table;
	private Usuario usuario;
	private DefaultTableModel tablaDef;
	private ArrayList<Producto> productosD = App.sistema.desplegarPDisponibles(App.listaProductos);

	public InfoCompra(Usuario usuario) {
		this.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 48, 482, 274);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		tablaDef = new DefaultTableModel() {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		};
		setModelo();
		setDatos();
		
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(3).setPreferredWidth(109);
		
		JLabel ListaC = new JLabel("Lista de compras");
		ListaC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ListaC.setBounds(43, 11, 121, 26);
		contentPane.add(ListaC);
		
		JLabel cantCompras = new JLabel("Cantidad compras: "+App.usuario.getCantCompras());
		cantCompras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cantCompras.setBounds(348, 11, 165, 26);
		contentPane.add(cantCompras);
		
		JButton Back = new JButton("volver");
		Back.setBounds(235, 352, 89, 23);
		contentPane.add(Back);
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Perfil perf = new Perfil(App.usuario);
				perf.setVisible(true);
				dispose();
			}
		});
	}
	
	public void setModelo() {
		String[] cabecera = {"ID", "Usuario", "Nombre Producto", "Precio"};
		tablaDef.setColumnIdentifiers(cabecera);
		table.setModel(tablaDef);
	}
	
	public void setDatos() {
		Object[] datos = new Object[4];
		try {
			for(Producto producto : productosD) {
				int i=0;
				for(Usuario usuario : producto.getCompradores()) {
					if(producto.getCompradores().get(i)==App.usuario) {
						datos[0] = producto.getID();
						datos[1] = producto.getVendedor().getNombre();
						datos[2] = producto.getNombre();
						datos[3] = producto.getPrecio();
						tablaDef.addRow(datos);
					}
					i++;
				}
			}
			table.setModel(tablaDef);
		}catch(Exception er) {
			System.out.println("");
		}
	}
	
}