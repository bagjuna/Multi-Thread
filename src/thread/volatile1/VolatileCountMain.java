package thread.volatile1;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

public class VolatileCountMain {

	public static void main(String[] args) {
		MyTask task = new MyTask();
		Thread t = new Thread(task);
		t.start();

		sleep(1000); // 1초 대기

		task.flag = false;
		log("flag = " + task.flag + ", count = " + task.count + " im main");
	}

	static class MyTask implements Runnable {

		// boolean flag = true; // 일반 변수로 선언
		// long count = 0; // 카운트 변수
		//
		volatile boolean flag = true; // 일반 변수로 선언
		volatile long count = 0; // 카운트 변수

		@Override
		public void run() {
			while(flag) {
				count++; // 카운트 증가
				// 1억번에 한번씩 출력
				if (count % 100_000_000 == 0) {
					log("flag = " + flag + ", count = " + count+" in while()");
				}
			}
			log("flag = " + flag + ", count = " + count + " 종료");
		}
	}
}
