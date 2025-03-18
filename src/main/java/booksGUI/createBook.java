package booksGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.hibernate.Session;

import libraryBook.libraryBook.HibernateUtil;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Year;

import models.Autor;
import models.Libro;
import models.Libro.EstadoLibro;

public class createBook extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	
	private List<Autor> getAutors() {
		List<Autor> autores = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        autores = session.createQuery("FROM Autor", Autor.class).getResultList();
	        return autores;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al guardar el usuario", e);
	    }
	}
	
    private void cargarEstadosEnComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        for (EstadoLibro estado : EstadoLibro.values()) {
            comboBox.addItem(estado.name());
        }
    }
    
    private void createdBook(Libro libro) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	session.beginTransaction();
	        session.persist(libro);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error al guardar el usuario", e);
	    }finally {
	    	System.out.println(libro.toString());	
	    }
    }
    
	/**
	 * Create the panel.
	 */
	public createBook() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Libros");
		lblNewLabel.setBounds(286, 51, 45, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Titulo");
		lblNewLabel_1.setBounds(60, 105, 45, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ISBN");
		lblNewLabel_2.setBounds(477, 105, 45, 13);
		add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(27, 133, 177, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(376, 133, 164, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Año de Publicación");
		lblNewLabel_3.setBounds(60, 179, 144, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Estatus");
		lblNewLabel_4.setBounds(477, 179, 45, 13);
		add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(27, 202, 177, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		cargarEstadosEnComboBox(comboBox);
		comboBox.setBounds(376, 202, 164, 21);
		add(comboBox);
		
		DefaultListModel<Autor> listModel = new DefaultListModel<>();
		List<Autor> autors = getAutors();
	    for (Autor autor : autors) {
	    	listModel.addElement(autor);
	    }
		JList<Autor> list = new JList<Autor>(listModel);
		list.setBounds(40, 266, 164, 118);
		add(list);
				
		JLabel lblNewLabel_5 = new JLabel("Autores");
		lblNewLabel_5.setBounds(90, 243, 45, 13);
		add(lblNewLabel_5);
		
        JTextField txtDeseleccionar = new JTextField();
        txtDeseleccionar.setBounds(220, 300, 85, 21);
        add(txtDeseleccionar);
        txtDeseleccionar.setToolTipText("Ingrese el índice del elemento a deseleccionar");
		
        JButton btnDeseleccionar = new JButton("Deseleccionar");
        btnDeseleccionar.setBounds(315, 300, 100, 21);
        btnDeseleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = (Integer.parseInt(txtDeseleccionar.getText()) - 1);
                    if (index < 0 || index >= listModel.getSize()) {
                        JOptionPane.showMessageDialog(null, "Índice fuera de rango.");
                    } else {
                        if (list.isSelectedIndex(index)) {
                            list.removeSelectionInterval(index, index);
                        } else {
                            JOptionPane.showMessageDialog(null, "El elemento en el índice " + index + " no está seleccionado.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido para el índice.");
                }
            }
        });
        add(btnDeseleccionar);
        
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estadoSeleccionado = (String) comboBox.getSelectedItem();
				EstadoLibro estado = EstadoLibro.valueOf(estadoSeleccionado);
				Libro libro = new Libro(textField.getText(), textField_1.getText(), Year.parse(textField_2.getText()), estado, autors);
				createdBook(libro);
				getAutors();
				
			}
		});
		btnNewButton.setBounds(452, 363, 150, 21);
		add(btnNewButton);

		

	}
}
