package com.javacourse2018.lw03.payment_method;

public interface IPaymentMethod {

    Method getPaymentMethod();

    static Method getPaymentMethodByCode(int code)
    {
        return Method.values()[code];
    }
}
