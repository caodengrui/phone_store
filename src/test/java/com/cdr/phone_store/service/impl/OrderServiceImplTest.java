package com.cdr.phone_store.service.impl;

import com.cdr.phone_store.dto.OrderDTO;
import com.cdr.phone_store.service.OrderService;
import com.cdr.phone_store.vo.OrderDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("王二麻子");
        orderDTO.setBuyerAddress("广东省深圳市罗湖区科技路123号456室");
        orderDTO.setBuyerPhone("13678787878");
        orderDTO.setSpecsId(1);
        orderDTO.setPhoneQuantity(1);

        OrderDTO result = orderService.create(orderDTO);
        System.out.println(result);
    }

    @Test
    void findDetail() {
        OrderDetailVO orderDetailVO = orderService.findOrderDetailByOrderId("1594119914203505121");
        int id = 10;
    }

    @Test
    void pay() {
        orderService.pay("1594119914203505121");
    }
}
