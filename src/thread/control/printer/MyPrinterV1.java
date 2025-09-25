package thread.control.printer;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MyPrinterV1 {

	public static void main(String[] args) {
		Printer printer = new Printer();
		Thread printerThread = new Thread(printer, "printer");
		printerThread.start();

		Scanner userInput = new Scanner(System.in);
		while (true) {
			log("출력할 문서를 입력하세요 (종료하려면 'exit' 입력): ");
			String input = userInput.nextLine();

			if (input.equals("q")) {
				printer.work = false; // 작업 중지 요청
				break;
			}

			printer.addJob(input); // 작업 큐에 문서 추가

		}
	}

	static class Printer implements Runnable {
		volatile boolean work = true;
		Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

		@Override
		public void run() {
			while (work) {
				if (jobQueue.isEmpty()) {
					continue;
				}

				String job = jobQueue.poll();// 작업 큐에서 작업 제거
				log("출력 시작: " + job + ", 대기 문서 = " + jobQueue);
				sleep(3000); // 3초 대기 (출력 작업 시뮬레이션)
				log("출력 완료");
			}

		}
		public void addJob(String input) {
			jobQueue.offer(input); // 작업 큐에 작업 추가
		}

	}
}
