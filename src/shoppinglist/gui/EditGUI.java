/*
 * 
 */
package shoppinglist.gui;

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
import javax.swing.DefaultComboBoxModel;

import shoppinglist.Item;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

/**
 * Represents a public class that edits shopping list.
 * @author Sanja Zelenovic, Milena Djurdjic, Nevena Djuricic
 * @version 1.0
 */
public class EditGUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	static JPanel contentPane;
	
	/** The text field item. */
	static JTextField textFieldItem;
	
	/** The text field new quantity. */
	static JTextField textFieldNewQuantity;
	
	/** The combo box category. */
	static JComboBox<String> comboBoxCategory = new JComboBox<String>();
	
	/** The text area selected category. */
	static JTextArea textAreaSelectedCategory = new JTextArea();
	
	/** The Constant btnDelete. */
	final static JButton btnDelete = new JButton("Delete");
	
	/**
	 * List of selected category.
	 */
	static LinkedList<Item> selectedCategory = new LinkedList<Item>();
	
	/**
	 * Gets the opt.
	 *
	 * @return the opt
	 */
	public static int getOpt() {
		return opt;
	}

	/**
	 * Sets the opt.
	 *
	 * @param option the new opt
	 */
	public static void setOpt(int option) {
		opt = option;
	}

	/**
	 * Checks if is found.
	 *
	 * @return true, if is found
	 */
	public static boolean isFound() {
		return found;
	}

	/**
	 * Sets the found.
	 *
	 * @param f the new found
	 */
	public static void setFound(boolean f) {
		found = f;
	}

	/** Additional String made of selected category items. */
	private static String selectedCat = "";
	
	/**
	 * Gets the selected cat.
	 *
	 * @return the selected cat
	 */
	public static String getSelectedCat() {
		return selectedCat;
	}

	/**
	 * Sets the selected cat.
	 *
	 * @param selectedCat the new selected cat
	 */
	public static void setSelectedCat(String selectedCat) {
		EditGUI.selectedCat = selectedCat;
	}

	/**
	 * Checks if is all deleted.
	 *
	 * @return true, if is all deleted
	 */
	public static boolean isAllDeleted() {
		return allDeleted;
	}

	/**
	 * Sets the all deleted.
	 *
	 * @param ad the new all deleted
	 */
	public static void setAllDeleted(boolean ad) {
		allDeleted = ad;
	}

	/** Additional String made of selected category items. */
	private static String selectedCatS = "";
	
	/**
	 * Gets the selected cat s.
	 *
	 * @return the selected cat s
	 */
	public static String getSelectedCatS() {
		return selectedCatS;
	}

	/**
	 * Sets the selected cat s.
	 *
	 * @param selectedCatS the new selected cat s
	 */
	public static void setSelectedCatS(String selectedCatS) {
		EditGUI.selectedCatS = selectedCatS;
	}

	/**Additional parameter.*/
	static int opt = 0;

	/**Shows us if item is found in selected category.*/
	static boolean found = false;

	/**Shows us if all items are deleted from selected category.*/
	static boolean allDeleted = false;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditGUI() {
		setResizable(false);
		setTitle("Edit");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 505, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(138, 10, 68, 20);
		contentPane.add(lblCategory);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 209, 189);
		contentPane.add(scrollPane);

		textAreaSelectedCategory.setEditable(false);
		scrollPane.setViewportView(textAreaSelectedCategory);

		JLabel lblSelectedCategory = new JLabel("Selected category");
		lblSelectedCategory.setBounds(65, 41, 113, 20);
		contentPane.add(lblSelectedCategory);

		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(243, 74, 68, 20);
		contentPane.add(lblItem);

		textFieldItem = new JTextField();
		textFieldItem.setEditable(false);
		textFieldItem.setBounds(324, 74, 131, 20);
		contentPane.add(textFieldItem);
		textFieldItem.setColumns(10);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				dispose();
			}
		});
		btnQuit.setBounds(303, 238, 113, 23);
		contentPane.add(btnQuit);

		JLabel lblNewQuantity = new JLabel("New quantity");
		lblNewQuantity.setBounds(243, 147, 78, 23);
		contentPane.add(lblNewQuantity);

		textFieldNewQuantity = new JTextField();
		textFieldNewQuantity.setEditable(false);
		textFieldNewQuantity.setBounds(324, 148, 131, 20);
		contentPane.add(textFieldNewQuantity);
		textFieldNewQuantity.setColumns(10);

		comboBoxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.selectCategory();
			}
		});
		comboBoxCategory.setModel(new DefaultComboBoxModel(new String[] {"- select category -", "Food", "Drinks", "Hygiene"}));
		comboBoxCategory.setBounds(216, 10, 158, 20);
		contentPane.add(comboBoxCategory);

		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.deleteItem();
			}
		});
		btnDelete.setBounds(229, 204, 128, 23);
		contentPane.add(btnDelete);

		final JButton btnChangeQuantity = new JButton("Change quantity");
		btnChangeQuantity.setEnabled(false);
		btnChangeQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				GUIControler.changeQuantity();
			}
		});
		btnChangeQuantity.setBounds(359, 204, 130, 23);
		contentPane.add(btnChangeQuantity);

		final JCheckBox chckbxChangeQuantity = new JCheckBox("Change quantity");
		chckbxChangeQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				if(chckbxChangeQuantity.isSelected()) {
					textFieldNewQuantity.setEditable(true);
					btnDelete.setEnabled(false);
					btnChangeQuantity.setEnabled(true);
				} else {
					textFieldNewQuantity.setText("");
					textFieldNewQuantity.setEditable(false);
					btnDelete.setEnabled(true);
					btnChangeQuantity.setEnabled(false);
				}
			}
		});
		chckbxChangeQuantity.setBounds(243, 117, 131, 23);
		contentPane.add(chckbxChangeQuantity);
		
		JButton btnRefreshList = new JButton("Refresh list");
		btnRefreshList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				if(!selectedCategory.isEmpty())
					GUIControler.selectCategory();
				
				MainWindowGUI.textAreaShoppingList.setText(MainWindowGUI.getFoodS() + MainWindowGUI.getDrinksS() + 
						MainWindowGUI.getHygieneS());
			}
		});
		btnRefreshList.setBounds(65, 259, 113, 23);
		contentPane.add(btnRefreshList);

	}

	/**
	 * Return selected category.
	 *
	 * @return the linked list
	 */
	public static LinkedList<Item> returnSelectedCategory() {
		return selectedCategory;
	}
	
}
