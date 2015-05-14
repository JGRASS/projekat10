package shoppinglist.systemoperations;

import java.util.LinkedList;

import shoppinglist.Item;

public class SODeleteItem {

	public static void deleteItem(Item item, LinkedList<Item> shoppingList) {
		if (!shoppingList.contains(item))
			throw new RuntimeException("Item does not exist in the list");
		
		shoppingList.remove(item);
	}
	
}
