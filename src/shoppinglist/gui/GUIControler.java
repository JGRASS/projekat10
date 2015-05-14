package shoppinglist.gui;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import shoppinglist.Item;

public class GUIControler {
	
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowGUI frame = new MainWindowGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Open save window.
	 */
	public static void openSaveWindow() {
		if(!MainWindowGUI.textAreaShoppingList.getText().isEmpty()) {
			int option = JOptionPane.showConfirmDialog(MainWindowGUI.contentPane, "Do you want to save your shopping list?",
					"Save", JOptionPane.YES_NO_CANCEL_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				new SaveGUI().setVisible(true);
			} else {
				JOptionPane.showMessageDialog(MainWindowGUI.contentPane, "Error: You have to add an item in order to save it!",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Close application.
	 */
	public static void closeApp() {
		int option = JOptionPane.showConfirmDialog(MainWindowGUI.contentPane, "Do you really want to exit? REALLY?!",
				"Exit", JOptionPane.YES_NO_CANCEL_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * Creates new shopping list and and if wanted, opens save window and saves the list.
	 */
	public static void newList() {
		int opt = JOptionPane.showConfirmDialog(MainWindowGUI.contentPane, "Do you want to save your list before you open new?",
				"Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
		if(opt == JOptionPane.YES_OPTION) {
			openSaveWindow();
		}
		/*
		 * empties all previously added data from old shopping list
		 */
		if(!MainWindowGUI.returnFood().isEmpty()){
			MainWindowGUI.returnFood().remove();
			MainWindowGUI.setFoodS("");
		}
		if(!MainWindowGUI.returnDrinks().isEmpty()) {
			MainWindowGUI.returnDrinks().remove();
			MainWindowGUI.setDrinksS("");
		}

		if(!MainWindowGUI.returnHygiene().isEmpty()) {
			MainWindowGUI.returnHygiene().remove();
			MainWindowGUI.setHygieneS("");
		}

		MainWindowGUI.textAreaShoppingList.setText("");
		
		MainWindowGUI.textFieldNewItem.setText("");
		MainWindowGUI.textFieldQuantity.setText("");
	}

	/**
	 * Open show list window.
	 */
	public static void openShowListWindow() {
		new ShowListGUI().setVisible(true);
	}

	/**
	 * Open edit window.
	 */
	public static void openEditWindow() {
		if( !(MainWindowGUI.textAreaShoppingList.getText().isEmpty()) ) {
			new EditGUI().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(MainWindowGUI.contentPane, "Error: You have to add an item in order to change it!",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Adds new item to the list.
	 */
	public static void addToList() {
		try {
			if(MainWindowGUI.comboBoxItemCategory.getSelectedItem().equals("- select category -")) {
				JOptionPane.showMessageDialog(MainWindowGUI.contentPane, "You have to select a category!",
						"Error", JOptionPane.ERROR_MESSAGE);
			}

			addItemToMatchingList();

			MainWindowGUI.textAreaShoppingList.setText(MainWindowGUI.getFoodS() + 
					MainWindowGUI.getDrinksS() + MainWindowGUI.getHygieneS());

			MainWindowGUI.textFieldNewItem.setText("");
			MainWindowGUI.textFieldQuantity.setText("");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainWindowGUI.contentPane, "Error! " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Makes new item.
	 *
	 * @return the item
	 */
	public static Item makeNewItem() {

		Item newItem = new Item();

		// reading all data from text fields
		String item = MainWindowGUI.textFieldNewItem.getText();
		String category = MainWindowGUI.comboBoxItemCategory.getSelectedItem().toString();
		String quantity = MainWindowGUI.textFieldQuantity.getText(); // ne moraju da ga unesu

		if (item.isEmpty()) {
			JOptionPane.showMessageDialog(MainWindowGUI.contentPane, "You have to name the item!",
					"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			// setting of new items atributes
			newItem.setCategory(category);
			newItem.setItemName(item);
			newItem.setQuantity(quantity);
			return newItem;
		}
	}

	/**
	 * Adds the item to matching list.
	 */
	public static void addItemToMatchingList() {

		Item newItem = makeNewItem();

		if (newItem != null) {

			/*
			 * if new item is the first one from its category, we write the category name
			 */

			if(MainWindowGUI.comboBoxItemCategory.getSelectedItem().equals("Food")) {
				MainWindowGUI.returnFood().add(newItem);

				if (MainWindowGUI.returnFood().size() == 1) 
					MainWindowGUI.setFoodS("Food" + newItem.toString());
				else MainWindowGUI.setFoodS(MainWindowGUI.getFoodS() + newItem.toString());
			}

			if(MainWindowGUI.comboBoxItemCategory.getSelectedItem().equals("Drinks")) {
				MainWindowGUI.returnDrinks().add(newItem);

				if (MainWindowGUI.returnDrinks().size() == 1) 
					MainWindowGUI.setDrinksS("\n" + "Drinks" + newItem.toString());
				else MainWindowGUI.setDrinksS(MainWindowGUI.getDrinksS() + newItem.toString());
			}

			if(MainWindowGUI.comboBoxItemCategory.getSelectedItem().equals("Hygiene")) {
				MainWindowGUI.returnHygiene().add(newItem);

				if (MainWindowGUI.returnHygiene().size() == 1) 
					MainWindowGUI.setHygieneS("\n" + "Hygiene" + newItem.toString());
				else MainWindowGUI.setDrinksS(MainWindowGUI.getHygieneS() + newItem.toString());
			}
		}
	}
	
	/**
	 * Chooses category and writes it in the text area
	 */
	public static void selectCategory() {
		if (EditGUI.comboBoxCategory.getSelectedItem().equals("- select category -")) {
			EditGUI.textFieldItem.setEditable(false);
			EditGUI.btnDelete.setEnabled(false);
		} else {
			EditGUI.textFieldItem.setEditable(true);
			EditGUI.btnDelete.setEnabled(true);

			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Food")) { 
				if(!MainWindowGUI.returnFood().isEmpty()) {
					EditGUI.textAreaSelectedCategory.setText(MainWindowGUI.getFoodS().toString());
					EditGUI.selectedCategory = MainWindowGUI.returnFood();
					EditGUI.setSelectedCat("Food");
					EditGUI.setSelectedCatS(MainWindowGUI.getFoodS());
				} else {
					JOptionPane.showMessageDialog(EditGUI.contentPane, "Error! You have to choose a category that is not empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} 

			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Drinks")) {
				if(!MainWindowGUI.returnDrinks().isEmpty()) {
					EditGUI.textAreaSelectedCategory.setText(MainWindowGUI.getDrinksS().toString());
					EditGUI.selectedCategory = MainWindowGUI.returnDrinks();
					EditGUI.setSelectedCat("\nDrinks");
					EditGUI.setSelectedCatS(MainWindowGUI.getDrinksS());
				} else {
					JOptionPane.showMessageDialog(EditGUI.contentPane, "Error! You have to choose a category that is not empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Hygiene")) {
				if(!MainWindowGUI.returnHygiene().isEmpty()) {
					EditGUI.textAreaSelectedCategory.setText(MainWindowGUI.getHygieneS().toString());
					EditGUI.selectedCategory = MainWindowGUI.returnHygiene();
					EditGUI.setSelectedCat("\nHygiene");
					EditGUI.setSelectedCatS(MainWindowGUI.getHygieneS());
				} else {
					JOptionPane.showMessageDialog(EditGUI.contentPane, "Error! You have to choose a category that is not empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			EditGUI.textFieldItem.setEditable(true);
		}
	}

	/**
	 * Deletes item entered in text field 
	 */
	public static void deleteItem() {
		try {
			if(EditGUI.comboBoxCategory.getSelectedItem().equals("- select category -")){
				JOptionPane.showMessageDialog(EditGUI.contentPane, "You have to select a category!");
			}

			if(EditGUI.textFieldItem.getText().isEmpty()) {
				EditGUI.setOpt(JOptionPane.showConfirmDialog(EditGUI.contentPane, "You didn't enter an item! Do you want to delete the entire category?",
						"Warning", JOptionPane.YES_NO_CANCEL_OPTION));
				if(EditGUI.getOpt() == JOptionPane.YES_OPTION) {
					EditGUI.selectedCategory.clear();
					EditGUI.setSelectedCat("");
					EditGUI.setSelectedCatS("");
					EditGUI.textAreaSelectedCategory.setText("");
					EditGUI.setAllDeleted(true);
				} else {
					if(EditGUI.getOpt() == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(EditGUI.contentPane, "You have to enter the item!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			if(EditGUI.returnSelectedCategory().size() == 1 
					&& EditGUI.returnSelectedCategory().get(0).getItemName().equals(EditGUI.textFieldItem.getText())) {
				EditGUI.returnSelectedCategory().clear();
				EditGUI.setSelectedCat("");
				EditGUI.setSelectedCatS("");
				EditGUI.textAreaSelectedCategory.setText("");
				EditGUI.setAllDeleted(true);
			} else {
				for (int i = 0; i < EditGUI.returnSelectedCategory().size(); i++) {

					if(EditGUI.returnSelectedCategory().get(i).getItemName().equals(EditGUI.textFieldItem.getText())) {
						EditGUI.returnSelectedCategory().remove(i);
						EditGUI.setSelectedCatS(EditGUI.getSelectedCat());
						for (int j = 0; j < EditGUI.returnSelectedCategory().size(); j++) {
							EditGUI.setSelectedCatS(EditGUI.getSelectedCatS() + EditGUI.returnSelectedCategory().get(j));
						}

						EditGUI.setFound(true);
						break;
					}
				}

				if(EditGUI.isFound() == false && EditGUI.isAllDeleted() == false) {
					JOptionPane.showMessageDialog(EditGUI.contentPane, "Item you entered doesn't exist!", 
							"Error!", JOptionPane.ERROR_MESSAGE);
				}
				if (EditGUI.isFound() == true)	
					EditGUI.textAreaSelectedCategory.setText(EditGUI.getSelectedCat() + EditGUI.getSelectedCatS());

				updateOriginalList();
				
				EditGUI.textFieldItem.setText("");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Changes quantity for one item to amount entered in text field
	 */
	public static void changeQuantity() {
		if(!EditGUI.textAreaSelectedCategory.getText().isEmpty()) {

			for (int i = 0; i < EditGUI.returnSelectedCategory().size(); i++) {

				if(EditGUI.returnSelectedCategory().get(i).getItemName().equals(EditGUI.textFieldItem.getText())) {

					if(!EditGUI.textFieldNewQuantity.getText().isEmpty()) {

						EditGUI.returnSelectedCategory().get(i).setQuantity(EditGUI.textFieldNewQuantity.getText());

						EditGUI.setSelectedCatS(EditGUI.getSelectedCat());
						for (int j = 0; j < EditGUI.returnSelectedCategory().size(); j++) {
							EditGUI.setSelectedCatS(EditGUI.getSelectedCatS()+ 
									EditGUI.returnSelectedCategory().get(j));
						}
					} else {
						JOptionPane.showMessageDialog(EditGUI.contentPane, "Error: You didn't add any new amount!", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			EditGUI.textAreaSelectedCategory.setText(EditGUI.getSelectedCat() + 
					EditGUI.getSelectedCatS());


			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Food")) {
				EditGUI.textAreaSelectedCategory.setText(MainWindowGUI.getFoodS().toString());
				MainWindowGUI.setFood(EditGUI.returnSelectedCategory());
				MainWindowGUI.setFoodS(EditGUI.getSelectedCatS());
			}

			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Drinks")) {
				EditGUI.textAreaSelectedCategory.setText(MainWindowGUI.getDrinksS().toString());
				MainWindowGUI.setDrinks(EditGUI.returnSelectedCategory());
				MainWindowGUI.setDrinksS(EditGUI.getSelectedCatS());
			}

			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Hygiene")) {
				EditGUI.textAreaSelectedCategory.setText(MainWindowGUI.getHygieneS().toString());
				MainWindowGUI.setHygiene(EditGUI.returnSelectedCategory());
				MainWindowGUI.setHygieneS(EditGUI.getSelectedCatS());
			}

			MainWindowGUI.setTextAreaShoppingList(MainWindowGUI.getFoodS() + MainWindowGUI.getDrinksS()
					+ MainWindowGUI.getHygieneS());

			EditGUI.textFieldItem.setText("");
			EditGUI.textFieldNewQuantity.setText("");
		}
	}

	/**
	 * Updating original list
	 */
	public static void updateOriginalList() {
		if(EditGUI.isFound() == true) {

			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Food")) {
				EditGUI.textAreaSelectedCategory.setText(MainWindowGUI.getFoodS().toString());
				MainWindowGUI.setFood(EditGUI.returnSelectedCategory());
				MainWindowGUI.setFoodS(EditGUI.getSelectedCatS());
			}

			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Drinks")) {
				EditGUI.textAreaSelectedCategory.setText(MainWindowGUI.getDrinksS().toString());
				MainWindowGUI.setDrinks(EditGUI.returnSelectedCategory());
				MainWindowGUI.setDrinksS(EditGUI.getSelectedCatS());
			}

			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Hygiene")) {
				EditGUI.textAreaSelectedCategory.setText(MainWindowGUI.getHygieneS().toString());
				MainWindowGUI.setHygiene(EditGUI.returnSelectedCategory());
				MainWindowGUI.setHygieneS(EditGUI.getSelectedCatS());
			}

			MainWindowGUI.setTextAreaShoppingList(MainWindowGUI.getFoodS() + MainWindowGUI.getDrinksS()
					+ MainWindowGUI.getHygieneS());

		}
		if (EditGUI.isAllDeleted() == true) {
			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Food")) {
				MainWindowGUI.setFood(null);
				MainWindowGUI.setFoodS("");
			}

			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Drinks")) {
				MainWindowGUI.setDrinks(null);
				MainWindowGUI.setDrinksS("");
			}

			if(EditGUI.comboBoxCategory.getSelectedItem().equals("Hygiene")) {
				MainWindowGUI.setHygiene(null);
				MainWindowGUI.setHygieneS("");
			}
			MainWindowGUI.setTextAreaShoppingList(MainWindowGUI.getFoodS() + MainWindowGUI.getDrinksS()
					+ MainWindowGUI.getHygieneS());
		}
	}
	
	/**
	 * Saves list in text file
	 *
	 * @param listName the list name
	 */
	public static void saveList(String listName) {

		if (SaveGUI.textFieldListName.getText() != "") {
			try {

				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("files/" + listName + ".txt")));					

				out.println(MainWindowGUI.getFoodS());
				out.println(MainWindowGUI.getDrinksS());
				out.println(MainWindowGUI.getHygieneS());

				out.close();

				SaveGUI.textFieldListName.setText("");
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(SaveGUI.contentPane, "Error: " + e.getMessage(), 
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(SaveGUI.contentPane, "You have to name your list in order to save it!",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Shows list which name we wrote
	 *
	 * @param listName the list name we want to open
	 */
	public static void showList(String listName) {
		try {
			BufferedReader in = new BufferedReader(new FileReader("files/" + listName + ".txt"));
			boolean end = false;
			String s = "";

			while(!end) {
				String temp = in.readLine();
				if (temp == null) end = true;
				else s += "\n" + temp;
			}
			in.close();
			
			MainWindowGUI.textAreaShoppingList.setText(s);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(ShowListGUI.contentPane, "Error! The list you are trying to access doesn't exist!", 
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
