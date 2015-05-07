package shoppinglist.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class EditGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textFieldNewQuantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditGUI frame = new EditGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditGUI() {
		setResizable(false);
		setTitle("Edit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(138, 10, 68, 20);
		contentPane.add(lblCategory);
		
		JComboBox comboBoxCategory = new JComboBox();
		comboBoxCategory.setBounds(216, 10, 95, 20);
		contentPane.add(comboBoxCategory);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 209, 189);
		contentPane.add(scrollPane);
		
		JTextArea textAreaSelectedCategory = new JTextArea();
		scrollPane.setViewportView(textAreaSelectedCategory);
		
		JLabel lblSelectedCategory = new JLabel("Selected category");
		lblSelectedCategory.setBounds(65, 41, 87, 20);
		contentPane.add(lblSelectedCategory);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(243, 74, 68, 20);
		contentPane.add(lblItem);
		
		textField = new JTextField();
		textField.setBounds(324, 74, 131, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(236, 204, 113, 23);
		contentPane.add(btnDelete);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(300, 238, 113, 23);
		contentPane.add(btnQuit);
		
		JLabel lblNewQuantity = new JLabel("New quantity");
		lblNewQuantity.setBounds(243, 147, 78, 23);
		contentPane.add(lblNewQuantity);
		
		JCheckBox chckbxChangeQuantity = new JCheckBox("Change quantity");
		chckbxChangeQuantity.setBounds(243, 117, 131, 23);
		contentPane.add(chckbxChangeQuantity);
		
		textFieldNewQuantity = new JTextField();
		textFieldNewQuantity.setEditable(false);
		textFieldNewQuantity.setBounds(324, 148, 131, 20);
		contentPane.add(textFieldNewQuantity);
		textFieldNewQuantity.setColumns(10);
		
		JButton btnChangeQuantity = new JButton("Change quantity");
		btnChangeQuantity.setBounds(359, 204, 113, 23);
		contentPane.add(btnChangeQuantity);
	}
}
