package tictactoe.bThreads.gameRules;

import static bp.BProgram.bp;
import static bp.eventSets.EventSetConstants.none;
import static tictactoe.events.StaticEvents.gameOver;
import tictactoe.events.Click;
import tictactoe.events.X;
import bp.BThread;
import bp.eventSets.EventSet;
import bp.eventSets.EventsOfClass;
import bp.exceptions.BPJException;

/**
 * BThread that fires X when a button is clicked (in response to a P event)
 */
@SuppressWarnings("serial")
public class UserMove extends BThread {

	@Override
	public void runBThread() throws BPJException {
		interruptingEvents = new EventSet(gameOver);


		while (true) {
			// Wait for a P event

			bp.bSync(none,new EventsOfClass(Click.class),none);

			// Put an X
			Click p = (Click) bp.lastEvent;
			bp.bSync(new X(p.row, p.col),none,none);

		}
	}

}