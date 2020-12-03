package com.cdr.phone_store.repository;

import com.cdr.phone_store.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerAddressRepositoryTest {
    @Autowired
    private BuyerAddressRepository buyerAddressRepository;

    @Test
    void findAll() {
        List<BuyerAddress> list = buyerAddressRepository.findAll();
        for (BuyerAddress buyerAddress : list) {
            System.out.println(buyerAddress);
        }
    }

    @Test
    void save() {
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setAreaCode("330104");
        buyerAddress.setBuyerAddress("广东省深圳市罗湖区科技路123号456室");
        buyerAddress.setBuyerName("王小明");
        buyerAddress.setBuyerPhone("13634343434");
        buyerAddressRepository.save(buyerAddress);
    }

    @Test
    void update() {
        BuyerAddress buyerAddress = buyerAddressRepository.findById(36).get();
        buyerAddress.setBuyerName("小明");
        buyerAddressRepository.save(buyerAddress);
    }
}
