
interface PaymentRepository {
    fun makePayment(paymentPin : String, amount : Double) : BaseResult<String, String>
}