import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class GUIInterface extends JFrame implements ActionListener, UserInterface {
	
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 700;
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
	private JLabel moneyRemaining;
	
	public GUIInterface() {
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setVisible(true);
		Container container = new Container();
		container.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(container);
		
		menu = new JTextArea("Hi there");
		search = new JTextArea("Hello");
		buttonA = new ItemButton("A");
		buttonB = new ItemButton("B");
		buttonC = new ItemButton("C");
		buttonD = new ItemButton("D");
		buttonE = new ItemButton("E");
		buttonF = new ItemButton("F");
		
		container.add(search);
		container.add(menu);
		container.add(buttonA);
		container.add(buttonB);
		container.add(buttonC);
		container.add(buttonD);
		container.add(buttonE);
		container.add(buttonF);
		
		GroupLayout layout = new GroupLayout(container);
		container.setLayout(layout);
		
		
		search.setEditable(false);
	}
	
	public static void main(String[] args) {
		GUIInterface gui = new GUIInterface();
	}

}