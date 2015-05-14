package shoppinglist.systemoperations;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import shoppinglist.Item;

public class SOReadFile {

	public static void readFile(String path, LinkedList<Item> shoppingList) {
		try {
			ObjectInputStream in = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(path)));
			
			shoppingList = (LinkedList<Item>)(in.readObject());
			
			in.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
