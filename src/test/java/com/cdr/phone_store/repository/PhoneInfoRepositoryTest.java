package com.cdr.phone_store.repository;

import com.cdr.phone_store.entity.PhoneInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneInfoRepositoryTest {
    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Test
    void findAll() {
        List<PhoneInfo> list = phoneInfoRepository.findAll();
        for (PhoneInfo phoneInfo : list) {
            System.out.println(phoneInfo);
        }
    }

    @Test
    void findAllByCategoryType() {
        List<PhoneInfo> list = phoneInfoRepository.findAllByCategoryType(1);
        for (PhoneInfo phoneInfo : list) {
            System.out.println(phoneInfo);
        }
    }

}
