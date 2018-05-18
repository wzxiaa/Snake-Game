import java.util.*;

public class Grid {
	
    private Snake snake;
    private Node food;
    
    public final boolean status[][];
    private final int width;
    private final int height;
    
    // Default direction to be right
    private Direction snakeDirection = Direction.LEFT;

    private Direction beforeDirection = Direction.LEFT;
	
    public Grid(int width, int height) {
    	this.width = width;
    	this.height = height;
    	
    	status = new boolean[width][height];
    	init();
    }
    
    public void init() {
    	for(int i=0; i<width; ++i) {
    		Arrays.fill(status[i], false);
    	}
    	
    	snakeDirection = Direction.LEFT;

    	initSnake();
    	createFood();
    }
    
    private Snake initSnake() {
    	snake = new Snake();
    	int x = width/2;
    	int y = height/2;
    	int length = width/3;
    	for(int i=x; i<(x+length); i++) {
			Node init = new Node(i, y);
			snake.addTail(init);
			occupy(init);
		}
    	return snake;
    }
    
    public boolean nextRound() {
    	//Move in the given direction
    	Node snakeTail = snake.move(snakeDirection);
    	Node snakeHead = snake.getHead();
    	if(validPosition(snakeHead)) {
    		if(isFood(snakeHead)) {
    			snake.addTail(snakeTail);
    			createFood();
    		}
    		else {
    			dispose(snakeTail);
    		}
    		occupy(snakeHead);
    		return true;
    	}
    	return false;
    }
    
    public Node createFood() {
    	int x = 0;
    	int y = 0;  
    	
    	do {
    		Random r = new Random();
    		x = r.nextInt(width);
    		y = r.nextInt(height);
    	} while(status[x][y]);
    	
    	food = new Node(x,y);
    	return food;
    }
    
    public void changeDirection(Direction newDirection) {
    	if(beforeDirection.compatibleWith(newDirection)) {
    		snakeDirection = newDirection;
    	}
    }
    
    public boolean validPosition(Node area) {
    	int x = area.getX();
    	int y = area.getY();
    	return x >= 0 && x < width && y>= 0 && y < height && !status[x][y];
    }
    
    private void dispose(Node node) {
    	status[node.getX()][node.getY()] = false;
    }
    
    private void occupy(Node node) {
    	status[node.getX()][node.getY()] = true;
    }
    
    public boolean isFood(Node area) {
    	int x = area.getX();
    	int y = area.getY();
    	return x == food.getX() && y == food.getY();
    }
    
    public Node getFood() {
    	return food;
    }
    
    public int getWidth() {
    	return this.width;
    }
    
    public int getHeight() {
    	return this.height;
    }
    
    public Snake getSnake() {
    	return this.snake;
    }

	public Direction getSnakeDirection() {
		return snakeDirection;
	}

	public void setBeforeDirection (Direction newDirection) {
    	beforeDirection = newDirection;
	}

	public Direction getBeforeDirection (){
		return beforeDirection;
	}
}
