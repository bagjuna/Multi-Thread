package thread.executor.poolsize;

import static thread.executor.ExecutorUtils.*;
import static thread.util.MyLogger.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import thread.executor.RunnbleTask;

public class PoolSizeMainV2 {

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(2);

		log("pool 생성");
		printState(es);

		for (int i = 1; i <= 6; i++) {
			String takeName = "task" + i;
			es.execute(new RunnbleTask(takeName));
			printState(es, takeName);
		}

		es.close();
		log("== showdown 완료 ==");


	}
}

