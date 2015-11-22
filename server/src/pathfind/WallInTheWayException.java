package pathfind;

public class WallInTheWayException extends Exception {
	private static final long serialVersionUID = 1L;

	public WallInTheWayException() {
		super();
	}

	public WallInTheWayException(String message) {
		super(message);
	}
}
