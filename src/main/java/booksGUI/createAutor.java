package booksGUI;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Session;

import libraryBook.libraryBook.HibernateUtil;
import models.Autor;
import models.Usuario;

public class createAutor extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	private void guardarAutor(Autor autor) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        session.beginTransaction();
	        session.persist(autor);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al guardar el usuario", e);
	    }
	}
	
	/**
	 * Create the panel.
	 */
	public createAutor() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Autor");
		lblNewLabel.setBounds(278, 64, 34, 21);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(278, 123, 45, 13);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(251, 146, 96, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nacionalidad");
		lblNewLabel_2.setBounds(265, 196, 69, 13);
		add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(251, 219, 96, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String nombre = textField.getText();
                String nacionalidad = textField_1.getText();
                
                if(nombre.isEmpty() || nacionalidad.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        createAutor.this,
                        "Todos los campos son obligatorios",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                
                try {
                    Autor nuevoAutor = new Autor(nombre, nacionalidad);
                    guardarAutor(nuevoAutor);
                    JOptionPane.showMessageDialog(
                        createAutor.this,
                        "Autor creado exitosamente!\nID: " + nuevoAutor.getId(),
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    textField.setText("");
                    textField_1.setText("");
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        createAutor.this,
                        "Error al crear autor: " + ex.getMessage(),
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
