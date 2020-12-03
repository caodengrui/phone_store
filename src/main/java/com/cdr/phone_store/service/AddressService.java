package com.cdr.phone_store.service;

import com.cdr.phone_store.form.AddressForm;
import com.cdr.phone_store.vo.AddressVO;

import java.util.List;

public interface AddressService {
    public List<AddressVO> findAll();

    public void saverOrUpdate(AddressForm addressForm);
}
