import java.math.BigDecimal;
import java.util.ArrayList;

public interface UserInterface {
  String waitForCategorySelection(String[] categories);
  int waitForItemSelection(ArrayList<Item> items);
  int waitForMoney();
  void displayBalance(BigDecimal money);
  void displayResult(VendingMachine.TransactionResult result, BigDecimal change);
  void goodbye(BigDecimal totalRevenue);
}