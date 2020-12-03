package com.cdr.phone_store.service.impl;

import com.cdr.phone_store.form.AddressForm;
import com.cdr.phone_store.service.AddressService;
import com.cdr.phone_store.vo.AddressVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {
    @Autowired
    private AddressService addressService;

    @Test
    void findAll() {
        List<AddressVO> list = addressService.findAll();
        int id = 0;
    }

    @Test
    void saveOfUpdate() {
        AddressForm addressForm = new AddressForm();
        addressForm.setId(37);
        addressForm.setName("李旺思");
        addressForm.setTel("13678900987");
        addressForm.setAreaCode("110101");
        addressForm.setAddressDetail("168号305室");
        addressForm.setCity("北京市");
        addressForm.setCounty("东城区");
        addressForm.setProvince("北京市");
        addressService.saverOrUpdate(addressForm);
    }
}
