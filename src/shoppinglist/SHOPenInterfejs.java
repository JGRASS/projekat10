package shoppinglist;

import java.util.LinkedList;

/**
 * Represents a shopping list interface.
 * @author Sanja Zelenovic, Milena Djurdjic, Nevena Djuricic
 * @version 1.0
 */
public interface SHOPenInterfejs {

	
	public LinkedList<Item> returnShoppingList();
	
	public void addToList(Item item);
	public void deleteItem(Item item);
	public void changeQuantity(Item item, String newQuantity);
	
	public void saveFile(String path);
	public void readFile(String path);
	
}
