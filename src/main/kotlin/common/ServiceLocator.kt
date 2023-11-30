package common

object ServiceLocator {

    fun getBeverageRepository() = BeverageRepositoryImpl(ResourceServiceImpl())
}