package com.cdr.phone_store.repository;

import com.cdr.phone_store.entity.PhoneCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneCategoryRepositoryTest {

    @Autowired
    private PhoneCategoryRepository phoneCategoryRepository;

    @Test
    void findAll() {
        List<PhoneCategory> list = phoneCategoryRepository.findAll();
        for (PhoneCategory phoneCategory : list) {
            System.out.println(phoneCategory);
        }
    }

    @Test
    void findByCategoryType() {
        PhoneCategory phoneCategory = phoneCategoryRepository.findByCategoryType(1);
        System.out.println(phoneCategory);
    }
}
