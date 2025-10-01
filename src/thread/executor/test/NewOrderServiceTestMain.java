package thread.executor.test;

import java.util.concurrent.ExecutionException;

public class NewOrderServiceTestMain {
	public static void main(String[] args) {
		String orderNo = "Order#1234"; // 예시 주문 번호
		NewOrderService orderService = new NewOrderService();
		try {
			orderService.order(orderNo);
		} catch (ExecutionException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
