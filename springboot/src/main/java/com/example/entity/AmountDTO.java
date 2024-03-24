package com.example.entity;

import java.math.BigDecimal;

public class AmountDTO {
    // 总价
    private BigDecimal amount;
    // 优惠金额
    private BigDecimal discount;
    // 实际价格
    private BigDecimal actual;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }
}
