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
import shoppinglist.SHOPenInterfejs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

/**
 * Represents a public class that edits shopping list.
 * @author Sanja Zelenovic, Milena Djurdjic, Nevena Djuricic
 * @version 1.0
 */
public class EditGUI extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldItem;
	private JTextField textFieldNewQuantity;
	
	private static LinkedList<Item> selectedCategory = new LinkedList<Item>();
	private static String selectedCat = "";
	private static String selectedCatS = "";
	
	private SHOPenInterfejs system;
	
	/**
	 * Additional parameter.
	 */
	int opt = 0;
	
	/**
	 * Shows us if item is found in selected category.
	 */
	boolean found = false;
	
	/**
	 * Shows us if all items are deleted from selected category.
	 */
	boolean allDeleted = false;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditGUI() {
		setResizable(false);
		setTitle("Edit");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 505, 300);
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
		btnQuit.setBounds(303, 238, 113, 23);
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

		

		final JComboBox comboBoxCategory = new JComboBox();
		comboBoxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				if(comboBoxCategory.getSelectedItem().equals("Food")) { 
					if(!MainWindowGUI.returnFood().isEmpty()) {
						textAreaSelectedCategory.setText("Food " + MainWindowGUI.returnFood().toString());
						selectedCategory = MainWindowGUI.returnFood();
						selectedCat = "Food";
						selectedCatS = MainWindowGUI.getFoodS();
					} else {
						JOptionPane.showMessageDialog(contentPane, "Error! You have to choose a category that is not empty!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} 
				
				if(comboBoxCategory.getSelectedItem().equals("Drinks")) {
					if(!MainWindowGUI.returnDrinks().isEmpty()) {
						textAreaSelectedCategory.setText("Drinks " + MainWindowGUI.returnDrinks().toString());
						selectedCategory = MainWindowGUI.returnDrinks();
						selectedCat = "Drinks";
						selectedCatS = MainWindowGUI.getDrinksS();
					} else {
						JOptionPane.showMessageDialog(contentPane, "Error! You have to choose a category that is not empty!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}

				if(comboBoxCategory.getSelectedItem().equals("Hygiene")) {
					if(!MainWindowGUI.returnHygiene().isEmpty()) {
						textAreaSelectedCategory.setText("Hygiene " + MainWindowGUI.returnHygiene().toString());
						selectedCategory = MainWindowGUI.returnHygiene();
						selectedCat = "Hygiene";
						selectedCatS = MainWindowGUI.getHygieneS();
					} else {
						JOptionPane.showMessageDialog(contentPane, "Error! You have to choose a category that is not empty!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}

				//				if(comboBoxCategory.getSelectedItem().equals("Other")) {
				//					textAreaSelectedCategory.setText("Other " + MainWindowGUI.showOther().toString()); 
				//					selectedCategory = MainWindowGUI.showOther();
				//					selectedCat = "Other";
				//				}
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
							selectedCategory.clear();
							selectedCat = "";
							selectedCatS = "";
							textAreaSelectedCategory.setText("");
							for (int i = 0; i < selectedCategory.size(); i++) {
								system.deleteItem(selectedCategory.get(i));
							}
							allDeleted = true;
						} else {
							if(opt == JOptionPane.NO_OPTION) {
								JOptionPane.showMessageDialog(contentPane, "You have to enter the item!", "Error!", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					if(selectedCategory.size() == 1 
							&& selectedCategory.get(0).getItemName().equals(textFieldItem.getText())) {
						system.deleteItem(selectedCategory.get(0));
						selectedCategory.clear();;
						selectedCat = "";
						selectedCatS = "";
						textAreaSelectedCategory.setText("");
						allDeleted = true;
					} else {
						for (int i = 0; i < selectedCategory.size(); i++) {

							if(selectedCategory.get(i).getItemName().equals(textFieldItem.getText())) {
								system.deleteItem(selectedCategory.get(i));
								selectedCategory.remove(i);
								selectedCatS = selectedCat;
								for (int j = 0; j < selectedCategory.size(); j++) {
									selectedCatS += selectedCategory.get(j).toString();
								}
								
								found = true; //ako je pronasao item onda postavlja da je pronadjen odn. found je true
								break;
							}
						}

						/*
						 * ako ga nije nasao obavestava korisnika da je uneo nepostojeci item
						 */
						if(found == false && allDeleted == false) {
							JOptionPane.showMessageDialog(contentPane, "Item you entered doesn't exist!", 
									"Error!", JOptionPane.ERROR_MESSAGE);
						}
						if (found == true)	
							textAreaSelectedCategory.setText(selectedCat 
									+ selectedCategory.toString()); //obrisan je item, lista kategorije je update-ovana u EditGUI

						/*
						 * update-ujemo originalnu listu na odradjene promene
						 */
						if(allDeleted == false) {
							if(comboBoxCategory.getSelectedItem().equals("Food")) {
								textAreaSelectedCategory.setText("Food " + MainWindowGUI.returnFood().toString());
								MainWindowGUI.setFood(selectedCategory);
								MainWindowGUI.setFoodS(selectedCatS);
							}

							if(comboBoxCategory.getSelectedItem().equals("Drinks")) {
								textAreaSelectedCategory.setText("Drinks " + MainWindowGUI.returnDrinks().toString());
								MainWindowGUI.setDrinks(selectedCategory);
								MainWindowGUI.setDrinksS(selectedCatS);
							}

							if(comboBoxCategory.getSelectedItem().equals("Hygiene")) {
								textAreaSelectedCategory.setText("Hygiene " + MainWindowGUI.returnHygiene().toString());
								MainWindowGUI.setHygiene(selectedCategory);
								MainWindowGUI.setHygieneS(selectedCatS);
							}

							MainWindowGUI.setTextAreaShoppingList(MainWindowGUI.getFoodS() + MainWindowGUI.getDrinksS()
									+ MainWindowGUI.getHygieneS()); // + MainWindowGUI.getOtherS()
						} else {
							if(comboBoxCategory.getSelectedItem().equals("Food")) {
								MainWindowGUI.setFood(null);
								MainWindowGUI.setFoodS("");
//								MainWindowGUI.setTextAreaShoppingList(MainWindowGUI.getDrinksS() + MainWindowGUI.getHygieneS());
							}

							if(comboBoxCategory.getSelectedItem().equals("Drinks")) {
								MainWindowGUI.setDrinks(null);
								MainWindowGUI.setDrinksS("");
//								MainWindowGUI.setTextAreaShoppingList(MainWindowGUI.getFoodS() + MainWindowGUI.getHygieneS());
							}

							if(comboBoxCategory.getSelectedItem().equals("Hygiene")) {
								MainWindowGUI.setHygiene(null);
								MainWindowGUI.setHygieneS("");
//								MainWindowGUI.setTextAreaShoppingList(MainWindowGUI.getFoodS() + MainWindowGUI.getDrinksS());
							}
							
							MainWindowGUI.setTextAreaShoppingList(MainWindowGUI.getFoodS() + MainWindowGUI.getDrinksS()
									+ MainWindowGUI.getHygieneS()); // + MainWindowGUI.getOtherS()
						}

						//						if(comboBoxCategory.getSelectedItem().equals("Other")) {
						//							textAreaSelectedCategory.setText("Other " + MainWindowGUI.showOther().toString()); 
						//							selectedCategory = MainWindowGUI.showOther();
						//							selectedCat = "Other";
					}

					textFieldItem.setText("");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(229, 204, 128, 23);
		contentPane.add(btnDelete);
		
		JButton btnChangeQuantity = new JButton("Change quantity");
		btnChangeQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				if(!textAreaSelectedCategory.getText().isEmpty()) {

					for (int i = 0; i < selectedCategory.size(); i++) {

						if(selectedCategory.get(i).getItemName().equals(textFieldItem.getText())) {

							if(!textFieldNewQuantity.getText().isEmpty()) {

								selectedCategory.get(i).setQuantity(textFieldNewQuantity.getText());

								selectedCatS = selectedCat;
								for (int j = 0; j < selectedCategory.size(); j++) {
									selectedCatS += selectedCategory.get(j).toString();
								}
								
								system.changeQuantity(selectedCategory.get(i), textFieldNewQuantity.getText());
							} else {
								JOptionPane.showMessageDialog(contentPane, "Error: You didn't add any new amount!", 
										"Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					textAreaSelectedCategory.setText(selectedCat + selectedCategory.toString());
					
					if(comboBoxCategory.getSelectedItem().equals("Food")) {
						textAreaSelectedCategory.setText("Food " + MainWindowGUI.returnFood().toString());
						MainWindowGUI.setFood(selectedCategory);
						MainWindowGUI.setFoodS(selectedCatS);
					}

					if(comboBoxCategory.getSelectedItem().equals("Drinks")) {
						textAreaSelectedCategory.setText("Drinks " + MainWindowGUI.returnDrinks().toString());
						MainWindowGUI.setDrinks(selectedCategory);
						MainWindowGUI.setDrinksS(selectedCatS);
					}

					if(comboBoxCategory.getSelectedItem().equals("Hygiene")) {
						textAreaSelectedCategory.setText("Hygiene " + MainWindowGUI.returnHygiene().toString());
						MainWindowGUI.setHygiene(selectedCategory);
						MainWindowGUI.setHygieneS(selectedCatS);
					}

					MainWindowGUI.setTextAreaShoppingList(MainWindowGUI.getFoodS() + MainWindowGUI.getDrinksS()
							+ MainWindowGUI.getHygieneS()); // + MainWindowGUI.getOtherS()
					
					textFieldItem.setText("");
					textFieldNewQuantity.setText("");
					
				}
			}
		});
		btnChangeQuantity.setBounds(359, 204, 130, 23);
		contentPane.add(btnChangeQuantity);

	}
}
