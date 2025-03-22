package booksGUI;

import javax.swing.JPanel;

import org.hibernate.Session;

import libraryBook.libraryBook.HibernateUtil;
import models.Libro;
import models.Renta;
import models.Usuario;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class createRenta extends JPanel {

	private static final long serialVersionUID = 1L;

	private void cargarClientes(JComboBox<Usuario> comboBox) {
		List<Usuario> clientes = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        clientes = session.createQuery("FROM Usuario", Usuario.class).getResultList();
		    for (Usuario client : clientes) {
		    	comboBox.addItem(client);
		    }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al guardar el usuario", e);
	    }
	}
	
	private void cargarLibros(JComboBox<Libro> comboBox) {
		List<Libro> libros = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        libros = session.createQuery("FROM Libro", Libro.class).getResultList();
		    for (Libro book : libros) {
		    	comboBox.addItem(book);
		    }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al guardar el usuario", e);
	    }
	}
	
	private void guardarRenta(Renta renta) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	session.beginTransaction();
	        session.persist(renta);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al guardar el usuario", e);
	    }finally {
	    	System.out.println(renta.toString());	
	    }
	}
	
	/**
	 * Create the panel.
	 * 
	 * 
	 */
	public createRenta() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Renta");
		lblNewLabel.setBounds(279, 59, 72, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(269, 104, 57, 13);
		add(lblNewLabel_1);
		
		JComboBox<Usuario> comboBox = new JComboBox<Usuario>();
		comboBox.setBounds(217, 128, 150, 21);
		cargarClientes(comboBox);
		add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Libro");
		lblNewLabel_2.setBounds(281, 184, 45, 13);
		add(lblNewLabel_2);
		
		JComboBox<Libro> comboBox_1 = new JComboBox<Libro>();
		comboBox_1.setBounds(217, 207, 150, 21);
		cargarLibros(comboBox_1);
		add(comboBox_1);
		
		JButton btnNewButton = new JButton("Rentar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = (Usuario) comboBox.getSelectedItem();
				Libro libro = (Libro) comboBox_1.getSelectedItem();
				Renta renta = new Renta(usuario, libro);
				guardarRenta(renta);
			}
		});
		btnNewButton.setBounds(256, 256, 85, 21);
		add(btnNewButton);

	}
}
