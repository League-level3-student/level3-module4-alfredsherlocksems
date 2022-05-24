package _03_Hangman;

import java.awt.Dimension;
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
	String current;
	StringBuilder b = new StringBuilder();
	String dashes = "";
	String text;
	String used = "";
	boolean unused = true;
	
	public static void main (String[] args) {
		Hangman h = new Hangman();
		h.setup();
		
	}
	
	void setup() {
		frame.setVisible(true);
		panel.setPreferredSize(new Dimension(1000, 500));
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
		current = stack.pop();
		for (int i = 0; i < current.length(); i++) {
			dashes = dashes + "-";
		}
		text = dashes;
		label.setText(text);
		b.append(dashes);
		frame.pack();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String l = JOptionPane.showInputDialog("Type in a letter");
		char[] array = l.toCharArray();
		char letter = array[0];
		boolean found = false;
		for(int i = 0; i < used.length(); i++) {
			if (letter == used.charAt(i)) {
				unused = false;
				break;
			}
		}
		if (unused) {
			for(int i = 0; i < b.length(); i++) {
				if (current.charAt(i) == letter) {
					found = true;
					b.replace(i, i+1, l);
				}
						
			}
		}
		if (!found && unused) {
			lives--;
		}
		if (unused) {
			used = used + l + " ";
		}
		unused = true;
		found = false;
		text = b.toString() + " These letters have been used: " 
		+ used + "Lives = " + lives;
		label.setText(text);
		if (current.equals(b.toString())) {
			used = "";
			found = false;
			unused = true;
			JOptionPane.showMessageDialog(null, "You won with " + lives + " lives! The word was " + current + "!");
			lives = 8;
			setup();
		}
		else if (lives == 0) {
			used = "";
			found = false;
			unused = true;
			JOptionPane.showMessageDialog(null, "You lost; the word was " + current + "!");
			lives = 8;
			setup();
		}
		frame.pack();
	}
}
