package framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gameObjects.Bullet;
import gameObjects.GameObject;
import gameObjects.Handler;
import gameObjects.ID;
import gameObjects.Player;

public class KeyInput implements KeyListener, Constants {
	
	Handler handler;
	boolean[] keyDown = new boolean[2];

	
	public KeyInput(Handler handler) {
		
		this.handler = handler;
		for(int i = 0; i < keyDown.length;i++) {
			keyDown[i] = false;
		}
		
	}
	public void keyPressed(KeyEvent e) {
		for(int i = 0; i < handler.objects.size(); i++) {
			GameObject object = handler.objects.get(i);
			if(object.getID() == ID.Player) {
				if(e.getKeyCode() == KeyEvent.VK_W && !((Player)object).isJumping()) {
					object.setDy(JUMP_HEIGHT);
					((Player) object).setJumping(true);
				}
				
				if(e.getModifiers() == 1) { // shift =1, ctrl = 2, alt = 8
					if(e.getKeyCode() == KeyEvent.VK_A) {
						object.setDx(-2);
						((Player) object).setDirection(-1);
						keyDown[0] = true;
					}
					if(e.getKeyCode() == KeyEvent.VK_D) {
						object.setDx(2);
						((Player) object).setDirection(1);
						keyDown[1] = true;
					}
				}else if(e.getKeyCode() == KeyEvent.VK_A) {
					object.setDx(-5);
					((Player) object).setDirection(-1);
					keyDown[0] = true;
				}else if(e.getKeyCode() == KeyEvent.VK_D) {
					object.setDx(5);
					((Player) object).setDirection(1);
					keyDown[1] = true;

				}
				if(((Player)object).hasKnife() && e.getKeyCode() == KeyEvent.VK_SPACE) {
					handler.addObject(new Bullet(object.getX() + PLAYER_WIDTH/2, 
							object.getY() + PLAYER_HEIGHT/5 * 2, ID.PlayerBullet,
							BULLET_SPEED * ((Player)object).getDirection(), 0));
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		for(GameObject object : handler.objects) {
			if(object.getID() == ID.Player) {
				if(e.getKeyCode() == KeyEvent.VK_A) {
					keyDown[0] = false;
				}
				if(e.getKeyCode() == KeyEvent.VK_D) {
					keyDown[1] = false;
				}
				if(!keyDown[0] && !keyDown[1]) {
					object.setDx(0);
				}
			}
		}
	}

	public void keyTyped(KeyEvent e) {

		
	}
	
}
