package payment_method;

public interface IPaymentMethod {

    public enum Method {
        Cash,
        Card,
        Bonuses
    }

    Method getPaymentMethod();

    static Method getPaymentMethodByCode(int code)
    {
        return Method.values()[code];
    }
}
