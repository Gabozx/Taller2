package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import dominio.Usuario;
import logica.*;

public class Registro extends JFrame {

	private JPanel panel;
	private JTextField nombre;
	private JTextField correo;
	private JPasswordField pass;
	private JPasswordField passC;
	private JTextField contacto;
	private JTextField nombreCom;
	private Perfil perfil;

	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 370);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel Dnombre = new JLabel("Nombre de Usuario");
		Dnombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Dnombre.setBounds(37, 30, 129, 25);
		panel.add(Dnombre);
		
		JLabel Dcorreo = new JLabel("Correo");
		Dcorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Dcorreo.setBounds(37, 102, 129, 25);
		panel.add(Dcorreo);
		
		nombre = new JTextField();
		nombre.setBounds(34, 55, 132, 25);
		panel.add(nombre);
		
		correo = new JTextField();
		correo.setBounds(34, 127, 132, 25);
		panel.add(correo);
		
		JLabel DContra = new JLabel("Contrase\u00F1a");
		DContra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DContra.setBounds(37, 173, 129, 25);
		panel.add(DContra);
		
		pass = new JPasswordField();
		pass.setEchoChar('*');
		pass.setBounds(34, 199, 132, 25);
		panel.add(pass);
		
		JLabel Dconfirmar = new JLabel("Confirmar Contrase\u00F1a");
		Dconfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Dconfirmar.setBounds(222, 173, 147, 25);
		panel.add(Dconfirmar);
		
		passC = new JPasswordField();
		passC.setEchoChar('*');
		passC.setBounds(222, 199, 132, 25);
		panel.add(passC);
		
		contacto = new JTextField();
		contacto.setBounds(222, 127, 132, 25);
		panel.add(contacto);
		
		JLabel Dcontacto = new JLabel("Contacto");
		Dcontacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Dcontacto.setBounds(222, 102, 129, 25);
		panel.add(Dcontacto);
		
		JLabel Dfullname = new JLabel("Nombre Completo");
		Dfullname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Dfullname.setBounds(222, 30, 129, 25);
		panel.add(Dfullname);
		
		nombreCom = new JTextField();
		nombreCom.setBounds(222, 55, 132, 25);
		panel.add(nombreCom);
		
		JButton Bregister = new JButton("Registrarse");
		Bregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = "";
				String passwordC = "";
				char[] passw = pass.getPassword();
				char[] passwC = passC.getPassword();
				for (int i=0; i<passw.length;i++) {
					password += passw[i];
				}
				for(int j=0; j<passwC.length; j++) {
					passwordC += passwC[j];
				}
				if(!nombre.getText().isEmpty() && !correo.getText().isEmpty() && !contacto.getText().isEmpty() && !nombreCom.getText().isEmpty() && !password.equals("") && !passwordC.equals("")) {
					if(password.equals(passwordC)) {
						if(correo.getText().contains("@")) {
							try {
								Usuario usuario = App.sistema.registrarUsuario(nombre.getText(), nombreCom.getText(), correo.getText(), Integer.parseInt(contacto.getText()), password, App.listaUsuarios);
								App.guardarUsuario("Usuarios.txt", App.listaUsuarios);
								if(perfil!=null) {
									perfil.setVisible(true);
									dispose();
								}else {
									Perfil perfil = new Perfil(usuario);
									perfil.setVisible(true);
									dispose();
								}
							}
							catch(NumberFormatException ex) {
								JOptionPane.showMessageDialog(panel, "Ingrese un número de contacto válido.");
							}
						}
						else {
							JOptionPane.showMessageDialog(panel, "Ingrese un correo válido.");
						}
					}
					else {
						JOptionPane.showMessageDialog(panel, "Contraseñas no coinciden.");
					}
				}
				else {
					JOptionPane.showMessageDialog(panel, "Debe rellenar todos los campos.");
				}
			}
		});
		Bregister.setFont(new Font("Consolas", Font.PLAIN, 13));
		Bregister.setBounds(139, 265, 121, 23);
		panel.add(Bregister);
	}
	
}