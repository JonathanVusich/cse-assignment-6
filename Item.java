public class Item {
	private String description;
	private double price;
	private int quantity;
	private String identifier;
	
	public Item(String itemDescription, double itemPrice, int itemQuantity, String identifier) {
		this.description = itemDescription;
		this.price = itemPrice;
		this.quantity = itemQuantity;
		this.identifier = identifier;
	}
	
	public String description() {
		return this.description;
	}
	
	public double price() {
		return this.price;
	}
	
	public int quantity() {
		return this.quantity;
	}
	
	public void updateQuantity(int delta) {
		this.quantity += delta;
	}
	
	public String getID() {
		return this.identifier;
	}
}