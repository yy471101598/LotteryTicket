package com.lottery.bossex.bean;

public class OrderMsg {
    public String lotteryId;// "1",
    public String aheadLotteryNo;// "21,32,32,12",    //前面的数字
    public String behindLotteryNo;// "32",            //后面的数字
    public String orderId;// "201901161900589749",    //订单号
    public String totalPrice;// "6.00",               //投入的总金额
    public String bonus;// "0.00",                    //获得奖金
    public String resultStatus;// "pending"           // 状态
}
