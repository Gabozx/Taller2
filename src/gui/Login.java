package gui;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;

import logica.*;
import dominio.*;
import dominio.*;
public class Login extends JFrame {

	private JPanel panel;
	private JTextField user;
	private Perfil perfil;
	private JPasswordField pass;
	private Usuario usuario;
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 330);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		user = new JTextField();
		user.setBounds(179, 76, 133, 23);
		panel.add(user);
		user.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Inicio Sesi\u00F3n");
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(100, 22, 142, 20);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(22, 74, 113, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(35, 129, 113, 21);
		panel.add(lblNewLabel_1_1);
		
		pass = new JPasswordField();
		pass.setEchoChar('*');
		pass.setBounds(179, 131, 133, 22);
		panel.add(pass);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro registrar = new Registro();
				registrar.setVisible(true);
				dispose();
				}
			});
		btnRegistrarse.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRegistrarse.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnRegistrarse.setBounds(20, 225, 115, 25);
		panel.add(btnRegistrarse);
		
		JButton btnIniciarSesin = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Desencriptar contraseña para validarla
				String password = "";
				char[] Pass = pass.getPassword();
				for(int i=0; i<Pass.length; i++) {
					password += Pass[i];
				}
				//Comprobar que los campos no estén vacíos
				if(!user.getText().isEmpty() && !password.equals("")) {
					//Comprobar que la contraseña sea correcta
					if(App.sistema.iniciarSesion(user.getText(), password, App.listaUsuarios)) {
						usuario = App.listaUsuarios.buscarCliente(user.getText());
						App.usuario = usuario;
						JOptionPane.showMessageDialog(panel, "Sesión iniciada correctamente.");
						if(perfil!=null) {
							perfil.setVisible(true);
							invisible(e);
						}else {
							perfil = new Perfil(usuario);
							perfil.setVisible(true);
							invisible(e);
						}
					}
					else {
						JOptionPane.showMessageDialog(panel, "Usuario y/o contraseña incorrecta.");
					}
				}
				else {
					JOptionPane.showMessageDialog(panel, "Ingrese usuario y contraseña.");
				}
			}	
		});
		btnIniciarSesin.setVerticalAlignment(SwingConstants.BOTTOM);
		btnIniciarSesin.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnIniciarSesin.setBounds(186, 225, 137, 25);
		panel.add(btnIniciarSesin);
	}

	public void invisible(ActionEvent e) {
		this.setVisible(false);
	}

}
