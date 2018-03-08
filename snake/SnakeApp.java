import javax.swing.*;
import java.awt.*;

public class SnakeApp implements Runnable {
	
	Grid grid;
	GameView gameView;
	GameController gameController;
	
	public void run() {
	//initialize grid
		grid = new Grid(Settings.DEFAULT_GRID_WIDTH/Settings.DEFAULT_NODE_SIZE, Settings.DEFAULT_GRID_HEIGHT/Settings.DEFAULT_NODE_SIZE);
	//create JFrame
		JFrame window = new JFrame("Snake Application");
		Container contentPane = window.getContentPane();
		gameView = new GameView(grid);
		gameView.init();
		gameView.getCanvas().setPreferredSize(new Dimension(Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT));
		contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);
		
	//set window size unadjustable
		window.setResizable(false);
	//JFrame closure
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//windows painting and setting
		window.pack();
		window.setVisible(true);
		
	//initialize game controller and add event listener
		gameController = new GameController(grid, gameView);
		window.addKeyListener(gameController);
		
	//activate thread
		new Thread(gameController).start();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new SnakeApp());
	}
}