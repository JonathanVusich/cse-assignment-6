import java.math.BigDecimal;
import java.util.ArrayList;

public class VendingMachine {
  private BigDecimal money;
  private BigDecimal revenue;
  private ArrayList<Item> items;
  
  public VendingMachine(ArrayList<Item> items) {
    this.items = items;
    this.money = new BigDecimal(0);
    this.revenue = new BigDecimal(0);
  }
  
  public ArrayList<Item> currentStock() {
    return this.items;
  }
  
  public TransactionResult vend(String itemId) {
    
	  if (!this.containsID(items, itemId)) {   	
		  return TransactionResult.INVALID_ITEM;
	  }
    
	  final Item item = this.items.get(itemId);
    
	  if (new BigDecimal(item.price()).compareTo(this.money) == 1) {
		  return TransactionResult.INSUFFICIENT_FUNDS;
	  }
    
	  if (item.quantity() < 1) {
		  return TransactionResult.OUT_OF_STOCK;
	  }
    
	  item.updateQuantity(-1);
	  this.money = this.money.subtract(new BigDecimal(item.price()));
	  this.revenue = this.revenue.add(new BigDecimal(item.price()));
	  return TransactionResult.SUCCESS;
  	}
  
  public void insertMoney(BigDecimal money) {
    this.money = money;
  }
  
  public BigDecimal getBalance() {
    return this.money;
  }
  
  public BigDecimal revenue() {
    return this.revenue;
  }
  
  public BigDecimal dispenseChange() {
    BigDecimal money = this.money;
    this.money = new BigDecimal(0);
    return money;
  }
  
  public boolean containsID (final ArrayList<Item> list, final String id) {
	  return list.stream().filter(o -> o.getID().equals(id)).findFirst().isPresent();
  }
  
  public int idToIndex (String id) {
	  
  }
}