package app.netlify.gledyson;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Game implements KeyListener {
	public static final String TITLE = "Gledyson's Awesome Snake Game!";
	public static final int HEIGHT = 30;
	public static final int WIDTH = 30;
	public static final int DIMENSION = 20;

	private JFrame window;
	private Graphics graphics;
	private Snake player;
	private Food food;

	public Game() {
		player = new Snake();
		food = new Food(player);
		window = new JFrame();
		graphics = new Graphics(this);

		window.add(graphics);
		window.setSize(WIDTH * DIMENSION, (HEIGHT * DIMENSION) + DIMENSION + 4); // DIMENSION + 4 is the height of the
																					// top bar (tested on Linux Mint 20)
		window.setResizable(false);
		window.setTitle(TITLE);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void update() {
		player.move();
	}

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public Graphics getGraphics() {
		return graphics;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public Snake getPlayer() {
		return player;
	}

	public void setPlayer(Snake player) {
		this.player = player;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		System.out.println(keyCode);

		switch (keyCode) {
		case KeyEvent.VK_UP:
			player.goUp();
			break;
		case KeyEvent.VK_DOWN:
			player.goDown();
			break;
		case KeyEvent.VK_RIGHT:
			player.goRight();
			break;
		case KeyEvent.VK_LEFT:
			player.goLeft();
			break;
		default:
			System.out.println("Invalid command for key code: " + keyCode);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
