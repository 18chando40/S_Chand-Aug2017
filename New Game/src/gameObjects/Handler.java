package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

import framework.BufferedImageLoader;
import framework.Game;
import framework.Game.STATE;
import userInterface.HUD;
/**
 * Handles all GameObjects and creates and keeps track of levels.
 * 
 *Also where lives are checked.
 */
public class Handler {
	public static int LEVEL = 0;
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	private BufferedImage[] levels = new BufferedImage[3];
	private HUD hud;
	
	public Handler(HUD hud){
		this.hud = hud;
		BufferedImageLoader loader = new BufferedImageLoader();
		
		Scanner levelReader;
//		System.out.println(file.exists());
		
		try {
			levelReader = new Scanner(new FileInputStream("lev.txt"));
			LEVEL = levelReader.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < levels.length; i++) {
			levels[i] = loader.loadImage("/level"+ (i + 1) +".png");
		}
	}
	
	public void tick() {
		if(hud.getLives() == 0) {//checks lives
			Game.gameState = STATE.Loss;
		}
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).tick(objects);
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
	}
	
	public void switchLevel() {
		clearLevel();
		loadLevel(levels[LEVEL]);
	}
	
	
	private void clearLevel() {
		objects.clear();
	}
	
	public void addObject(GameObject object) {
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objects.remove(object);
	}
	
	public void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		//System.out.println(w + ", " + h);
		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16 ) & 0xff;
				int green = (pixel >> 8 ) & 0xff;
				int blue = (pixel) & 0xff;
				//if statements determining objects:
				if(red == 255 && green == 255 & blue == 255) {
					addObject(new Block(xx*32, yy*32, ID.Block));
				}
				if(red == 0 && green == 0 & blue == 255) {
					addObject(new Player(xx*32, yy*32, ID.Player, this, hud));
				}
				if(red == 255 && green == 0 & blue == 0) {
					addObject(new DeathBlock(xx*32, yy*32, ID.DeathBlock));
				}
				if(red == 0 && green == 255 & blue == 0) {
					addObject(new Goal(xx*32, yy*32, ID.Goal));
				}
				if(red == 127 && green == 127 & blue == 127) {
					addObject(new TransparentBlock(xx*32, yy*32, ID.TransparentBlock));
				}
				if(red == 255 && green == 127 & blue == 39) {
					addObject(new Shooter(xx*32, yy*32, ID.Shooter, -2, hud));
				}
				if(red == 255 && green == 201 & blue == 14) {
					addObject(new Coin(xx*32, yy*32, ID.Coin));
				}
				if(red == 0 && green == 100 & blue == 100) {
					addObject(new Knife(xx*32, yy*32, ID.Knife));
				}
				if(red == 100 && green == 0 & blue == 100) {
					addObject(new Enemy(xx*32, yy*32, ID.Enemy, -2, hud));
				}
			}
		}
	}
}
