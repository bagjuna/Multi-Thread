package thread.executor;

import static thread.executor.ExecutorUtils.*;
import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorBasicMain {

	public static void main(String[] args) {
		ExecutorService es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
		log("== 초기 상태 ==");
		printState(es);
		es.execute(new RunnbleTask("taskA"));
		es.execute(new RunnbleTask("taskB"));
		es.execute(new RunnbleTask("taskC"));
		es.execute(new RunnbleTask("taskD"));
		log("== 작업 수행 중==");
		printState(es);

		sleep(3000);
		log("== 작업 수행 완료==");
		printState(es);

		es.close();
		log("== showdown 완료==");
		printState(es);

	}
}
