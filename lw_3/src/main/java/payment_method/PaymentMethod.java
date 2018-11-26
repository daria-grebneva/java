package payment_method;

public class PaymentMethod implements IPaymentMethod {

    public PaymentMethod(Method method) {
        this._method = method;
    }

    public Method getPaymentMethod() {
        return this._method;
    }

    private Method _method;
}
