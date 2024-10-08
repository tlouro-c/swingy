package tc.tlouro_c.swingy.models;

public enum Direction {
	NORTH(0, -1), SOUTH(+0, 1), EAST(+1, 0), WEST(-1, 0);

	private int xDelta;
	private int yDelta;

	private Direction(int xDelta, int yDelta) {
		this.xDelta = xDelta;
		this.yDelta = yDelta;
	};

	public int getXDelta() {
		return xDelta;
	}

	public int getYDelta() {
		return yDelta;
	}
}
