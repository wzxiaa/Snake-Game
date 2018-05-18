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
			this.grid.setBeforeDirection(this.grid.getSnakeDirection());

            try {
                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
            } catch (InterruptedException e) {
                break;
            }

			if(grid.nextRound()) {
            	gameView.draw();
			}

			else{
            	//gameView.showGameOverMessage();
				System.out.println("Game Over!");
				running = false;
			}
        }
        running = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		switch(keyCode) {
			case KeyEvent.VK_UP:
				if(this.grid.getBeforeDirection().compatibleWith(Direction.UP)) {
					grid.changeDirection(Direction.UP);
				}
				break;

			case KeyEvent.VK_DOWN:
				if(this.grid.getBeforeDirection().compatibleWith(Direction.DOWN)) {
					grid.changeDirection(Direction.DOWN);
				}
				break;

			case KeyEvent.VK_LEFT:
				if(this.grid.getBeforeDirection().compatibleWith(Direction.LEFT)) {
					grid.changeDirection(Direction.LEFT);
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				if(this.grid.getBeforeDirection().compatibleWith(Direction.RIGHT)) {
					grid.changeDirection(Direction.RIGHT);
				}
				break;

			case KeyEvent.VK_SPACE:
				if(!running) {
					running = true;
					new Thread(this).start();
				}
				else {
					running = false;
				}
				break;

			case KeyEvent.VK_ENTER:
				if(!running ) {
					running = true;
					new Thread(this).start();
					grid.init();
				}
				break;

			default:
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
