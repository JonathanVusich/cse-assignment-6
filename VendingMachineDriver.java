import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineDriver {
  public static void main(String[] args) {
    
	String[] filenames = {"data/snacks.txt", "data/drinks.txt"};
    UserInterface ui = new GUIInterface();
    Map<String, ArrayList<Item>> categories = getCategories(filenames);
    BigDecimal revenue = new BigDecimal(0);
    String categoryName;
    String itemId;
    
    while ((categoryName = ui.waitForCategorySelection(categories.keySet())) != "") {
    	VendingMachine vm = new VendingMachine(categories.get(categoryName));
      
      back_to_category_select:
      while (true) {
        if (ui.waitForMoney() == new BigDecimal(-1)) {
          ui.displayResult(TransactionResult.CANCELLED, vm.dispenseChange());
          break back_to_category_select;
        }
        
        ui.displayBalance(vm.getBalance());
        
        back_to_insert_money:
    	while ((itemId = ui.waitForItemSelection(vm.currentStock())) != "") {
          TransactionResult result = vm.vend(itemId);
          
          switch (result) {
            case SUCCESS:
              ui.displayResult(result, vm.dispenseChange());
              break back_to_category_select;
             
            case INSUFFICIENT_FUNDS:
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
  
  

  private static Map<String, ArrayList<Item>> getCategories(String[] filenames) {
    HashMap<String, ArrayList<Item>> categories = new HashMap<String, ArrayList<Item>>();
    
    for (String f : filenames) {
      String basename = f.split("\\.(?=[^\\.]+$)")[0];
      String category = basename.substring(0, 1).toUpperCase() + basename.substring(1);
      categories.put(category, parseFile(f));
    }
    
    return categories;
  }
  
  private static ArrayList<Item> parseFile(String filename) {
    
	ArrayList<Item> stock = new ArrayList<Item>();
    String[] tokens;
	
	
    char letter = 'A';
    String identifier;
    int value = 0;
    int line = 0;
    String data;
    BufferedReader br = null;
    FileReader fr = null;
    // This is still broken, skips every other line
    
    
      try {
    	  fr = new FileReader(filename);
    	  br = new BufferedReader(fr);
      } catch (IOException ioe) {
    	  System.out.println("Error! File not found!");
    	
       try {
		while ((data = br.readLine()) != null) {
			   	   
		       line++;
		       value++;
		       if (value == 7) {
		     	  value = 1;
		       }
		       if (line != 0 && line%6 == 0) {
		     	  letter++;
		       }
		       
		       tokens = data.split(",");
		       identifier = Character.toString(letter) + value;
		       try {
		         stock.add(new Item(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), identifier));
		         System.out.println(stock.get(line).description());
		         System.out.println(stock.get(line).getID());
		       } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
		         System.err.println("Bad item in file " + filename + 
		             " on line " + (line+1) + " of " + filename);
		       }
		     }
	} catch (IOException e) {
		System.out.println("Error! File not found!");
	}
       }
      return stock;
    }
}