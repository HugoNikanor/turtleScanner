package pathfind;

public class Cell {

	private int xPos;
	private int yPos;
	private int counter;

	public Cell( int xPos, int yPos, int counter ) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.counter = counter;

		System.out.println( this );
	}

	/**
	 * @return the xPos
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getYPos() {
		return yPos;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	public String toString() {
		String returnString = 
			"xPos="+xPos+", yPos="+yPos+", counter="+counter;
		return returnString;
	}

}
