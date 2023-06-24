package sample.cafekiosk.spring.api.service.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;
import sample.cafekiosk.spring.domain.stock.Stock;
import sample.cafekiosk.spring.domain.stock.StockRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {

	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	private final StockRepository stockRepository;

	public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
		List<String> productNumbers = request.getProductNumbers();
		List<Product> products = findProductsBy(productNumbers);

		// LocalDateTime.now()가 서비스 레이어에 있으면 테스트 하기 어려우므로 파라미터로 추출
		Order order = Order.create(products, registeredDateTime);
		Order savedOrder = orderRepository.save(order);

		// 재고 차감 체크가 필요한 상품들 filter
		List<String> stockProductNumbers = products.stream()
			.filter(product -> ProductType.containsStockType(product.getType()))
			.map(Product::getProductNumber)
			.collect(Collectors.toList());

		// 재고 엔티티 조회
		List<Stock> stocks = stockRepository.findAllByProductNumberIn(stockProductNumbers);
		Map<String, Stock> stockMap = stocks.stream()
			.collect(
				Collectors.toMap(Stock::getProductNumber, stock -> stock)); // 리스트를 그냥 순회하면 성능이 안 나올 수도 있으므로, map을 만든다.

		// 상품별 counting
		Map<String, Long> productCountingMap = stockProductNumbers.stream()
			.collect(Collectors.groupingBy(productNumber -> productNumber, Collectors.counting()));

		// 재고 차감시도
		for (String stockProductNumber : new HashSet<>(stockProductNumbers)) {
			Stock stock = stockMap.get(stockProductNumber);
			int quantity = productCountingMap.get(stockProductNumber).intValue();

			// stock.deductQuantity 에서도 예외처리를 하는데 여기서도 예외처리를 한 이유?
			// 👉 예외를 핸들링하는 방향이 다르다.
			if (stock.isQuantityLessThan(quantity)) {
				throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
			}
			stock.deductQuantity(quantity);
		}

		return OrderResponse.of(savedOrder);
	}

	private List<Product> findProductsBy(List<String> productNumbers) {
		// 중복이 제거된 프로덕트가 조회된다.
		List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

		// productNumber를 기반으로 Product를 빨리 찾을 수 있는 Map을 만든다.
		Map<String, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getProductNumber, product -> product));

		// productNumber -> Product 변환
		return productNumbers.stream()
			.map(productMap::get)
			.collect(Collectors.toList());
	}

}