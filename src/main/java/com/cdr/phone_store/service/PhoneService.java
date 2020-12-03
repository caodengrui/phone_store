package com.cdr.phone_store.service;

import com.cdr.phone_store.vo.DataVO;
import com.cdr.phone_store.vo.PhoneInfoVO;
import com.cdr.phone_store.vo.SpecsPackageVO;

import java.util.List;

public interface PhoneService {
    public DataVO findDataVo();

    public List<PhoneInfoVO> findPhoneInfoVoByCategoryType(Integer categoryType);

    public SpecsPackageVO findSpecsByPhoneId(Integer phoneId);

    public void subStock(Integer specsId, Integer quantity);
}
