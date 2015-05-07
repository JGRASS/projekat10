package shoppinglist;

import java.util.LinkedList;

public class Recepie {

	private String recepieName;
	private LinkedList<Item> recepieItems = new LinkedList<Item>();
	
	public Recepie (String recepieName, LinkedList<Item> recepieItems) {
		this.recepieItems = recepieItems;
		this.recepieName = recepieName;
	}
	
	public String getRecepieName() {
		return recepieName;
	}
	public void setRecipieName(String recipieName) {
		this.recepieName = recipieName;
	}
	public LinkedList<Item> getRecepieItems() {
		return recepieItems;
	}
	public void setRecepieItems(LinkedList<Item> recepieItems) {
		this.recepieItems = recepieItems;
	}
	
	
	
}
