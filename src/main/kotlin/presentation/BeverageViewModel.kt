package presentation

import common.data.BaseResult
import common.data.Beverage
import common.data.BeverageOrder
import domain.BeverageRepository
import observer.MachineStateObserver
import observer.Subject

class BeverageViewModel(private val factory: BeverageRepository) {

    val subject = Subject<BeverageMachineUiState>(BeverageMachineUiState.Loading)
    val observer : MachineStateObserver<BeverageMachineUiState> = MachineStateObserver(subject)

    fun getAllBeverage() {
        when(val response = factory.getAllBeverage()){
            is BaseResult.Success -> subject.state = BeverageMachineUiState.BeverageListSuccess(response.order)
            is BaseResult.Error -> subject.state = BeverageMachineUiState.Error(response.msg)
        }
    }

    fun prepareBeverageOrderBy(beverage: Beverage){
        when(val order = factory.prepareBeverage(beverage)){
            is BaseResult.Success -> subject.state = BeverageMachineUiState.BeverageOrderSuccess(order.order)
            is BaseResult.Error -> subject.state = BeverageMachineUiState.Error(order.msg)
        }

    }

}

sealed class BeverageMachineUiState{
    data object Loading : BeverageMachineUiState()
    data class BeverageOrderSuccess(val order: BeverageOrder) : BeverageMachineUiState()
    data class BeverageListSuccess(val listOrBeverage : List<Beverage>) : BeverageMachineUiState()
    data class Error(val msg : String) : BeverageMachineUiState()
}