package shoppinglist;

public class Item {

	private String itemName;
	private String category;
	private String quantity;
	
	public Item (String itemName, String category) {
		this.category = category;
		this.itemName = itemName;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String toString() {
		return "   -" + itemName + " (" + quantity + ")";
	}
	
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
