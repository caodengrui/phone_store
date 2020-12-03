package com.cdr.phone_store.service.impl;

import com.cdr.phone_store.entity.PhoneCategory;
import com.cdr.phone_store.entity.PhoneInfo;
import com.cdr.phone_store.entity.PhoneSpecs;
import com.cdr.phone_store.enums.ResultEnum;
import com.cdr.phone_store.exception.PhoneException;
import com.cdr.phone_store.repository.PhoneCategoryRepository;
import com.cdr.phone_store.repository.PhoneInfoRepository;
import com.cdr.phone_store.repository.PhoneSpecsRepository;
import com.cdr.phone_store.service.PhoneService;
import com.cdr.phone_store.util.PhoneUtil;
import com.cdr.phone_store.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneCategoryRepository phoneCategoryRepository;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    @Override
    public DataVO findDataVo() {
        DataVO dataVO = new DataVO();
        //类型categories
        List<PhoneCategory> phoneCategoryList = phoneCategoryRepository.findAll();

        //常规写法
        /*List<PhoneCategoryVO> phoneCategoryVOList = new ArrayList<>();
        for (PhoneCategory phoneCategory : phoneCategoryList){
            PhoneCategoryVO phoneCategoryVO = new PhoneCategoryVO();
            phoneCategoryVO.setCategoryName(phoneCategory.getCategoryName());
            phoneCategoryVO.setCategoryType(phoneCategory.getCategoryType());
            phoneCategoryVOList.add(phoneCategoryVO);
        }*/

        //stream (jdk8)
        List<PhoneCategoryVO> phoneCategoryVOList = phoneCategoryList.stream()
                .map(e -> new PhoneCategoryVO(
                        e.getCategoryName(),
                        e.getCategoryType()
                )).collect(Collectors.toList());
        dataVO.setCategories(phoneCategoryVOList);

        //手机
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByCategoryType(phoneCategoryList.get(0).getCategoryType());
        //常规写法
       /*  List<PhoneInfoVO> phoneInfoVOList = new ArrayList<>();
         for (PhoneInfo phoneInfo : phoneInfoList){
            PhoneInfoVO phoneInfoVO = new PhoneInfoVO();
            BeanUtils.copyProperties(phoneInfo,phoneInfoVO);
            phoneInfoVO.setTag(PhoneUtil.createTag(phoneInfo.getPhoneTag()));
            phoneInfoVOList.add(phoneInfoVO);
        }*/

        //stream
        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice() + ".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());
        dataVO.setPhones(phoneInfoVOList);
        return dataVO;
    }

    @Override
    public List<PhoneInfoVO> findPhoneInfoVoByCategoryType(Integer categoryType) {
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByCategoryType(categoryType);
        //stream
        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice() + ".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());
        return phoneInfoVOList;
    }

    @Override
    public SpecsPackageVO findSpecsByPhoneId(Integer phoneId) {

        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneId).get();
        List<PhoneSpecs> phoneSpecsList = phoneSpecsRepository.findAllByPhoneId(phoneId);

        //tree
        List<PhoneSpecsVO> phoneSpecsVOList = new ArrayList<>();
        List<PhoneSpecsCasVo> phoneSpecsCasVoList = new ArrayList<>();
        PhoneSpecsCasVo phoneSpecsCasVo;
        PhoneSpecsVO phoneSpecsVO;
        for (PhoneSpecs phoneSpecs : phoneSpecsList) {
            phoneSpecsVO = new PhoneSpecsVO();
            phoneSpecsCasVo = new PhoneSpecsCasVo();
            BeanUtils.copyProperties(phoneSpecs, phoneSpecsVO);
            BeanUtils.copyProperties(phoneSpecs, phoneSpecsCasVo);
            phoneSpecsVOList.add(phoneSpecsVO);
            phoneSpecsCasVoList.add(phoneSpecsCasVo);
        }
        TreeVO treeVO = new TreeVO();
        treeVO.setV(phoneSpecsVOList);
        List<TreeVO> treeVOList = new ArrayList<>();
        treeVOList.add(treeVO);

        SkuVO skuVO = new SkuVO();
        Integer price = phoneInfo.getPhonePrice().intValue();
        skuVO.setPrice(price + ".00");
        skuVO.setStock_num(phoneInfo.getPhoneStock());
        skuVO.setTree(treeVOList);
        skuVO.setList(phoneSpecsCasVoList);

        SpecsPackageVO specsPackageVO = new SpecsPackageVO();
        specsPackageVO.setSku(skuVO);
        Map<String, String> goods = new HashMap<>();
        goods.put("picture", phoneInfo.getPhoneIcon());
        specsPackageVO.setGoods(goods);
        return specsPackageVO;
    }

    @Override
    public void subStock(Integer specsId, Integer quantity) {
        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(specsId).get();
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        Integer result = phoneSpecs.getSpecsStock() - quantity;
        if (result < 0) {
            log.error("【扣库存】 库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }

        phoneSpecs.setSpecsStock(result);
        phoneSpecsRepository.save(phoneSpecs);

        result = phoneInfo.getPhoneStock() - quantity;
        if (result < 0) {
            log.error("【扣库存】 库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }

        phoneInfo.setPhoneStock(result);
        phoneInfoRepository.save(phoneInfo);
    }
}
