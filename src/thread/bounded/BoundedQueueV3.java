package thread.bounded;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

import java.util.ArrayDeque;
import java.util.Queue;

public class BoundedQueueV3 implements BoundedQueue {

	private final Queue<String> queue = new ArrayDeque<>();
	private final int max;

	public BoundedQueueV3(int max) {
		this.max = max;
	}

	@Override
	public synchronized void put(String data) {
		while (queue.size() == max) {
			log("[put] 큐가 꽉 찼습니다 생산자 대기");
			try {
				wait();  // RUNNABLE -> WAITING 락 반납
				log("[put] 생산자 깨어남");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		queue.offer(data);
		log("[put] 생산자 데이터 저장, notify() 호출");
		notify(); // 대기 스레드, WAITING -> RUNNABLE
	}


	@Override
	public synchronized String take() {
		while (queue.isEmpty()) {
			log("[take] 큐가 대이터가 없음 소비자 대기");
			try {
				wait();
				log("[take] 소비자 깨어남");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		String data = queue.poll();
		log("[take] 소비자 데이터 획득, notify() 호출");
		notify(); // 대기스레드, WAIT-> BLOCKED
		return data;
	}

	@Override
	public String toString() {
		return queue.toString();
	}

}
