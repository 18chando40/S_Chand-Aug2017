package derpyBurd;

import java.awt.Color;
import java.awt.Graphics;

import derpyBurd.Game.STATE;

public class Bird {
	
	private int x;
	private float y;
	private float dy;
	private int jumpCounter = 0;
	private Pipe p;
	private int lCounter = 0;
	
	public Bird(int x, int y, Pipe p) {
		this.x = x;
		this.y = y;
		this.p =p;
	}
	
	public void setdy(int dy) {
		this.dy = dy;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public float getdy() {
		return this.dy;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void tick() {
		this.y += dy;
		if(dy != 0 ) {
			jumpCounter ++;
		}
		if(jumpCounter > 15 || this.y < -20) {
				dy = 2;
		}
		if(this.y > 280) {
				dy = 0;
		}
		if(p.getBounds1().intersects(x,y,32,32) || p.getBounds2().intersects(x,y,32,32)) {
			lCounter++;
			if(lCounter > 2) {
				Game.gameState = STATE.Loss;
			}
		}
		if(p.getX() == this.x) {
			lCounter = 0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(x, (int) y, 32, 32);
		g.setColor(Color.white);
		g.fillOval(x +22, (int) y + 6, 4, 4);
		g.setColor(Color.yellow);
		int[] arr = {x + 31, x +32, x + 32 + 6};
		int[] arr2 = {(int)y + 11, (int)y + 17, (int) y + 14};
		g.fillPolygon(arr, arr2, 3);
	}
	
	public void jump() {
		dy = -3;
		jumpCounter = 0;
	}
}
