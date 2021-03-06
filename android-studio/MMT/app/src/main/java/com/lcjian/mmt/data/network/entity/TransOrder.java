package com.lcjian.mmt.data.network.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TransOrder implements Serializable {

    public String id; // 运输订单ID
    public String tranOrderCode; // 运单号
    public String tranStatus;
    @SerializedName("products")
    public Product product;
    public Integer quantity; // 数量(基础单位*1000)
    public Integer quantityDiff;
    public Double amount; // 总运费（分）

    // for detail
    public Long createDate;
    public String commTax; // 佣金收取方式（相对值单位%,绝对值单位分）
    public Double commAmount; // 佣金(分)
    public Store unloadStore; // 卸货仓库

}
