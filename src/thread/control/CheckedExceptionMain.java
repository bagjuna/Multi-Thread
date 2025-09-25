package thread.control;

public class CheckedExceptionMain {

	public static void main(String[] args) throws Exception {
		throw new Exception();
	}

	static class CheckedRunnable implements Runnable {
		@Override
		public void run() {
				// throw new RuntimeException(e); // 주석 풀면 예외 발생
		}


	}
}
