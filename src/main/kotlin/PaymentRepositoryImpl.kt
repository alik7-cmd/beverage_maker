import common.data.BaseResult

class PaymentRepositoryImpl : PaymentRepository {
    override fun makePayment(cardId: String, password: String): BaseResult<String, String> {
        return if(isValidCredentials(cardId, password)){
            BaseResult.Success("Payment Successful")
        }else{
            BaseResult.Error("Payment failed")
        }
    }

    private fun isValidCredentials(cardId: String, password: String) : Boolean{
        return cardId == "1234" && password == "1234"
    }
}