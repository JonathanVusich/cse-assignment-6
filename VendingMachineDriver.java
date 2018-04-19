import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class VendingMachineDriver {
  public static void main(String[] filenames) {
    
    UserInterface ui = new ConsoleInterface();
    Map<String, ArrayList<Item>> categories = getCategories(filenames);
    BigDecimal revenue = BigDecimal(0);
    
    while ((String categoryName = ui.waitForCategorySelection(categories.keySet().toArray())) != -1) {
    	VendingMachine vm = new VendingMachine(categories.get(categoryName));
      
      back_to_category_select:
      while (true) {
        if (vm.waitForMoney() == -1) {
          ui.displayResult(TransactionResult.CANCELLED, vm.dispenseChange());
          break back_to_category_select;
        }
        
        ui.displayBalance(vm.getBalance());
        
        back_to_insert_money:
    	while ((int itemId = ui.waitForItemSelection(vm.currentStock())) != -1) {
          TransactionResult result = vm.vend(itemId);
          
          switch (result) {
            case TransactionResult.SUCCESS:
              ui.displayResult(result, vm.dispenseChange());
              break back_to_category_select;
             
            case TransactionResult.INSUFFICIENT_FUNDS:
              ui.displayResult(result, vm.getBalance());
              break back_to_insert_money;
             
            default: // INVALID_ITEM and OUT_OF_STOCK
              ui.displayResult(result, vm.getBalance());
              // Goes back to selecting item
          }
      	}
      }
      
      // Exiting this vending machine back to category select
      revenue = revenue.add(vm.revenue());
    }
    
    ui.goodbye(revenue);
  }
  
  

  private Map<String, ArrayList<Item>> getCategories(String[] filenames) {
    Map<String, ArrayList<Item>> categories = new HashMap<String, ArrayList<Item>>();
    
    for (String f : filenames) {
      String basename = f.split("\\.(?=[^\\.]+$)")[0];
      String category = basename.substring(0, 1).toUpperCase() + basename.substring(1);
      categories.add(category, parseFile(f));
    }
    
    return categories;
  }
  
  private ArrayList<Item> parseFile(String filename) {
    ArrayList<Item> stock = new ArrayList<Item>();
    
    try (File file = new File(filename)) {
      try (Scanner scan = new Scanner(file)) {
        for (int line = 0; scan.hasNextLine(); line++) {
          String[] tokens = scan.nextLine().split(",");
          try {
            stock.add(new Item(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2])));
          } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            System.err.println("Bad item in file " + filename + 
                " on line " + (line+1) + " of " + filename);
          }
        }
      }
    }
    
    return stock;
  }
}
