import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Tile implements Constants{
	
	private int x, y, proximity = 0,temp; 
	private boolean mined, clicked;

	public Tile(int row, int col, boolean mined, int temp) {
		this.x = col * TILE_LENGTH;
		this.y = row * TILE_LENGTH;
		this.mined = mined;
		this.temp = temp;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		if(clicked) {
			g.setColor(Color.WHITE);
			g.drawRect(x, y, TILE_LENGTH, TILE_LENGTH);
			g.setColor(Color.BLACK);
			if(mined) {
				g.fillOval(x, y, TILE_LENGTH, TILE_LENGTH);
			}else if(proximity>0){
				g.setFont(new Font(null, 0, 64));
				g.drawString(""+proximity, x + 5, y + TILE_LENGTH-2);
			}
		}else {
			g.setColor(new Color(temp * 1, temp * 2, temp * 4));
			g.fillRect(x, y, TILE_LENGTH, TILE_LENGTH);
		}
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

	public boolean hasMine() {
		return mined;
	}

	public void setMine(boolean mine) {
		this.mined = mine;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public void click() {
		clicked = true;
	}

	public int getProximity() {
		return proximity;
	}

	public void setProximity(int proximity) {
		this.proximity = proximity;
	}
}