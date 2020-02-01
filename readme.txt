Example: TicTacToe

See example description in www.b-prog.org.

Run Configurations: copy VM Arguments below into run configuration definition in Eclipse.

Run configuration for running in playing mode:
Either no VM Arguments or:
-DrunMode=DET

Run configuration for model checking:
-DrunMode=MCSafety
-Dshallow=true
-noverify
 