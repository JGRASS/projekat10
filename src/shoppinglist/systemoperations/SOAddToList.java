package shoppinglist.systemoperations;

import java.util.LinkedList;

import shoppinglist.Item;

public class SOAddToList {

	public static void addToList(Item item, LinkedList<Item> shoppingList) {
		if (item == null)
			throw new RuntimeException("Item can't be null");
		
		if (shoppingList.contains(item))
			throw new RuntimeException("Item is already in the list");
		
		shoppingList.add(item);
	}
	
}
