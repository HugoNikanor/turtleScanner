package pathfind;

public class Node {
	private int xPos;
	private int yPos;

	private Node parent;

	public Node( int xPos, int yPos ) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public Node( int xPos, int yPos, Node parent ) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.parent = parent;
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
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	@Override
	public String toString() {
		String s = 
			"xPos="+xPos+", yPos="+yPos;
		return s;
	}
}
