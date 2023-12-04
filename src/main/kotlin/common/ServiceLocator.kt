package common

import PaymentRepositoryImpl

object ServiceLocator {

    fun getBeverageRepository() = BeverageRepositoryImpl(ResourceServiceImpl())

    fun getPaymentService() = PaymentRepositoryImpl()
}