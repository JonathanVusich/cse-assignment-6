import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestClass {

	public static void main(String[] args) {
		ArrayList<Item> items = parseFile("data/snacks.txt");
		VendingMachine vm = new VendingMachine(items);
		System.out.println(vm.idToIndex("B2"));
		
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
	    	  while ((data = br.readLine()) != null) {
			       if (data.length() == 0) {
			    	   continue;
			       }
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
			         line++;
			       } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			         System.err.println("Bad item in file " + filename + 
			             " on line " + (line+1) + " of " + filename);
			       }
			     }
		} catch (IOException e) {
			System.out.println("Error! File not found!");
		}
	      return stock;
	    }
	}
