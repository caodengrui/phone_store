package com.cdr.phone_store.service.impl;

import com.cdr.phone_store.dto.OrderDTO;
import com.cdr.phone_store.entity.OrderMaster;
import com.cdr.phone_store.entity.PhoneInfo;
import com.cdr.phone_store.entity.PhoneSpecs;
import com.cdr.phone_store.enums.PayStatusEnum;
import com.cdr.phone_store.enums.ResultEnum;
import com.cdr.phone_store.exception.PhoneException;
import com.cdr.phone_store.repository.OrderMasterRepository;
import com.cdr.phone_store.repository.PhoneInfoRepository;
import com.cdr.phone_store.repository.PhoneSpecsRepository;
import com.cdr.phone_store.service.OrderService;
import com.cdr.phone_store.service.PhoneService;
import com.cdr.phone_store.util.KeyUtil;
import com.cdr.phone_store.vo.OrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PhoneService phoneService;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(orderDTO.getSpecsId()).get();
        if (phoneSpecs == null) {
            log.error("【创建订单】 规格不存在,phoneSpecs={}", phoneSpecs);
            throw new PhoneException(ResultEnum.SPECS_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneSpecs, orderMaster);

        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        if (phoneInfo == null) {
            log.error("【创建订单】 手机不存在,phoneInfo={}", phoneInfo);
            throw new PhoneException(ResultEnum.PHONE_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneInfo, orderMaster);

        //计算总价
        BigDecimal orderAmount = new BigDecimal(0);
        orderAmount = phoneSpecs.getSpecsPrice().divide(new BigDecimal(100))
                .multiply(new BigDecimal(orderDTO.getPhoneQuantity()))
                .add(orderAmount)
                .add(new BigDecimal(10));
        orderMaster.setOrderAmount(orderAmount);

        //orderId
        orderMaster.setOrderId(KeyUtil.createUniqueKey());
        orderDTO.setOrderId(orderMaster.getOrderId());

        //payStatus
        orderMaster.setPayStatus(PayStatusEnum.UNPAYED.getCode());
        orderMasterRepository.save(orderMaster);

        phoneService.subStock(orderDTO.getSpecsId(), orderDTO.getPhoneQuantity());
        return orderDTO;
    }

    @Override
    public OrderDetailVO findOrderDetailByOrderId(String orderId) {
        OrderDetailVO orderDetailVO = new OrderDetailVO();

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            log.error("【支付订单】 订单不存在,orderMaster={}", orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster, orderDetailVO);
        orderDetailVO.setSpecsPrice(orderMaster.getSpecsPrice().divide(new BigDecimal(100)) + ".00");

        return orderDetailVO;
    }

    @Override
    public String pay(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            log.error("【支付订单】 订单不存在,orderMaster={}", orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }

        if (orderMaster.getPayStatus().equals(PayStatusEnum.UNPAYED.getCode())) {
            orderMaster.setPayStatus(PayStatusEnum.PAYED.getCode());
            orderMasterRepository.save(orderMaster);
        } else {
            log.error("[支付订单] 订单已支付，orderMaster{}", orderMaster);
        }

        return orderId;
    }
}
