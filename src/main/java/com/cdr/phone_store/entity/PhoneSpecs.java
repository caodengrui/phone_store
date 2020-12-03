package com.cdr.phone_store.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class PhoneSpecs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specsId;
    private Integer phoneId;
    private String SpecsName;
    private Integer specsStock;
    private BigDecimal specsPrice;
    private String specsIcon;
    private String specsPreview;
    private Date createTime;
    private Date updateTime;
}


