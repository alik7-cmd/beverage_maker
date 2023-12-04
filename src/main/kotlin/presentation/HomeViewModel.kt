package presentation

import common.data.BaseResult
import common.data.Beverage
import common.BeverageRepository
import observer.MachineStateObserver
import observer.Subject

class HomeViewModel(private val factory: BeverageRepository) {

    private val subject = Subject<BeverageMachineUiState>(BeverageMachineUiState.Init)
    val observer : MachineStateObserver<BeverageMachineUiState> = MachineStateObserver(subject)

    fun getAllBeverage() {
        when(val response = factory.getAllBeverage()){
            is BaseResult.Success -> subject.state = BeverageMachineUiState.BeverageListSuccess(response.order)
            is BaseResult.Error -> subject.state = BeverageMachineUiState.Error(response.msg)
        }
    }
}

sealed class BeverageMachineUiState{

    data object Init : BeverageMachineUiState()
    data class BeverageListSuccess(val listOrBeverage : List<Beverage>) : BeverageMachineUiState()
    data class Error(val msg : String) : BeverageMachineUiState()
}