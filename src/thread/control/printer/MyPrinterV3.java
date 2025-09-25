package thread.control.printer;

import static thread.util.MyLogger.*;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MyPrinterV3 {

	public static void main(String[] args) {
		Printer printer = new Printer();
		Thread printerThread = new Thread(printer, "printer");
		printerThread.start();

		Scanner userInput = new Scanner(System.in);
		while (true) {
			log("출력할 문서를 입력하세요 (종료하려면 'q' 입력): ");
			String input = userInput.nextLine();

			if (input.equals("q")) {
				printerThread.interrupt();
				break;
			}

			printer.addJob(input); // 작업 큐에 문서 추가

		}
	}

	static class Printer implements Runnable {
		Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				if (jobQueue.isEmpty()) {
					continue;
				}

				try {
					String job = jobQueue.poll();// 작업 큐에서 작업 제거
					log("출력 시작: " + job + ", 대기 문서 = " + jobQueue);
					Thread.sleep(3000);
					log("출력 완료");
				} catch (InterruptedException e) {
					log("인터럽트!");
					break;
				}
			}
			log("프린터 종료");

		}

		public void addJob(String input) {
			jobQueue.offer(input); // 작업 큐에 작업 추가
		}

	}
}
