package com.cdr.phone_store.controller;

import com.cdr.phone_store.service.PhoneService;
import com.cdr.phone_store.util.ResultVOUtil;
import com.cdr.phone_store.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/index")
    public ResultVO index() {
        return ResultVOUtil.success(phoneService.findDataVo());
    }

    @GetMapping("/findByCategoryType/{categoryType}")
    public ResultVO findByCategoryType(@PathVariable Integer categoryType) {
        return ResultVOUtil.success(phoneService.findPhoneInfoVoByCategoryType(categoryType));
    }

    @GetMapping("/findSpecsByPhoneId/{phoneId}")
    public ResultVO findSpecsByPhoneId(@PathVariable Integer phoneId) {
        return ResultVOUtil.success(phoneService.findSpecsByPhoneId(phoneId));

    }
}
