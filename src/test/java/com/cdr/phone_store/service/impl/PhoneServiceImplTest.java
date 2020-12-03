package com.cdr.phone_store.service.impl;

import com.cdr.phone_store.entity.PhoneInfo;
import com.cdr.phone_store.service.PhoneService;
import com.cdr.phone_store.vo.DataVO;
import com.cdr.phone_store.vo.PhoneInfoVO;
import com.cdr.phone_store.vo.SpecsPackageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneServiceImplTest {
    @Autowired
    private PhoneService phoneService;

    @Test
    void findDataVO() {
        DataVO dataVO = phoneService.findDataVo();
        int id = 0;
    }

    @Test
    void findPhoneInfoByCategoryType() {
        List<PhoneInfoVO> list = phoneService.findPhoneInfoVoByCategoryType(2);
        int id = 0;
    }


    @Test
    void findSku() {
        SpecsPackageVO specsPackageVO = phoneService.findSpecsByPhoneId(1);
        int id = 0;
    }

    @Test
    void subStock() {
        phoneService.subStock(1, 3);
    }

}
