package thread.cas.SpinLockBad;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLock {
	private final AtomicBoolean lock = new AtomicBoolean(false);

	public void lock() {
		log("락 획득 시도");
		while (!lock.compareAndSet(false, true)) {
			// 락을 획득 할 때 까지 스핀 대기(바쁜 대기) 한다.
			log("락 획득 실패 - 스핀 대기");
			sleep(1);  // 오래 걸리는 로직에서 스핀락 사용 x
		}
		log("락 획득 성공");

	}


	public void unlock() {
		lock.set(false);
		log("락 반납 완료");
	}

}
