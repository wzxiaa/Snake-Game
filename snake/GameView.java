import javax.swing.*;
import java.awt.*;

public class GameView {
	
	private final Grid grid;
	private JPanel canvas;
	
	
	public GameView(Grid grid) {
		this.grid = grid;
	}
	
	public void init() {
		canvas = new JPanel() {
			@Override
			public void paintComponent(Graphics graphics) {
				drawGridBackground(graphics);
				drawSnake(graphics, grid.getSnake());
				drawFood(graphics, grid.getFood());
			}
		};
	}
	
	
	/**
	 * repaint the canvas
	 */
	public void draw() {
		this.canvas.repaint();
	}
	
	
	/**
	 * repaint a snake
	 * @param graphics
	 * @param snake
	 */
	public void drawSnake(Graphics graphics, Snake snake) {
		//贪吃蛇用正方形画出
		for(Node n: snake.getBody()) {
			drawSquare(graphics, n, Settings.DEFAULT_SNAKE_COLOR);
		}
	}
	
	/**
	 * repaint food
	 * @param graphics
	 * @param squareArea
	 */
	public void drawFood(Graphics graphics, Node squareArea) {
		//食物用圆形画出
		drawCircle(graphics, squareArea, Settings.DEFAULT_FOOD_COLOR);
	}
	
	/**
	 * repaint the gridbackground
	 * @param graphics
	 */
	public void drawGridBackground(Graphics graphics) {
		graphics.setColor(Settings.DEFAULT_BACKGROUND_COLOR);
		graphics.fillRect(0, 0, Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT);
	}
	
	/**
	 * Draw a rectangle
	 * @param graphics
	 * @param squareArea
	 * @param color
	 */
	private void drawSquare(Graphics graphics, Node squareArea, Color color) {
		graphics.setColor(color);
		int size = Settings.DEFAULT_NODE_SIZE;
		graphics.fillRect(squareArea.getX() * size, squareArea.getY() * size, size - 1, size - 1);
	}
	
	/**
	 * Draw a circle
	 * @param graphics
	 * @param squareArea
	 * @param color
	 */
	private void drawCircle(Graphics graphics, Node squareArea, Color color) {
		graphics.setColor(color);
		int size = Settings.DEFAULT_NODE_SIZE;
		graphics.fillOval(squareArea.getX() * size, squareArea.getY() * size, size, size);
	}
	
	public void showGameOverMessage() {
		JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public JPanel getCanvas() {
		return canvas;
	}

}