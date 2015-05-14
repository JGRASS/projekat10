package shoppinglist.systemoperations;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import shoppinglist.Item;

public class SOSaveFile {

	public static void saveFile(String path, LinkedList<Item> shoppingList) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(path)));
			
			out.writeObject(shoppingList);
			
			out.close();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
