package shoppinglist;

import java.util.LinkedList;

import shoppinglist.systemoperations.SOAddToList;
import shoppinglist.systemoperations.SOChangeQuantity;
import shoppinglist.systemoperations.SODeleteItem;
import shoppinglist.systemoperations.SOReadFile;
import shoppinglist.systemoperations.SOSaveFile;

/**
 * Represents a shopping list.
 * @author Sanja Zelenovic, Milena Djurdjic, Nevena Djuricic
 * @version 1.0
 */
public class SHOPen implements SHOPenInterfejs {

	/**
	 * New shopping list.
	 */
	private static LinkedList<Item> shoppingList = new LinkedList<Item>();
	
	/**
	 * Method adds new item to shopping list.
	 * @param new item as Object of class Item
	 * @throws java.lang.RuntimeException if entered name of item is null or list contains that item already
	 */
	public void addToList(Item item) {
		SOAddToList.addToList(item, shoppingList);
	}

	/**
	 * Method deletes entered item from current shopping list
	 * @param new item as Object of class Item
	 * @throws java.lang.RuntimeException if list doesn't contains that item
	 */
	public void deleteItem(Item item) {
		SODeleteItem.deleteItem(item, shoppingList);
	}

	/**
	 * Method changes quantity of item to new quantity
	 * @param new item as Object of class Item, new quantity as String
	 * @throw java.lang.RuntimeException if 
	 *<ul>
	 * 	<li> item is null</li>
	 *  <li> quantity is null</li>
	 *</ul>
	 */
	public void changeQuantity(Item item, String newQuantity) {
		SOChangeQuantity.changeQuantity(item, newQuantity, shoppingList);
	}

	/**
	 * Method saves Object in file.
	 * @param path as a String, which shows the path of file in which we want to save our Object
	 */
	public void saveFile(String path) {
		SOSaveFile.saveFile(path, shoppingList);
	}

	/**
	 * Method reads Object from file.
	 * @param path as a String, which shows the path of file we want to read
	 */
	public void readFile(String path) {
		SOReadFile.readFile(path, shoppingList);
	}

	/**
	 * Method returns shopping list.
	 */
	public LinkedList<Item> returnShoppingList() {
		return shoppingList;
	}


	
}
