package shoppinglist.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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

public class EditGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldItem;
	private JTextField textFieldNewQuantity;
	private LinkedList<Item> selectedCategory = new LinkedList<Item>();
	private String selectedCat = "";

	int opt = 0;
	boolean found = false;
	boolean allDeleted = false;

	/**
	 * Create the frame.
	 */
	public EditGUI() {
		setResizable(false);
		setTitle("Edit");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 488, 300);
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

		final JTextArea textAreaSelectedCategory = new JTextArea();
		textAreaSelectedCategory.setEditable(false);
		scrollPane.setViewportView(textAreaSelectedCategory);

		JLabel lblSelectedCategory = new JLabel("Selected category");
		lblSelectedCategory.setBounds(65, 41, 113, 20);
		contentPane.add(lblSelectedCategory);

		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(243, 74, 68, 20);
		contentPane.add(lblItem);

		textFieldItem = new JTextField();
		textFieldItem.setBounds(324, 74, 131, 20);
		contentPane.add(textFieldItem);
		textFieldItem.setColumns(10);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {

				dispose();
			}
		});
		btnQuit.setBounds(300, 238, 113, 23);
		contentPane.add(btnQuit);

		JLabel lblNewQuantity = new JLabel("New quantity");
		lblNewQuantity.setBounds(243, 147, 78, 23);
		contentPane.add(lblNewQuantity);

		final JCheckBox chckbxChangeQuantity = new JCheckBox("Change quantity");
		chckbxChangeQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				if(chckbxChangeQuantity.isSelected()) {
					textFieldNewQuantity.setEditable(true);
				}
			}
		});
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

		final JComboBox comboBoxCategory = new JComboBox();
		comboBoxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				if(comboBoxCategory.getSelectedItem().equals("Food")) {
					textAreaSelectedCategory.setText("Food " + MainWindowGUI.showFood().toString());
					selectedCategory = MainWindowGUI.showFood();
					selectedCat = "Food";
				}

				if(comboBoxCategory.getSelectedItem().equals("Drinks")) {
					textAreaSelectedCategory.setText("Drinks " + MainWindowGUI.showDrinks().toString());
					selectedCategory = MainWindowGUI.showDrinks();
					selectedCat = "Drinks";
				}

				if(comboBoxCategory.getSelectedItem().equals("Hygiene")) {
					textAreaSelectedCategory.setText("Hygiene " + MainWindowGUI.showHygiene().toString());
					selectedCategory = MainWindowGUI.showHygiene();
					selectedCat = "Hygiene";
				}

				if(comboBoxCategory.getSelectedItem().equals("Other")) {
					textAreaSelectedCategory.setText("Other " + MainWindowGUI.showOther().toString()); 
					selectedCategory = MainWindowGUI.showOther();
					selectedCat = "Other";

				}
			}
		});
		comboBoxCategory.setModel(new DefaultComboBoxModel(new String[] {"- select category -", "Food", "Drinks", "Hygiene", "Other"}));
		comboBoxCategory.setBounds(216, 10, 158, 20);
		contentPane.add(comboBoxCategory);


		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				try {
					/*
					 * ako nije izabrao nijednu kategoriju naglasava da mora da izabere jednu da bi mogao njome da manipulise
					 */
					if(comboBoxCategory.getSelectedItem().equals("- select category -")){
						JOptionPane.showMessageDialog(contentPane, "You have to select a category!");
					}
					/*
					 * ako nije uneo nijedan item pita da li zelimo da izbrise celu kategoriju
					 * ako DA onda se brise cela kategorija i sastav textArea
					 * ako NE onda trazi da se upise item i pronalazi ga u listi i brise
					 */

					if(textFieldItem.getText().isEmpty()) {
						opt = JOptionPane.showConfirmDialog(contentPane, "You didn't enter an item! Do you want to delete the entire category?",
								"Warning", JOptionPane.YES_NO_CANCEL_OPTION);

						if(opt == JOptionPane.YES_OPTION) {
							selectedCategory = null;
							textAreaSelectedCategory.setText("");
							allDeleted = true;
						} else {
							if(opt == JOptionPane.NO_OPTION) {
								JOptionPane.showMessageDialog(contentPane, "You have to enter the item!", "Error!", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					if(selectedCategory.size() == 1 && selectedCategory.get(0).getItemName().equals(textFieldItem.getText())){
						selectedCategory = null;
						textAreaSelectedCategory.setText("");
						allDeleted = true;
					} else {
						for (int i = 0; i < selectedCategory.size(); i++) {

							if(selectedCategory.get(i).getItemName().equals(textFieldItem.getText())) {
								selectedCategory.remove(i);
								found = true; //ako je pronasao item onda postavlja da je pronadjen odn. found je true
								break;
							}
						}
						
						/*
						 * ako ga nije nasao obavestava korisnika da je uneo nepostojeci item
						 */
						if(found == false) {
							JOptionPane.showMessageDialog(contentPane, "Item you entered doesn't exist!", "Error!", JOptionPane.ERROR_MESSAGE);
						} else {
							textAreaSelectedCategory.setText(selectedCat + selectedCategory.toString()); //obrisan je item, lista kategorije je update-ovana
						}
						
						/*
						 * update-ujemo originalnu listu na odradjene promene
						 */
						if(selectedCat.equals("Food")) {
							MainWindowGUI.setFood(selectedCategory);
//							if (allDeleted == false) {
								MainWindowGUI.setFoodS("Food " + selectedCategory.toString());
//							} else {
//								MainWindowGUI.setFoodS("");
//							}
						}
						if(selectedCat.equals("Drinks")) {
							MainWindowGUI.setDrinks(selectedCategory);
							MainWindowGUI.setDrinksS("Drinks" + selectedCategory.toString());
						}
						if(selectedCat.equals("Hygiene")) {
							MainWindowGUI.setHygiene(selectedCategory);
							MainWindowGUI.setHygieneS("Hygiene " + selectedCategory.toString());
						}
						if(selectedCat.equals("Other")) {
							MainWindowGUI.setOther(selectedCategory);
							MainWindowGUI.setOtherS("Other " + selectedCategory.toString());
						}
					}

					textFieldItem.setText("");

					MainWindowGUI.setTextAreaShoppingList(MainWindowGUI.getFoodS() + MainWindowGUI.getDrinksS()
							+ MainWindowGUI.getHygieneS() + MainWindowGUI.getOtherS());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(236, 204, 113, 23);
		contentPane.add(btnDelete);

	}




}
