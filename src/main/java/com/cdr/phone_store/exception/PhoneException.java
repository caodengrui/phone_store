package com.cdr.phone_store.exception;

import com.cdr.phone_store.enums.ResultEnum;

public class PhoneException extends RuntimeException {

    public PhoneException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }

    public PhoneException(String error) {
        super(error);
    }
}
