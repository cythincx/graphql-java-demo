package com.chengyuxing.graphql.domain;

import java.math.BigDecimal;

/**
 * Created by mt-chengyuxing on 2016/12/29.
 * 商品
 */
public class GoodsDO {
    private Integer id;
    private String name;
    private String desc;
    /**单价*/
    private BigDecimal unitPrice;
    /**商品种类*/
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
