package sample.cafekiosk.spring.api.controller.order.request;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

	private List<String> productNumbers;

	@Builder // 나중에 어떤 필드가 추가될지 모르므로 빌더를 열어둔다.
	private OrderCreateRequest(List<String> productNumbers) {
		this.productNumbers = productNumbers;
	}

}

