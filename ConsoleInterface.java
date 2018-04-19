import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInterface implements UserInterface {
  
  public String waitForCategorySelection(ArrayList<String> categories) {
    System.out.println("Welcome to Jonathan's Super Vending Machines!\n\nI sense that you are hungry or thirsty....\n\n"
					+ "Please select a vending machine:\n");
		
    char option = 'A';
    for (String category : categories) {
      System.out.printf("%c-%s, ", option++, category);
    }
    System.out.println("X-Exit:");
    
    Scanner sc = new Scanner(System.in);
    while (true) {
      String userInput = sc.next().toUpperCase();
      
      if (userInput.length() != 1) {
        continue;
      }
      
      if (userInput.equals("X")) {
        return "-1";
      }
      
      int categoryId = userInput.charAt(0) - 'A';
      if (categoryId >= categories.size()) {
        continue;
      }
      
      return categories.get(categoryId);
    }
  }
  
  
  public int waitForItemSelection(ArrayList<Item> items) {
    
  }

  public int waitForMoney() {
  }


@Override
public String waitForCategorySelection(String[] categories) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public int waitForItemSelection(ArrayList<Item> items) {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public void displayBalance(BigDecimal money) {
	// TODO Auto-generated method stub
	
}


@Override
public void displayResult(TransactionResult result, BigDecimal change) {
	// TODO Auto-generated method stub
	
}


@Override
public void goodbye(BigDecimal totalRevenue) {
	// TODO Auto-generated method stub
	
}
}