package tictactoe.externalApp;

// Main program for running Tic Tac Toe for playing and for model-checking.
// model-checking run configuration
// -Dsearch=BFS
// 

import static bp.BProgram.bp;
import static bp.BProgramControls.globalRunMode;
import tictactoe.bThreads.gameRules.DeclareWinner;
import tictactoe.bThreads.gameRules.DetectDraw;
import tictactoe.bThreads.gameRules.DetectOWin;
import tictactoe.bThreads.gameRules.DetectXWin;
import tictactoe.bThreads.gameRules.EnforceTurns;
import tictactoe.bThreads.gameRules.SquareTaken;
import tictactoe.bThreads.gameRules.UpdateMCMonitorGUI;
import tictactoe.bThreads.gameRules.UpdatePlayingGUI;
import tictactoe.bThreads.gameRules.UserMove;
import tictactoe.bThreads.tactics.AddThirdO;
import tictactoe.bThreads.tactics.DefaultOMoves;
import tictactoe.bThreads.tactics.PreventAnotherXFork;
import tictactoe.bThreads.tactics.PreventThirdX;
import tictactoe.bThreads.tactics.PreventXFork;
import tictactoe.bThreads.tactics.PreventYetAnotherXFork;
import tictactoe.environment.XAllMoves;
import bApplication.BApplication;
import bp.BProgram;
import bp.BThread;
import bp.RunMode;

/**
 * The main entry point to the TicTacToe program.
 */
public class TicTacToe implements BApplication { 
	// GUI for interactively playing the game
	public static GUI playGUI;
	
	// Add GUI for watching the model-checking run. 
	public static TTTDisplay modelCheckingMonitorGUI;


	/**
	 * Main program entry point
	 * 
	 * @param args
	 *            Command line parameters (ignored)
	 */

	static public void main(String arg[]) {
		try {

			BProgram.startBApplication(TicTacToe.class, "tictactoe");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runBApplication() {
		
		if (globalRunMode == RunMode.MCSAFETY || globalRunMode == RunMode.MCLIVENESS) {

			// Start the MC GUI 
			modelCheckingMonitorGUI = new TTTDisplay(bp);

			bp.add(new UpdateMCMonitorGUI(), 0.6); 		
			bp.add(new XAllMoves(), 15.0);
			
		}else{

			// start the playing GUI
			playGUI = new GUI(bp);

			bp.add(new UpdatePlayingGUI(), 0.1); 
			bp.add(new UserMove(), 0.5);
		}
		
		// TTT B-Threads
		
		// SquareTaken bthread(s)
		double pr = 1.0;
		for (BThread sc : SquareTaken.constructInstances())
			bp.add(sc, pr += 0.001);

		bp.add(new EnforceTurns(), 2.0);
		
 		bp.add(new DeclareWinner(), 2.5);

		// DetectXWin bthread(s)
		pr = 3.0;
		for (BThread sc : DetectXWin.constructInstances())
			bp.add(sc, pr += 0.001);

		// DetectOWin bthread(s)
		pr = 4.0;
		for (BThread sc : DetectOWin.constructInstances())
			bp.add(sc, pr += 0.001);

		bp.add(new DetectDraw(), 5.0);

		bp.add(new DefaultOMoves(), 12.0);
		
		/*******************************************************/

		pr = 7;
		for (BThread sc : PreventThirdX.constructInstances())
			bp.add(sc, pr += 0.001);

 		// prevent corner-edge fork
 		pr = 8;
 		for (BThread sc : PreventXFork.constructInstances()){
 			bp.add(sc, pr += 0.001);
 		}

 		// prevent corner-corner fork
	   	bp.add(new PreventAnotherXFork(), 9.0);

 	   	// win if you can

 	   	
 	   	pr = 6;
 		for (BThread sc : AddThirdO.constructInstances())
 			bp.add(sc, pr += 0.001);
 	
 		
 	   	// prevent edge-edge fork
 		pr = 9.5;
 		for (BThread sc : PreventYetAnotherXFork.constructInstances()){ 
 			bp.add(sc, pr += 0.001);
 		}

		/*******************************************************/

		// Start the scenarios
		bp.startAll();
	}

}
