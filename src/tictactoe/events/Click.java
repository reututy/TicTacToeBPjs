package tictactoe.events;

import bp.Event;

/**
 * An event that is requested (with high priority) whenever the user presses a
 * button on the game board.
 */
@SuppressWarnings("serial")
public class Click extends Event {

	/**
	 * Row of the pressed button
	 */
	public int row;

	/**
	 * Column of the pressed button
	 */
	public int col;

	/**
	 * Constructor.
	 * 
	 * @param row
	 *            Row of the pressed button
	 * @param col
	 *            Column of the pressed button
	 */
	public Click(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		this.setName("Click(" + row + "," + col + ")");
	}

	/**
	 * @see java.lang.Object#toString()
	 */

}
