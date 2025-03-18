package booksGUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class createRenta extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public createRenta() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Renta");
		lblNewLabel.setBounds(279, 59, 72, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(269, 104, 57, 13);
		add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(217, 128, 150, 21);
		add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Libro");
		lblNewLabel_2.setBounds(281, 184, 45, 13);
		add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(217, 207, 150, 21);
		add(comboBox_1);
		
		JButton btnNewButton = new JButton("Rentar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(256, 256, 85, 21);
		add(btnNewButton);

	}
}
