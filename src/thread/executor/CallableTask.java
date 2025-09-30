package thread.executor;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {

	private final String name;
	private int sleepMs = 1000;

	public CallableTask(String name, int sleepMs) {
		this.name = name;
		this.sleepMs = sleepMs;
	}

	@Override
	public Integer call() throws Exception {
		log("name = " + name + " 실행");
		sleep(sleepMs);
		log("name = " + name + " 완료");
		return sleepMs;
	}
}
