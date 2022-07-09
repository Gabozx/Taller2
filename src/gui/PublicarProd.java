package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dominio.Producto;
import dominio.Usuario;
import logica.App;
import logica.ListaProductos;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PublicarProd extends JFrame {

	private JPanel contentPane;
	private JTextField precio;
	private JTextField nombre;
	private File imagen;
	private BufferedImage picture;
	private Usuario usuario;

	public PublicarProd(Usuario usuario) {
		this.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Ddescripcion = new JLabel("Descripci\u00F3n ");
		Ddescripcion.setBounds(45, 232, 98, 20);
		contentPane.add(Ddescripcion);		
		
		JLabel Dprecio = new JLabel("Precio");
		Dprecio.setBounds(45, 162, 98, 14);
		contentPane.add(Dprecio);
		
		JLabel Dnombre = new JLabel("Nombre");
		Dnombre.setBounds(45, 80, 98, 14);
		contentPane.add(Dnombre);
		
		JLabel Dcategoria = new JLabel("Categor\u00EDa");
		Dcategoria.setBounds(197, 80, 98, 14);
		contentPane.add(Dcategoria);		
		
		JLabel resolucionr = new JLabel("resoluci\u00F3n recomendada");
		resolucionr.setBounds(356, 37, 139, 14);
		contentPane.add(resolucionr);		
		
		JLabel resolucionrr = new JLabel("220 x 200");
		resolucionrr.setBounds(395, 62, 71, 14);
		contentPane.add(resolucionrr);		
		
		JLabel foto = new JLabel("");
		foto.setBounds(304, 96, 220, 200);
		contentPane.add(foto);		

		precio = new JTextField();
		precio.setBounds(45, 187, 98, 25);
		contentPane.add(precio);
		
		nombre = new JTextField();
		nombre.setBounds(45, 103, 98, 25);
		contentPane.add(nombre);
				
		JComboBox box = new JComboBox();
		box.setBounds(185, 103, 88, 24);		
		box.addItem("Juegos");
		box.addItem("Hogar");
		box.addItem("Electrodoméstico");
		box.addItem("etc");
		contentPane.add(box);
		
		JButton Bsubir = new JButton("Subir Foto");
		Bsubir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Bsubir.setBounds(321, 353, 145, 25);
		contentPane.add(Bsubir);
		Bsubir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            JFileChooser fileChooser = new JFileChooser();
	            FileNameExtensionFilter filter1 = new FileNameExtensionFilter(".png","png");
	            FileNameExtensionFilter filter2 = new FileNameExtensionFilter(".jpg","jpg");
	            FileNameExtensionFilter filter3 = new FileNameExtensionFilter(".jpeg","jpeg");
	            fileChooser.setFileFilter(filter1);fileChooser.setFileFilter(filter2);fileChooser.setFileFilter(filter3);
	            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	            int accede = fileChooser.showOpenDialog(getParent());
	            if (accede == JFileChooser.APPROVE_OPTION) {
	                try {
	                    imagen = fileChooser.getSelectedFile();
	                    picture = ImageIO.read(imagen);
	                    foto.setIcon(new ImageIcon(picture));
	                    getContentPane().add(foto);
	                } catch (IOException ie) {
	                    ie.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "ERROR");
	                }
	            }
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 253, 239, 87);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton Bregistro = new JButton("Registrar / editar ");
		Bregistro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Bregistro.setBounds(45, 353, 145, 25);
		contentPane.add(Bregistro);
		Bregistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!nombre.getText().equals("") && !precio.getText().equals("")) {
                    try {
                        String[] categorias= new String[4];
                        categorias[0]="Juegos";categorias[1]="Hogar";categorias[2]="Electrodoméstico";categorias[3]="etc";
                        String categoria=categorias[(box.getSelectedIndex())];
                        ImageIcon image = new ImageIcon(picture);
                        App.sistema.publicarProducto(nombre.getText(),categoria,Integer.parseInt(precio.getText()),textArea.getText(),image,usuario, App.listaProductos);
                        Perfil perf = new Perfil(usuario);
                        perf.setVisible(true);
                        dispose();
                    }
                    catch(NullPointerException er) {
                        JOptionPane.showMessageDialog(contentPane, "Debe agregar una foto del producto.");
                    }
                    catch(NumberFormatException ex) {
								JOptionPane.showMessageDialog(contentPane, "Ingrese un número de contacto válido.");
                    }
                    
                }else {
                    JOptionPane.showMessageDialog(contentPane, "Debe rellenar todos los campos.");
                }
            }
        });
		
		JButton Back = new JButton("volver");
		Back.setBounds(10, 11, 111, 23);
		contentPane.add(Back);
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Perfil perf = new Perfil(usuario);
				perf.setVisible(true);
				dispose();
			}
		});
	}

}