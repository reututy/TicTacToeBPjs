package tictactoe.bThreads.gameRules;

import static bp.BProgram.bp;
import static bp.eventSets.EventSetConstants.none;
import static tictactoe.events.StaticEvents.gameOver;

import javax.swing.JButton;

import tictactoe.events.Move;
import tictactoe.externalApp.TicTacToe;
import bp.BThread;
import bp.eventSets.EventSet;
import bp.eventSets.EventsOfClass;
import bp.exceptions.BPJException;

/**
 * BThread for updating the labels of the buttons.
 */
@SuppressWarnings("serial")
public class UpdatePlayingGUI extends BThread {

	@Override
	public void runBThread() throws BPJException {
		interruptingEvents = new EventSet(gameOver);



		while (true) {

			// Wait for an event
			bp.bSync(none, new EventsOfClass(Move.class), none);

			// Update the board
			Move move = (Move) bp.lastEvent;
			JButton btt = TicTacToe.playGUI.buttons[move.row][move.col];
			btt.setText(move.displayString());
//			btt.setEnabled(false);
		}
	}
}
