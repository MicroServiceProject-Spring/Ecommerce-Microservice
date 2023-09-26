package com.arman.order.service;

import com.arman.order.dto.InventoryResponse;
import com.arman.order.dto.OrderLineItemsDto;
import com.arman.order.dto.OrderRequest;
import com.arman.order.model.Order;
import com.arman.order.model.OrderLineItems;
import com.arman.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::getOrderLineItemFromOrderRequest).toList();
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = orderLineItems.stream().map(OrderLineItems::getSkuCode)
                .toList();


        InventoryResponse[] inventoryResponses = webClient.build().get()
                .uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        if(!Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock)){
            throw new IllegalArgumentException("Product is not in stock");
        }
        orderRepository.save(order);
        log.info("Order has been placed successfully");
    }

    private OrderLineItems getOrderLineItemFromOrderRequest(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
