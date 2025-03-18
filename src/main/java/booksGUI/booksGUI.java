package booksGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import libraryBook.libraryBook.HibernateUtil;
import models.Usuario;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class booksGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                booksGUI frame = new booksGUI();
	                frame.setVisible(true);

	                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	                    session.beginTransaction();
	                    session.createNativeQuery("SELECT 1", Integer.class).getResultList();
	                    session.getTransaction().commit();
	                    System.out.println("¡Conexión exitosa con Hibernate!");
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }

	                // Cerrar SessionFactory solo cuando se cierre la aplicación
	                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	                    HibernateUtil.shutdown();
	                }));

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

	/**
	 * Create the frame.
	 */
	public booksGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		 contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(5, 5, 616, 22);
		contentPane.add(menuBar);
		
		//CLIENTES
		JMenu mnNewMenu = new JMenu("Clientes");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Crear");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	createClient cliente = new createClient(); 
		  
		        contentPane.removeAll();
		        contentPane.add(menuBar);
		        contentPane.add(cliente);
		        
		        contentPane.revalidate();
		        contentPane.repaint();
		    }
		});
	
		//AUTOR
		JMenu mnNewMenu_3 = new JMenu("Autor");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Crear");
		mnNewMenu_3.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        createAutor autor = new createAutor(); 
		  
		        contentPane.removeAll();
		        contentPane.add(menuBar);
		        contentPane.add(autor);
		        
		        contentPane.revalidate();
		        contentPane.repaint();
		    }
		});
		
		//LIBROS
		JMenu mnNewMenu_1 = new JMenu("Libros");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Crear");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	createBook libro = new createBook(); 
		  
		        contentPane.removeAll();
		        contentPane.add(menuBar);
		        contentPane.add(libro);
		        
		        contentPane.revalidate();
		        contentPane.repaint();
		    }
		});
		
		//RENTA
		JMenu mnNewMenu_2 = new JMenu("Renta");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Adquirir");
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		createClient cliente = new createClient();
		contentPane.add(cliente);
		
	}
}
