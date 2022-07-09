package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dominio.Producto;
import dominio.Usuario;
import logica.App;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class InfoProd extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Producto producto;
	private Usuario usuario;
	private DefaultTableModel tablaDef;
	private ArrayList<Producto> compradores;
	
	public InfoProd(Producto producto, Usuario usuario) {
		this.producto = producto;
		this.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NombreProd = new JLabel("nombre producto: "+producto.getNombre());
		NombreProd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NombreProd.setBounds(31, 11, 465, 28);
		contentPane.add(NombreProd);
		
		JLabel DisplayDesc = new JLabel("descripci\u00F3n");
		DisplayDesc.setBounds(20, 254, 71, 23);
		contentPane.add(DisplayDesc);
		
		JLabel precio = new JLabel("precio: "+producto.getPrecio());
		precio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		precio.setBounds(20, 440, 244, 23);
		contentPane.add(precio);
		
		JButton buy = new JButton("Comprar");
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App.sistema.comprarProducto(producto, usuario);
				Perfil perfil = new Perfil(usuario);
				perfil.setVisible(true);
				dispose();
			}
		});
		buy.setBounds(80, 480, 138, 31);
		contentPane.add(buy);
		
		JLabel DisplayInfoV = new JLabel("informaci\u00F3n vendedor");
		DisplayInfoV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DisplayInfoV.setBounds(309, 50, 187, 23);
		contentPane.add(DisplayInfoV);
		
		JLabel NombreV = new JLabel("nombre vendedor: "+producto.getVendedor().getNombre());
		NombreV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		NombreV.setBounds(259, 134, 372, 23);
		contentPane.add(NombreV);
		
		JLabel Contact = new JLabel("contacto: "+producto.getVendedor().getContacto());
		Contact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Contact.setBounds(259, 180, 372, 28);
		contentPane.add(Contact);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 272, 412, 217);
		contentPane.add(scrollPane);
		
		table = new JTable();
		tablaDef = new DefaultTableModel() {
			boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		};
		setModelo();
		try {
			setDatos();
		}
		catch(Exception e) {
			System.out.println();
		}
		
		table.getColumnModel().getColumn(0).setPreferredWidth(24);
		table.getColumnModel().getColumn(1).setPreferredWidth(55);
		table.getColumnModel().getColumn(2).setPreferredWidth(123);
		scrollPane.setViewportView(table);
		
		JLabel foto = new JLabel("");
        foto.setBounds(20, 50, 220, 200);
        contentPane.add(foto);
        foto.setIcon(producto.getImagen());
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(31, 288, 232, 141);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(producto.getDescripcion());
		scrollPane_1.setViewportView(textArea);
		
		JButton Back = new JButton("volver");
		Back.setBounds(571, 16, 96, 23);
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
		String[] cabecera = {"ID", "Fecha", "Comprador"};
		tablaDef.setColumnIdentifiers(cabecera);
		table.setModel(tablaDef);
	}
	
	public void setDatos() {
		Object[] datos = new Object[3];
		int i=0;
	
		for(Usuario usuario : producto.getCompradores()) {
			datos[0] = producto.getID();
			datos[1] = producto.getFechaComprado().get(i);
			datos[2] = producto.getCompradores().get(i).getNombre();
			i++;
			tablaDef.addRow(datos);
		}
		table.setModel(tablaDef);
	}
}