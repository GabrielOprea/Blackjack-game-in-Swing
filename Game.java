import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener {
	
	/**
	 * 
	 */
	
	Vector<Card> playerCards;
	Vector<Card> dealerCards;
	
	static BufferedImage chips;
	private final static int offset = 30;
	private Timer clock;
	
	
	private static final long serialVersionUID = 1L;	
	Game()
	{
		
		setFocusable(true);
		addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
		
		playerCards = new Vector<Card>(1, 1);
		dealerCards = new Vector<Card>(1, 1);

		playerCards.add(new Card());
		playerCards.add(new Card());
		
		dealerCards.add(new Card());
		dealerCards.add(new Card());
		
		dealerCards.get(1).flipped = false;

		try {
			chips = ImageIO.read(new File("C:\\Users\\oprea\\eclipse-workspace\\Blackjack\\Cards\\CasinoChips.jpg"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		clock = new Timer(8, this);
		clock.start();
	}
	
	int getValue(boolean player)
	{
		// 0 - dealer
		// 1 - player
		
		int value = 0;
		
		if(player)
		{
			for(int i = 0; i < playerCards.size(); i++)
				value += playerCards.get(i).value;
		}
		else
		{
			for(int i = 0; i < dealerCards.size(); i++)
				value += dealerCards.get(i).value;
		}
		
		return value;
	}
	
	void Hit(boolean player)
	{
		//dealer stops on 17

		Card hit = new Card();
		
		if(player)
		{
			playerCards.add(hit);
		}
		else
		{
			dealerCards.add(hit);
		}
	}
	
	void Stand()
	{
		dealerCards.get(1).flipped = true;
		
		repaint();
		
		int sum =  0;
		for(Card c : dealerCards)
		{
			sum += c.value;
		}
		while(sum < 17)
		{
			Hit(false);
			
			sum += dealerCards.get(dealerCards.size() - 1).value;
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
			
		}
	}
	
	
	boolean checkBust(boolean player)
	{
		return getValue(player) > 21;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	
		if(e.getKeyCode() == KeyEvent.VK_H)
		{
			Hit(true);
			repaint();
			if(checkBust(true))
			{
				clock.stop();
				
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_S)
		{
			Stand();
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics g)
	{
		
		//Rectangle card = new Rectangle(10, 10, Card.width, Card.length);
		//g.setColor(Color.WHITE);
		//g.fillRect(10, 10, Card.width, Card.length);
		

		
		for(int i = 0; i < playerCards.size(); i++)
		{
			
			int xPos = i * 200 + offset;
			int yPos = 400;
			
			g.drawImage(playerCards.get(i).img, xPos, yPos, Card.length, Card.width, this);
		}
		
		for(int i = 0; i < dealerCards.size(); i++)
		{
			int xPos = i * 200 + offset;
			int yPos = 100;
		
			if(dealerCards.get(i).flipped)
				g.drawImage(dealerCards.get(i).img, xPos, yPos, Card.length, Card.width, this);
			else
				g.drawImage(Card.back, xPos, yPos, Card.length, Card.width, this);
		}
		
		String type = "SansSerif";
		int style = Font.ITALIC;
			
		Font decisions = new Font(type, style, 20);
			
		g.setFont(decisions);
					
		g.drawString("Press H to hit", 1300, 690);
		g.drawString("Press S to stand", 1300, 720);
		g.drawString("Press D to double-down", 1300, 750);
		
		Font players = new Font(type, style, 30);
		g.setFont(players);
		
		g.drawString("Dealer's cards", 200, 70);
		g.drawString("Player's cards", 200, 370);
	
		
		g.drawImage(chips, 1300, 40, 80, 80, this);
		//System.out.println(crtCard.cardName);
			
			//g.fillRect(xPos, yPos, Card.length, Card.width);
	}

}
