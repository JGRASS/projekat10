package shoppinglist;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class SHOPen implements SHOPenInterfejs {

	LinkedList<Item> shoppingList = new LinkedList<Item>();
	
	public void addToList(Item item) {
		if (item == null)
			throw new RuntimeException("Item can't be null");
		
		if (shoppingList.contains(item))
			throw new RuntimeException("Item is already in the list");
		
		shoppingList.add(item);
	}

	public void deleteItem(Item item) { // dodati u edit gui-ju
		if (!shoppingList.contains(item))
			throw new RuntimeException("Item does not exist in the list");
		
		shoppingList.remove(item);
	}

	public void changeQuantity(Item item, String newQuantity) {
		
	}

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

	public LinkedList<Item> returnShoppingList() {
		return shoppingList;
	}


	
}
