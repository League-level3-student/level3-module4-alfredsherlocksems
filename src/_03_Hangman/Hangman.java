package _03_Hangman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	JButton button = new JButton();
	Stack<String> stack = new Stack<String>();
	int lives = 8;
	StringBuilder b = new StringBuilder();
	
	public static void main (String[] args) {
		Hangman h = new Hangman();
		h.setup();
		
	}
	
	void setup() {
		frame.setVisible(true);
		frame.add(panel);
		button.addActionListener(this);
		panel.add(button);
		panel.add(label);
		String number = JOptionPane.showInputDialog("Type a number less than 266");
		int num = Integer.parseInt(number);
		for (int i = 0; i < num; i++) {
			String word = Utilities.readRandomLineFromFile("dictionary.txt");
			if (stack.contains(word)) {
				i--;
			}
			else {
				stack.add(word);
			}
		}
		String current = stack.pop();
		String dashes = "";
		for (int i = 0; i < current.length() - 1; i++) {
			dashes = dashes + "-";
		}
		label.setText(dashes);
		b.append(dashes);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String l = JOptionPane.showInputDialog("Tyoe in a letter");
		char[] array = l.toCharArray();
		char letter = array[0];
		boolean found = false;
		for(int i = 0; i < b.length(); i++) {
			if (b.charAt(i) == letter) {
				found = true;
				b.replace(i, i, l);
			}
					
		}
		if (!found) {
			lives--;
		}
		label.setText(b.toString());
	}
}
