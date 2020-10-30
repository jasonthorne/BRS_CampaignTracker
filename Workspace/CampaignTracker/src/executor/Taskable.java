package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface Taskable {
	
	static ExecutorService singleThreadExec = Executors.newSingleThreadExecutor();
	
	//force shutdown of executor:
	abstract void shutDownExec();
}
