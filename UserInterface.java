import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

public interface UserInterface {
  String waitForCategorySelection(Set<String> categories);
  String waitForItemSelection(ArrayList<Item> items);
  BigDecimal waitForMoney();
  void displayBalance(BigDecimal money);
  void displayResult(TransactionResult result, BigDecimal change);
  void goodbye(BigDecimal totalRevenue);
}