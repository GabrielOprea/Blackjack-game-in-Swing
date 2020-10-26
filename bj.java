import java.awt.Color;

import javax.swing.JFrame;

public class bj {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Game BlackJack = new Game(); 

		System.out.println("Player has value " + BlackJack.getValue(true));
		System.out.println("Dealer has value " + BlackJack.getValue(false));
		
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 1600, 800);
		frame.setTitle("Blackjack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.black);
		frame.setResizable(false);

		frame.add(BlackJack);
		//frame.setResizable(true);
		frame.setVisible(true);
		
	}

}
