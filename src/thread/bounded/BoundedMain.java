package thread.bounded;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

import java.util.ArrayList;
import java.util.List;

public class BoundedMain {
	public static void main(String[] args) {

		//1. BoundedQueue 선택
		BoundedQueue queue = new BoundedQueueV1(2);

		// 2. 생산자, 소비자 실행 순서 선택, 반드시 하나만 선택!
		// producerFirst(queue);  // 생산자 먼저 실행
		consumerFirst(queue);  // 소비자 먼저 실행
	}

	private static void consumerFirst(BoundedQueue queue) {
		log("== [소비자 먼저 실행], " + queue.getClass().getSimpleName() + " ==");
		List<Thread> threads = new ArrayList<>();
		startConsumer(queue, threads);
		printAllState(queue, threads);
		startProducer(queue, threads);
		printAllState(queue, threads);
		log("== [소비자 먼저 실행] 종료 =="+ queue.getClass().getSimpleName() + " ==");
	}

	private static void producerFirst(BoundedQueue queue) {
		log("== [생산자 먼저 실행], " + queue.getClass().getSimpleName() + " ==");
		List<Thread> threads = new ArrayList<>();
		startProducer(queue, threads);
		printAllState(queue, threads);
		startConsumer(queue, threads);
		printAllState(queue, threads);
		log("== [생산자 먼저 실행] 종료 =="+ queue.getClass().getSimpleName() + " ==");
	}

	private static void startProducer(BoundedQueue queue, List<Thread> threads) {
		System.out.println();
		log("생상자 시작");
		for (int i = 1; i <= 3; i++) {
			Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
			threads.add(producer);
			producer.start();
			sleep(100);
		}
	}

	private static void startConsumer(BoundedQueue queue, List<Thread> threads) {
		System.out.println();
		log("소비자 시작");
		for (int i = 1; i <= 3; i++) {
			Thread thread = new Thread(new ConsumerTask(queue), "consumer" + i);
			threads.add(thread);
			thread.start();
			sleep(100);
		}
	}

	private static void printAllState(BoundedQueue queue, List<Thread> threads) {
		System.out.println();
		log("모든 스레드 상태 출력");
		for (Thread thread : threads) {
			log(thread.getName() + " : " + thread.getState());
		}
	}
}
