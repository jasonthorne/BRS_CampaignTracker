package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** to create tasks for operations needing to be kept separate from JavaFX application thread */

public interface Taskable {

	//single thread executor:
	static ExecutorService singleThreadExec = null;
	
	//force shutdown of executor:
	abstract void shutDownExec();
}