package com.cdr.phone_store.repository;

import com.cdr.phone_store.entity.OrderMaster;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Test
    void findAll() {
        List<OrderMaster> list = orderMasterRepository.findAll();
        for (OrderMaster orderMaster : list) {
            System.out.println(orderMaster);
        }
    }

    @Test
    void findById() {
        OrderMaster orderMaster = orderMasterRepository.findById("123456").get();
        System.out.println(orderMaster);
    }

    @Test
    void pay() {
        OrderMaster orderMaster = orderMasterRepository.findById("123456").get();
        orderMaster.setPayStatus(1);
        orderMasterRepository.save(orderMaster);
    }

    @Test
    void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerAddress("广东省深圳市罗湖区科技路123号456室");
        orderMaster.setBuyerPhone("13678787878");
        orderMaster.setOrderAmount(new BigDecimal(6400));
        orderMaster.setPayStatus(0);
        orderMaster.setPhoneIcon("");
        orderMaster.setPhoneId(1);
        orderMaster.setPhoneName("Honor 8A");
        orderMaster.setPhoneQuantity(2);
        orderMaster.setSpecsId(1);
        orderMaster.setSpecsName("32GB");
        orderMaster.setSpecsPrice(new BigDecimal(320000));
        orderMasterRepository.save(orderMaster);
    }
}
