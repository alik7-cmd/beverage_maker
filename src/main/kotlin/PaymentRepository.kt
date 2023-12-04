import common.data.BaseResult

interface PaymentRepository {

    fun makePayment(paymentPin : String, amount : Double) : BaseResult<String, String>
}