package tictactoe.bThreads.gameRules;

import static bp.BProgram.bp;
import static bp.eventSets.EventSetConstants.none;
import static tictactoe.events.StaticEvents.OWin;
import static tictactoe.events.StaticEvents.XWin;
import static tictactoe.events.StaticEvents.draw;
import tictactoe.externalApp.TicTacToe;
import bp.BThread;
import bp.eventSets.EventSet;
import bp.exceptions.BPJException;

/**
 * BThread that waits for a Win message and prints its message
 */
@SuppressWarnings("serial")
public class DeclareWinner extends BThread {

	@Override
	public void runBThread() throws BPJException {

		bp.bSync(none, new EventSet("WinnerDecided", XWin, OWin, draw), none);
		String msg;
		if (bp.lastEvent == XWin) {
			msg = "X Wins";
		} else if (bp.lastEvent == OWin) {
			msg = "O Wins";
		} else
			msg = "A Draw";

		System.out.println(msg);
		TicTacToe.playGUI.message.setText(msg);
//		bp.bSync(gameOver, none, none);
		bp.bSync(none, none, none);
	}
}
