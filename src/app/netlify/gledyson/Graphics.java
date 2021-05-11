package app.netlify.gledyson;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Graphics extends JPanel implements ActionListener {
	private static final long serialVersionUID = -6192374168235325516L;

	private Timer timer = new Timer(100, this);

	public enum GameState {
		START, RUNNING, END
	}

	private GameState state = GameState.START;

	private Game game;
	private Snake player;
	private Food food;

	public Graphics(Game game) {
		timer.start();

		this.game = game;
		this.player = game.getPlayer();
		this.food = game.getFood();

		// Add key listener
		this.addKeyListener(game);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}

	@Override
	protected void paintComponent(java.awt.Graphics graphics) {
		super.paintComponent(graphics);

		Graphics2D g = (Graphics2D) graphics;

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH * Game.DIMENSION, Game.HEIGHT * Game.DIMENSION);

		if (state == GameState.START) {

			g.setColor(Color.GREEN);
			String startMessage = "Press any KEY to start";
			g.drawString(startMessage, ((Game.WIDTH * Game.DIMENSION) / 2) - Game.DIMENSION - 40,
					((Game.HEIGHT * Game.DIMENSION) / 2) - Game.DIMENSION);
			
		} else if (state == GameState.RUNNING) {

			// drop the food
			g.setColor(Color.MAGENTA);
			g.fill(food.getFood());

			// drop the snake on the screen
			g.setColor(Color.GREEN);
			for (Rectangle bodyPart : player.getBody()) {
				g.fill(bodyPart);
			}
		} else if (state == GameState.END) {

			g.setColor(Color.RED);
			String startMessage = String.format("GAME OVER! Your score: %d", player.getBody().size() - 3);
			g.drawString(startMessage, ((Game.WIDTH * Game.DIMENSION) / 2) - Game.DIMENSION - 40,
					((Game.HEIGHT * Game.DIMENSION) / 2) - Game.DIMENSION);
		}

		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		repaint();
		game.update();

	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}
}
