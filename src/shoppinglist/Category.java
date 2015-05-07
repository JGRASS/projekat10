package shoppinglist;

import java.util.LinkedList;

public class Category {

	private String categoryName;
	private LinkedList<Item> categoryItems = new LinkedList<Item>();
	
	public Category (String categoryName, LinkedList<Item> categoryItems) {
		this.categoryItems = categoryItems;
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public LinkedList<Item> getCategoryItems() {
		return categoryItems;
	}
	public void setCategoryItems(LinkedList<Item> categoryItems) {
		this.categoryItems = categoryItems;
	}
	
	public void addNewItem (String itemName, String category) {
		Item item = new Item(itemName, category);
		if(!categoryItems.contains(item)) {
			categoryItems.add(item);
		}
	}
	
	
	
}
