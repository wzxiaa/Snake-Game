import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GameController implements Runnable, KeyListener {
	
	private final Grid grid;
	private final GameView gameView;
	
	private boolean running;
	
	public GameController(Grid grid, GameView gameView) {
		this.grid = grid;
		this.gameView = gameView;
		this.running = true;
	}
	
	@Override
	public void run() {
		while(running) {
            try {
                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
            } catch (InterruptedException e) {
                break;
            }
			if(grid.nextRound()) {
            	while(!running){
					try {
						Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
					} catch (InterruptedException e) {
						break;
					}
				}
            	gameView.draw();
			}
			else{
            	//gameView.showGameOverMessage();
				System.out.println("Game Over!");
            	running = false;
				while(!running){
					try {
						Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
					} catch (InterruptedException e) {
						break;
					}
				}
			}
        }
        running = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch(keyCode) {
			case KeyEvent.VK_UP:
				grid.changeDirection(Direction.UP);
				break;
				
			case KeyEvent.VK_DOWN:
				grid.changeDirection(Direction.DOWN);
				break;

			case KeyEvent.VK_LEFT:
				grid.changeDirection(Direction.LEFT);
				break;
				
			case KeyEvent.VK_RIGHT:
				grid.changeDirection(Direction.RIGHT);
				break;

			default:
		}

		if(keyCode == KeyEvent.VK_SPACE) {
			if(running == true) {
				System.out.println("Game paused");
			}
			else {
				System.out.println("Game resumed");
			}
			running = !running;
		}

		if(keyCode == KeyEvent.VK_ENTER) {
			if(running == false) {
				running = true;
				grid.init();
				System.out.println("Game restart!");
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
