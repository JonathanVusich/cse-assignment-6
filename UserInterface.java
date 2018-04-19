import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

public interface UserInterface {
  String waitForCategorySelection(Set<String> categories);
  int waitForItemSelection(ArrayList<Item> items);
  int waitForMoney();
  void displayBalance(BigDecimal money);
  void displayResult(TransactionResult result, BigDecimal change);
  void goodbye(BigDecimal totalRevenue);
}