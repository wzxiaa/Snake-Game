public enum Direction{
	UP(0),
	RIGHT(1),
	DOWN(2),
	LEFT(3);
	
	private final int directionCode;
	
	private Direction(int directionCode) {
		this.directionCode = directionCode;
	}
	
	public int directionCode() {
		return directionCode;
	}
	
	/**
	 * chech if change of direction is valid
	 * eg.UP to DOWN is not valid
	 * @param direction
	 * @return boolean
	 */
	public boolean compatibleWith(Direction direction) {
		// your code here
		if(Math.abs(this.compareTo(direction)) == 2) {
			return false;
		}
		return true;
	}
}