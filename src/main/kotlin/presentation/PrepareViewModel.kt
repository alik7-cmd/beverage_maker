package presentation

import common.BeverageRepository
import common.data.BaseResult
import common.data.Beverage
import common.data.BeverageOrder
import observer.MachineStateObserver
import observer.Subject

class PrepareViewModel(private val factory: BeverageRepository) {
    private val subject = Subject<PrepareUiState>(PrepareUiState.Init)
    val observer : MachineStateObserver<PrepareUiState> = MachineStateObserver(subject)

    fun prepareBeverageOrderBy(beverage: Beverage){
        when(val order = factory.prepareBeverage(beverage)){
            is BaseResult.Success -> subject.state = PrepareUiState.BeverageOrderSuccess(order.order)
            is BaseResult.Error -> subject.state = PrepareUiState.Error(order.msg)
        }
    }
}

sealed class PrepareUiState{
    data object Init : PrepareUiState()

    data class BeverageOrderSuccess(val order: BeverageOrder) : PrepareUiState()

    data class Error(val msg : String) : PrepareUiState()
}