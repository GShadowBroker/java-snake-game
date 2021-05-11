package app.netlify.gledyson;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.stream.Collectors;

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
		if (didCollideWithWall()) {

			System.out.println("Collided with wall! GAME OVER!");

			return;

		} else if (didCollideWithSelf()) {

			System.out.println("You just ate yourself!");

			return;

		} else if (didCollideWithFood()) {

			System.out.println("Yummy! Delicious!");
			food.placeAtRandomPosition(player);
			player.grow();

			return;

		}

		player.move();
	}

	public boolean didCollideWithWall() {

		Rectangle head = player.getBody().get(0);

		if (head.x < 0 || head.x > (WIDTH * DIMENSION) - DIMENSION) {
			return true;
		} else if (head.y < 0 || head.y > (HEIGHT * DIMENSION) - DIMENSION) {
			return true;
		}

		return false;
	}

	public boolean didCollideWithFood() {

		Rectangle head = player.getBody().get(0);
		Rectangle foodPos = food.getFood();

		if (head.x == foodPos.x && head.y == foodPos.y) {
			return true;
		}

		return false;
	}

	public boolean didCollideWithSelf() {
		List<Rectangle> body = player.getBody();
		Rectangle head = body.get(0);

//		for (Rectangle bodyPart : body.subList(1, body.size() - 1)) {
//			
//			System.out.println("part: " + bodyPart);
//			System.out.println("head: " + head);
//			
//			if (head.x == bodyPart.x && head.y == bodyPart.y) {
//				return true;
//			}
//		}
		for (int i = 1; i < body.size() - 1; i++) {
			if (head.x == body.get(i).x && head.y == body.get(i).y) {
				return true;
			}
		}

		return false;
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

		switch (keyCode) {
		case KeyEvent.VK_UP:
			if (Snake.getDirection() == Snake.Direction.DOWN)
				break;
			player.goUp();
			break;
		case KeyEvent.VK_DOWN:
			if (Snake.getDirection() == Snake.Direction.UP)
				break;
			player.goDown();
			break;
		case KeyEvent.VK_RIGHT:
			if (Snake.getDirection() == Snake.Direction.LEFT)
				break;
			player.goRight();
			break;
		case KeyEvent.VK_LEFT:
			if (Snake.getDirection() == Snake.Direction.RIGHT)
				break;
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
