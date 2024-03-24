package com.example.common.enums;

// '已取消', '待支付', '待发货', '待收货', '待评价', '已完成'
public enum OrderStatusEnum {
    CANCEL("已取消"),
    NO_PAY("待支付"),
    NO_SEND("待发货"),
    NO_RECEIVE("待收货"),
    NO_COMMENT("待评价"),
    MONEY_BACK("已退款"),
    DONE("已完成");

    private String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
