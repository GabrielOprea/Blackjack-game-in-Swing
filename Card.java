import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Card {

	int value;
	boolean color;
	//0 - red
	//1 - black
	boolean type;
	//0 - diamonds / spades
	//1 - hearts / clubs
	
	boolean flipped;
	
	final static int length = 150;
	final static int width = 200;
	
	String cardName = "";
	BufferedImage img;
	static BufferedImage back;
	
	// load images from resources files
	
	void loadImg() {
	
		try {
			//ClassLoader classLoader = Card.class.getClassLoader();
			img = ImageIO.read(new File("C:\\Users\\oprea\\eclipse-workspace\\Blackjack\\Cards\\" + cardName + ".png"));
			back = ImageIO.read(new File("C:\\Users\\oprea\\eclipse-workspace\\Blackjack\\Cards\\CardBack.jpg"));
		} catch (IOException ex) {
			System.out.println("Failed to load images");
		}
	}

	void getCardName() {
		switch(value){
		
			case 1: cardName += "Ace"; break;
			case 2: cardName += "Two"; break;
			case 3: cardName += "Three"; break;
			case 4: cardName += "Four"; break;
			case 5: cardName += "Five"; break;
			case 6: cardName += "Six"; break;
			case 7: cardName += "Seven"; break;
			case 8: cardName += "Eight"; break;
			case 9: cardName += "Nine"; break;
			case 10: cardName += "Ten"; break;
			case 11: cardName += "Jack"; break;
			case 12: cardName += "Queen"; break;
			case 13: cardName += "King"; break;
		}
			
			if(!color && !type)
				cardName += "Diamonds";
			else if(!color && type)
				cardName += "Hearts";
			else if(color && !type)
				cardName += "Spades";
			else if(color && type)
				cardName += "Clubs";
	}
	
	Card(){
		
		// Ace - 1 or 11
		// Jack - 11
		// Queen - 12
		// King - 13
		
		Random rand = new Random();
		
		value = rand.nextInt(13) + 1;
		color = rand.nextBoolean();
		type = rand.nextBoolean();
		
		getCardName();
		loadImg();
		
		if(value > 10)
			value = 10;
		
		flipped = true;
		
	}
	
}