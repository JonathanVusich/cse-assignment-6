import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class GUIInterface extends JFrame implements UserInterface, ActionListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 700;
	private static String vendingMachine = "";
	private static boolean selection = false;
	private JTextArea menu;
	private JTextArea search;
	private JTextField searchResults;
	
	// ItemButtons
	
	private ItemButton buttonA;
	private ItemButton buttonB;
	private ItemButton buttonC;
	private ItemButton buttonD;
	private ItemButton buttonE;
	private ItemButton buttonF;
	private ItemButton button1;
	private ItemButton button2;
	private ItemButton button3;
	private ItemButton button4;
	private ItemButton button5;
	private ItemButton button6;
	
	private JButton getChange;
	private JButton addMoney;
	private JButton vend;
	
	private JScrollPane scrollBar;
	
	
	private JLabel itemSelection;
	private JLabel moneyLabel;
	private JLabel itemSearch;
	private JLabel itemResults;
	private JLabel makeSelection;
	
	private JTextField moneyRemaining = null;
	private JTextField itemSelect = null;
	private JTextField itemSearchBox = null;
	
	private JPanel abcPad;
	private JPanel numPad;
	private JPanel mainFrame;
	private JPanel rightPane;
	private JPanel leftPane;
	private JPanel searchPane;
	private JPanel padPane1;
	private JPanel padPane2;
	private JPanel padPane;
	private JPanel assortedElements;
	
	private BigDecimal money;
	
	public GUIInterface() {
		
		mainFrame = new JPanel(new GridLayout(1,2,5,5));
		mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		rightPane = new JPanel();
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
		searchPane = new JPanel();
		searchPane.setLayout(new BoxLayout(searchPane, BoxLayout.Y_AXIS));
		padPane1 = new JPanel();
		padPane1.setLayout(new BoxLayout(padPane1, BoxLayout.Y_AXIS));
		padPane2 = new JPanel();
		padPane2.setLayout(new BoxLayout(padPane2, BoxLayout.Y_AXIS));
		padPane = new JPanel(new GridLayout(1,2,30,30));
		leftPane = new JPanel(new BorderLayout());
		
		abcPad = new JPanel(new GridLayout(2,3,15,15));
		numPad = new JPanel(new GridLayout(2,3,15,15));
		
		buttonA = new ItemButton("A");
		buttonB = new ItemButton("B");
		buttonC = new ItemButton("C");
		buttonD = new ItemButton("D");
		buttonE = new ItemButton("E");
		buttonF = new ItemButton("F");
		button1 = new ItemButton("1");
		button2 = new ItemButton("2");
		button3 = new ItemButton("3");
		button4 = new ItemButton("4");
		button5 = new ItemButton("5");
		button6 = new ItemButton("6");
		
		
		abcPad.add(buttonA);
		abcPad.add(buttonB);
		abcPad.add(buttonC);
		abcPad.add(buttonD);
		abcPad.add(buttonE);
		abcPad.add(buttonF);
		
		numPad.add(button1);
		numPad.add(button2);
		numPad.add(button3);
		numPad.add(button4);
		numPad.add(button5);
		numPad.add(button6);
		
		
		padPane1.add(abcPad);
		itemSelection = new JLabel("Item Selection:");
		padPane1.add(itemSelection);
		moneyLabel = new JLabel("Money remaining:");
		padPane1.add(moneyLabel);
		getChange = new JButton("Get Change");
		getChange.addActionListener(this);
		padPane1.add(getChange);
		
		padPane2.add(numPad);
		itemSelect = new JTextField(10);
		padPane2.add(itemSelect);
		moneyRemaining = new JTextField(10);
		padPane2.add(moneyRemaining);
		addMoney = new JButton("Add Money");
		addMoney.addActionListener(this);
		padPane2.add(addMoney);
		
		padPane = new JPanel(new GridLayout(1,2,30,30));
		padPane.add(padPane1);
		padPane.add(padPane2);
		
		leftPane.add(padPane, BorderLayout.CENTER);
		
		assortedElements = new JPanel();
		assortedElements.setLayout(new BoxLayout(assortedElements, BoxLayout.Y_AXIS));
		itemSearch = new JLabel("Search for item:");
		assortedElements.add(itemSearch);
		itemSearchBox = new JTextField(10);
		itemSearchBox.addActionListener(this);
		assortedElements.add(itemSearchBox);
		itemResults = new JLabel("");
		assortedElements.add(itemResults);
		makeSelection = new JLabel("Make a selection:");
		assortedElements.add(makeSelection);
		
		leftPane.add(assortedElements, BorderLayout.NORTH);
		
		// Set up right pane
		
		mainFrame.add(leftPane);
		this.add(mainFrame);
		this.setVisible(true);
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new JTextArea("Hi there");
		search = new JTextArea("Hello");
		
		
		this.validate();
		mainFrame.setVisible(true);
		search.setEditable(false);
		
		
	}
	
	public void run() {
		mainFrame.setVisible(true);
	}

	@Override
	public String waitForCategorySelection(Set<String> categories) {
		String[] category = categories.stream().toArray(String[]::new);
		JFrame frame = new JFrame();
			String selection = (String)JOptionPane.showInputDialog(frame, "Please select the vending machine \n that you wish to purchase from:", "Input Money", JOptionPane.PLAIN_MESSAGE, null, category, category[0]);
			if (selection == null) {
				System.exit(0);
			}
			return selection;
		
	}

	@Override
	public String waitForItemSelection(ArrayList<Item> items) {
		// TODO Auto-generated method stub
		return "A1";
	}

	@Override
	public BigDecimal waitForMoney() {
		JFrame frame = new JFrame();
		boolean input = false;
		BigDecimal money = new BigDecimal(0);
		double number = 0;
		while (!input) {
			String text = (String)JOptionPane.showInputDialog("Please enter the amount of money that you would like to spend", null);
			try {
				number = Double.parseDouble(text);
				input = true;
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(frame, "Error! Invalid input!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			money = new BigDecimal(number);
			if (money.compareTo(new BigDecimal(0)) < 0) {
				JOptionPane.showMessageDialog(frame, "Error! Not a valid amount of money!", "Error!", JOptionPane.ERROR_MESSAGE);
				input = false;
			}
		}	
		return money;
	}

	@Override
	public void displayBalance(BigDecimal money) {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.US);
		formatter.format("$.2f", money);
		this.moneyRemaining.setText(sb.toString());
		
	}

	@Override
	public void displayResult(TransactionResult result, BigDecimal change) {
		if (result == TransactionResult.SUCCESS) {
		JOptionPane.showMessageDialog(this, "Transaction completed successfully!");
		} else if (result == TransactionResult.INVALID_ITEM) {
			JOptionPane.showMessageDialog(this, "Error! Invalid item!");
		} else if (result == TransactionResult.INSUFFICIENT_FUNDS) {
			JOptionPane.showMessageDialog(this, "Error! You have insufficient funds to purchase this item!");
		} else {
			JOptionPane.showMessageDialog(this, "Error! This item is out of stock!");
		}
	}

	@Override
	public void goodbye(BigDecimal totalRevenue) {
		JOptionPane.showMessageDialog(this, "Thank you for your purchases!");
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		}

}