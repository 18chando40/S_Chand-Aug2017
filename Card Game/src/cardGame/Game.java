package cardGame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, Constants {

	private static final long serialVersionUID = -8921419424614180143L;
	
	private Thread thread;
	private boolean running = false;
	
	private Deck[] slots = new Deck[6];
	
	private Deck deck;
	
	public Game() {
		new Window("Cards", GAME_WIDTH, GAME_HEIGHT, this);
		
		deck = new Deck(SPACE, SPACE);
		
		for(int i = 0; i < 6; i++) {
			slots[i] = new Deck((SPACE * (i+1)) +  (DECK_WIDTH * i), DECK_HEIGHT + SPACE * 2);
		}
		
		this.addMouseListener(new mouseHandler());
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public void tick() {
		//slots.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////////////////////
		g.setColor(Color.green);
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		
		deck.render(g);
		for(int i = 0; i < 6; i++) {
			slots[i].render(g);
		}
		/////////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}

	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
        	long now = System.nanoTime();
        	delta += (now - lastTime) / ns;
        	lastTime = now;
        	while(delta >=1) {
        		tick();
        		delta--;
        	}
        	if(running) {
        		render();
        	}
        	frames++;
        	if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: "+ frames);
				frames = 0;
        	}
        }
        stop();
	}

	public synchronized void start() {
		if(!running) {
			thread = new Thread(this);
			thread.start();
			running = true;
		}else
			return;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}