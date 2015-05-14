package shoppinglist.systemoperations;

import java.util.LinkedList;

import shoppinglist.Item;

public class SOChangeQuantity {

	public static void changeQuantity(Item item, String newQuantity, 
			LinkedList<Item> shoppingList) {
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
	
}
