package com.cdr.phone_store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

@Data
public class PhoneSpecsCasVo {
    @JsonProperty("s1")
    private Integer specsId;
    @JsonProperty("price")
    private BigDecimal specsPrice;
    @JsonProperty("stock_num")
    private Integer specsStock;

}
