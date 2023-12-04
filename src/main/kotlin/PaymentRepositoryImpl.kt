import common.data.BaseResult

class PaymentRepositoryImpl : PaymentRepository {
    override fun makePayment(paymentPin: String, amount: Double): BaseResult<String, String> {
        return if(isValidCredentials(paymentPin, amount)){
            BaseResult.Success("Payment Successful")
        }else{
            BaseResult.Error("Payment failed")
        }
    }

    private fun isValidCredentials(paymentPin: String, amount: Double) : Boolean{
        return true
    }
}