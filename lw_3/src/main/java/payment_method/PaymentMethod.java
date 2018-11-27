package payment_method;

public class PaymentMethod implements IPaymentMethod {

    private Method _method;

    public PaymentMethod(Method method) {
        this._method = method;
    }

    public Method getPaymentMethod() {
        return this._method;
    }

}
