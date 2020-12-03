package com.cdr.phone_store.service;

import com.cdr.phone_store.dto.OrderDTO;
import com.cdr.phone_store.vo.OrderDetailVO;

public interface OrderService {
    public OrderDTO create(OrderDTO orderDTO);

    public OrderDetailVO findOrderDetailByOrderId(String orderId);

    public String pay(String orderId);
}
