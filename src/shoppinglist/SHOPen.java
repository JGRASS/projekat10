package shoppinglist;

import java.util.LinkedList;

public class SHOPen {

	private LinkedList<Category> categories = new LinkedList<Category>();
	private LinkedList<Recepie> recipies = new LinkedList<Recepie>();
	
	
	public SHOPen (LinkedList<Category> categories, LinkedList<Recepie> recepies) {
		this.categories = categories;
		this.recipies = recepies;
	}
	
	public LinkedList<Category> getCategories() {
		return categories;
	}
	public void setCategories(LinkedList<Category> categories) {
		this.categories = categories;
	}
	public LinkedList<Recepie> getRecipies() {
		return recipies;
	}
	public void setRecipies(LinkedList<Recepie> recipies) {
		this.recipies = recipies;
	}
	
	
}
