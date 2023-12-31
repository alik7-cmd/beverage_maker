
class PaymentRepositoryImpl : PaymentRepository {
    override fun makePayment(paymentPin: String, amount: Double): BaseResult<String, String> {
        return if(isValidCredentials(paymentPin, amount)){
            PaymentConstant.TOTAL_BALANCE-= amount
            BaseResult.Success("Payment Successful")
        }else{
            BaseResult.Error("Payment failed")
        }
    }

    private fun isValidCredentials(paymentPin: String, amount: Double) : Boolean{
        return paymentPin == "1234" && amount < PaymentConstant.TOTAL_BALANCE
    }
}