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

public class GUIInterface implements UserInterface, ActionListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 700;
	private static String vendingMachine = "";
	private static boolean selection = false;
	private JTextArea menu;
	private JTextArea search;
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
	private JScrollPane scrollBar = new JScrollPane(menu);
	private JTextPane searchResults;
	private JLabel itemSelection;
	private JLabel makeASelection;
	private JTextField moneyRemaining = null;
	private JPanel abcPad;
	private JPanel numPad;
	private JComboBox chooseMachine;
	private JTextField enterMoney;
	private BigDecimal money;
	private static JFrame mainFrame;
	
	public GUIInterface() {
		
		mainFrame = new JFrame();
		
		mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		mainFrame.setVisible(false);
		mainFrame.setLayout(new BorderLayout());
		abcPad = new JPanel();
		numPad = new JPanel();
		
		
		numPad.setLayout(new GridLayout(2,3,15,15));
		abcPad.setLayout(new GridLayout(2,3,15,15));
		
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
		
		
		mainFrame.add(abcPad, BorderLayout.CENTER);
		mainFrame.add(numPad, BorderLayout.WEST);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new JTextArea("Hi there");
		search = new JTextArea("Hello");
		
		
		mainFrame.validate();
		search.setEditable(false);
		
		
	}
	
	public void run() {

		mainFrame.setVisible(true);
	}

	@Override
	public String waitForCategorySelection(Set<String> categories) {
		String[] category = categories.stream().toArray(String[]::new);
		chooseMachine = new JComboBox(category);
		chooseMachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() instanceof JComboBox) {
					JComboBox cb = (JComboBox)event.getSource();
					vendingMachine = (String)cb.getSelectedItem();
					selection = true;				
				}
			}
		});
		
		JFrame machineSelect = new JFrame();
		machineSelect.setTitle("Vending Machine Selection");
		machineSelect.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		machineSelect.setLayout(new BorderLayout());
		machineSelect.add(chooseMachine);
		machineSelect.setVisible(true);
		machineSelect.validate();
		
		while(!selection) {
		}
		
		this.selection = false;
		machineSelect.setVisible(false);
		return vendingMachine;
		
		
	}

	@Override
	public String waitForItemSelection(ArrayList<Item> items) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal waitForMoney() {
		enterMoney = new JTextField(8);
		enterMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() instanceof JTextField) {
					JTextField jtf = (JTextField)event.getSource();
					double cash = Double.parseDouble(jtf.getText());
					money = new BigDecimal(cash);
					selection = true;
				}
			}
		});
		
		JFrame getMoney = new JFrame();
		getMoney.setTitle("Enter money");
		getMoney.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		getMoney.setLayout(new BorderLayout());
		getMoney.add(enterMoney);
		getMoney.setVisible(true);
		getMoney.validate();
		
		while(!selection) {
			
		}
		this.selection = false;
		getMoney.setVisible(false);
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
		JOptionPane.showMessageDialog(mainFrame, "Transaction completed successfully!");
		} else if (result == TransactionResult.INVALID_ITEM) {
			JOptionPane.showMessageDialog(mainFrame, "Error! Invalid item!");
		} else if (result == TransactionResult.INSUFFICIENT_FUNDS) {
			JOptionPane.showMessageDialog(mainFrame, "Error! You have insufficient funds to purchase this item!");
		} else {
			JOptionPane.showMessageDialog(mainFrame, "Error! This item is out of stock!");
		}
	}

	@Override
	public void goodbye(BigDecimal totalRevenue) {
		JOptionPane.showMessageDialog(mainFrame, "Thank you for your purchases!");
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		}

}