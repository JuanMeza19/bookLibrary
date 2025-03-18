package booksGUI;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import libraryBook.libraryBook.HibernateUtil;
import models.Usuario;

import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class createClient extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public void guardarUsuario(Usuario usuario) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        session.beginTransaction();
	        session.persist(usuario);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al guardar el usuario", e);
	    }
	}
	
	/**
	 * Create the panel.
	 */
	public createClient() {
		setLayout(null);
		
		Label label = new Label("Cliente");
		label.setBounds(283, 23, 59, 21);
		add(label);
		
		textField_2 = new JTextField();
		textField_2.setBounds(248, 113, 111, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(248, 193, 111, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(283, 92, 45, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(297, 170, 45, 13);
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String nombre = textField_2.getText();
                String email = textField_3.getText();
                
                if(nombre.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        createClient.this,
                        "Todos los campos son obligatorios",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                
                try {
                    Usuario nuevoUsuario = new Usuario(nombre, email);
                    guardarUsuario(nuevoUsuario);
                    JOptionPane.showMessageDialog(
                        createClient.this,
                        "Cliente creado exitosamente!\nID: " + nuevoUsuario.getId(),
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    textField_2.setText("");
                    textField_3.setText("");
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        createClient.this,
                        "Error al crear cliente: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
			}
		});
		btnNewButton.setBounds(263, 255, 85, 21);
		add(btnNewButton);
		
	}
}
