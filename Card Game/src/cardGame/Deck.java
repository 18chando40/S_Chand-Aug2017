package cardGame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Deck implements Constants{

	private int x,y;

	public ArrayList<Card> deck;
	
	public Deck(int x, int y, int size) {
		deck = new ArrayList<>(size);
		this.x = x;
		this.y = y;
	}
	
	public Deck(int x, int y) {
		this(x, y, 13);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(x, y, DECK_WIDTH, DECK_HEIGHT);
		for(int i = 0; i < deck.size(); i++) {
			deck.get(i).render(g);
		}
	}
	
	public void addCard(Card c) {
		deck.add(c);
	}
	
	public void removeCard() {
		deck.remove(deck.size()-1);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x ,y ,DECK_WIDTH, DECK_HEIGHT);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
