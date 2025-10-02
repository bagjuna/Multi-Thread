package thread.executor.poolsize;

import static thread.executor.ExecutorUtils.*;
import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import thread.executor.RunnbleTask;

public class PoolSizeMainV1 {

	public static void main(String[] args) {
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);

		ExecutorService es = new ThreadPoolExecutor(2, 4,
			3000, TimeUnit.MILLISECONDS, workQueue);

		printState(es);

		es.execute(new RunnbleTask("task1"));
		printState(es, "task1");

		es.execute(new RunnbleTask("task2"));
		printState(es, "task2");

		es.execute(new RunnbleTask("task3"));
		printState(es, "task3");

		es.execute(new RunnbleTask("task4"));
		printState(es, "task4");

		es.execute(new RunnbleTask("task5"));
		printState(es, "task5");

		es.execute(new RunnbleTask("task6"));
		printState(es, "task6");

		try {

			es.execute(new RunnbleTask("task7"));
		} catch (RejectedExecutionException e) {
			System.out.println("task7 - 실행 거절 예외 발생: " + e);
		}

		sleep(3000);
		log("== 작업 수행 완료 ==");
		printState(es);

		sleep(3000);
		log("== MaximumPoolSize 대기 시간 초과 ==");
		printState(es);

		es.close();
		log("== showdown 완료 ==");
		printState(es);

	}
}
