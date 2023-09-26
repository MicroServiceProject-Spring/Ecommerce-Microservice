package com.arman.order.service;


import com.arman.order.dto.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
