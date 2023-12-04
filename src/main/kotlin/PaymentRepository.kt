import common.data.BaseResult

interface PaymentRepository {

    fun makePayment(cardId : String, password : String) : BaseResult<String, String>
}