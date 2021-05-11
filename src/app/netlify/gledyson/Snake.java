package app.netlify.gledyson;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	private List<Rectangle> body;
	private static final int WIDTH = Game.WIDTH;
	private static final int HEIGHT = Game.HEIGHT;
	private static final int DIMENSION = Game.DIMENSION;

	public enum Direction {
		UP, DOWN, RIGHT, LEFT, STOPPED
	}

	private static Direction direction = Direction.RIGHT;

	public Snake() {
		body = new ArrayList<>();

		Rectangle startingBody = new Rectangle(DIMENSION, DIMENSION);
		startingBody.setLocation(WIDTH / 2 * DIMENSION, (HEIGHT / 2) * DIMENSION);
		body.add(startingBody);

		startingBody = new Rectangle(DIMENSION, DIMENSION);
		startingBody.setLocation((WIDTH / 2 - 1) * DIMENSION, (HEIGHT / 2) * DIMENSION);
		body.add(startingBody);

		startingBody = new Rectangle(DIMENSION, DIMENSION);
		startingBody.setLocation((WIDTH / 2 - 2) * DIMENSION, (HEIGHT / 2) * DIMENSION);
		body.add(startingBody);
	}

	public List<Rectangle> getBody() {
		return body;
	}

	public void setBody(List<Rectangle> body) {
		this.body = body;
	}

	public void move() {

		if (direction == Direction.STOPPED)
			return;

		Rectangle head = body.get(0);
		Rectangle newBodyPart = new Rectangle(Game.DIMENSION, Game.DIMENSION);

		switch (direction) {
		case UP:
			newBodyPart.setLocation(head.x, head.y - Game.DIMENSION);
			break;
		case DOWN:
			newBodyPart.setLocation(head.x, head.y + Game.DIMENSION);
			break;
		case RIGHT:
			newBodyPart.setLocation(head.x + Game.DIMENSION, head.y);
			break;
		case LEFT:
			newBodyPart.setLocation(head.x - Game.DIMENSION, head.y);
			break;
		default:
			newBodyPart.setLocation(head.x + Game.DIMENSION, head.y);
		}

		body.add(0, newBodyPart);
		body.remove(body.size() - 1);
	}

	public void grow() {

		Rectangle head = body.get(0);
		Rectangle newBodyPart = new Rectangle(Game.DIMENSION, Game.DIMENSION);

		switch (direction) {
		case UP:
			newBodyPart.setLocation(head.x, head.y - Game.DIMENSION);
			break;
		case DOWN:
			newBodyPart.setLocation(head.x, head.y + Game.DIMENSION);
			break;
		case RIGHT:
			newBodyPart.setLocation(head.x + Game.DIMENSION, head.y);
			break;
		case LEFT:
			newBodyPart.setLocation(head.x - Game.DIMENSION, head.y);
			break;
		default:
			newBodyPart.setLocation(head.x + Game.DIMENSION, head.y);
		}

		body.add(0, newBodyPart);
	}

	public void goUp() {
		direction = Direction.UP;
	}

	public void goDown() {
		direction = Direction.DOWN;
	}

	public void goRight() {
		direction = Direction.RIGHT;
	}

	public void goLeft() {
		direction = Direction.LEFT;
	}

	public static Direction getDirection() {
		return direction;
	}

	public static void setDirection(Direction direction) {
		Snake.direction = direction;
	}
}
