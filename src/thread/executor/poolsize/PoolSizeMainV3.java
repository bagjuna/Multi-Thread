package thread.executor.poolsize;

import static thread.executor.ExecutorUtils.*;
import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import thread.executor.RunnbleTask;

public class PoolSizeMainV3 {

	public static void main(String[] args) {
		// ExecutorService es = Executors.newCachedThreadPool();
		ExecutorService es = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
			3, TimeUnit.SECONDS,
			new SynchronousQueue<>());
		log("pool 생성");
		printState(es);

		for (int i = 1; i <= 4; i++) {
			String takeName = "task" + i;
			es.execute(new RunnbleTask(takeName));
			printState(es, takeName);
		}

		sleep(3000);
		log("== 작업 수행 완료 ==");
		printState(es);

		sleep(3000);
		log("== MaximumPoolSize 대기 시간 초과 ==");
		printState(es);

		es.close();
		log("== showdown 완료 ==");


	}
}

