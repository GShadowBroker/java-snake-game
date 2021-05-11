package app.netlify.gledyson;

import java.awt.Rectangle;
import java.util.Random;

public class Food {
	private Rectangle food;
	private int x = 0;
	private int y = 0;

	public Food(Snake player) {
		Rectangle body = new Rectangle(Game.DIMENSION, Game.DIMENSION);

		this.food = body;
		placeAtRandomPosition(player);
	}

	public void placeAtRandomPosition(Snake player) {
		Random rand = new Random();

		int randX = rand.nextInt(Game.WIDTH) * Game.DIMENSION; // 0 - 599
		int randY = rand.nextInt(Game.HEIGHT) * Game.DIMENSION;

		while (true) {
			boolean onSnake = false;

			for (Rectangle bodyPart : player.getBody()) {
				if (randX == bodyPart.x && randY == bodyPart.y) {
					System.out.println("Oops, can't place food inside snek!");
					onSnake = true;
					break;
				}
			}

			if (onSnake) {
				randX = rand.nextInt(Game.WIDTH) * Game.DIMENSION;
				randY = rand.nextInt(Game.HEIGHT) * Game.DIMENSION;
			} else {
				break;
			}
		}

		x = randX;
		y = randY;

		food.setLocation(x, y);
		System.out.println(String.format("x: %3d, y: %3d", randX, randY));
	}

	public Rectangle getFood() {
		return food;
	}

	public void setFood(Rectangle food) {
		this.food = food;
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
