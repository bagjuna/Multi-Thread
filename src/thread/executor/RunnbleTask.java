package thread.executor;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

public class RunnbleTask implements Runnable {

	private final String name;
	private int sleepMs = 1000;

	public RunnbleTask(String name) {
		this.name = name;
	}

	public RunnbleTask(String name, int sleepMs) {
		this.name = name;
		this.sleepMs = sleepMs;
	}

	@Override
	public void run() {
		log(name + " 시작");
		sleep(sleepMs); // 작업 시간 시뮬레이션
		log(name + " 완료");
	}
}
