package shoppinglist;
/**
 * Represents one item of the list.
 * 
 * @author Sanja Zelenovic, Milena Djurdjic, Nevena Djuricic
 * @version 1.0
 */
public class Item {

	/**
	 * Name of item.
	 */
	private String itemName;
	
	/**
	 * Name of category of item.
	 */
	private String category;
	
	/**
	 * Quantity of exact item, in exact shopping list.
	 */
	private String quantity;
	
	/**
	 * Method returns name of item.
	 * @return name of item as a String
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * Method sets name of item to entered value.
	 * @param new name of item
	 * @throws java.lang.RuntimeException if entered name of item is
	 *<ul>
	 * 	<li>null</li>
	 *  <li>empty String</li>
	 *</ul>
	 */
	public void setItemName(String itemName) {
		if (itemName == null || itemName.isEmpty())
			throw new RuntimeException("You have to enter the name of item!");
		this.itemName = itemName;
	}
	
	/**
	 * Method returns name of the category of exact item.
	 * @return name of category as a String
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Method sets name of category to entered value.
	 * @param new name of category
	 * @throws java.lang.RuntimeException if entered name of category is
	 *<ul>
	 * 	<li>null</li>
	 *  <li>empty String</li>
	 *</ul>
	 */
	public void setCategory(String category) {
		if (category == null || category.isEmpty())
			throw new RuntimeException("You have to enter the name of category!");
		this.category = category;
	}
	
	/**
	 * Method that returns quantity of exact item from the list.
	 * @return quantity of exact item as a String
	 */
	public String getQuantity() {
		return quantity;
	}
	
	/**
	 * Method sets quantity to entered value.
	 * @param new quantity of exact item
	 * @throws java.lang.RuntimeException if entered value of quantity is
	 *<ul>
	 * 	<li>null</li>
	 *  <li>empty String</li>
	 *</ul>
	 */
	public void setQuantity(String quantity) {
		if (category == null || category.isEmpty())
			throw new RuntimeException("Morate uneti kolicinu!");
		this.quantity = quantity;
	}
	/**
	 * Method that is called every time we write an object of Item class somewhere
	 */
	public String toString() {
		if (quantity.isEmpty()) {
			return "\n   - " + itemName;
		}
		String q = quantity;
		return "\n   - " + itemName + " (" + q + ")";
	}
	/**
	 * Method that checks if two objects are equal
	 * @param some object we want to compare
	 * @return true if objects are equal and false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Item) {
			Item item = (Item) o;
			if(item.getCategory().equals(category) && item.getItemName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
	
	
}
