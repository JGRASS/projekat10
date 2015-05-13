package shoppinglist;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

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
	 * Method returns shopping list.
	 * @return shopping list as a Linked List
	 */
	public static LinkedList<Item> getShoppingList() {
		return shoppingList;
	}

	/**
	 * Method makes new shopping list.
	 * @param shop
	 */
	public static void setShoppingList(LinkedList<Item> shop) {
		shoppingList = shop;
	}
	
	/**
	 * Method adds new item to shopping list.
	 * @param new item as Object of class Item
	 * @throws java.lang.RuntimeException if entered name of item is null or list contains that item already
	 */
	public void addToList(Item item) {
		if (item == null)
			throw new RuntimeException("Item can't be null");
		
		if (shoppingList.contains(item))
			throw new RuntimeException("Item is already in the list");
		
		shoppingList.add(item);
	}

	/**
	 * Method deletes entered item from current shopping list
	 * @param new item as Object of class Item
	 * @throws java.lang.RuntimeException if list doesn't contains that item
	 */
	public void deleteItem(Item item) {
		if (!shoppingList.contains(item))
			throw new RuntimeException("Item does not exist in the list");
		
		shoppingList.remove(item);
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
		if (item == null)
			throw new RuntimeException("Item can't be null");
		
		if (newQuantity == null)
			throw new RuntimeException("Quantity can't be null");
		
		if (!shoppingList.contains(item))
			throw new RuntimeException("Item does not exist in the list");
		
		for (int i = 0; i < shoppingList.size(); i++) {
			if (shoppingList.get(i).equals(item)) {
				shoppingList.get(i).setQuantity(newQuantity);
			}
		}
		
	}

	/**
	 * Method saves Object in file.
	 * @param path as a String, which shows the path of file in which we want to save our Object
	 */
	public void saveFile(String path) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(path)));
			
			out.writeObject(shoppingList);
			
			out.close();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method reads Object from file.
	 * @param path as a String, which shows the path of file we want to read
	 */
	@SuppressWarnings("unchecked")
	public void readFile(String path) {
		try {
			ObjectInputStream in = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(path)));
			
			shoppingList = (LinkedList<Item>)(in.readObject());
			
			in.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method returns shopping list.
	 */
	public LinkedList<Item> returnShoppingList() {
		return shoppingList;
	}


	
}
