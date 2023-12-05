import common.BeverageRepositoryImpl
import common.ResourceServiceImpl

object ServiceLocator {

    fun getBeverageRepository() = BeverageRepositoryImpl(ResourceServiceImpl())

    fun getPaymentService() = PaymentRepositoryImpl()
}