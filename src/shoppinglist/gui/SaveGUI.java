/*
 * 
 */
package shoppinglist.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The Class SaveGUI.
 */
@SuppressWarnings("serial")
public class SaveGUI extends JFrame {

	/** The content pane. */
	static JPanel contentPane;
	
	/** The text field list name. */
	static JTextField textFieldListName;

	/**
	 * Create the frame.
	 */
	public SaveGUI() {
		setTitle("Save");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 254, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(MainWindowGUI.contentPane);

		JLabel lblListName = new JLabel("List name");
		lblListName.setBounds(90, 23, 63, 14);
		contentPane.add(lblListName);

		textFieldListName = new JTextField();
		textFieldListName.setBounds(51, 48, 132, 20);
		contentPane.add(textFieldListName);
		textFieldListName.setColumns(10);

		JButton btnSaveList = new JButton("Save list");
		btnSaveList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIControler.saveList(textFieldListName.getText());
				
				dispose();
			}
		});
		btnSaveList.setBounds(74, 89, 89, 23);
		contentPane.add(btnSaveList);
	}

}
