package presentation

import PaymentRepository
import common.data.BaseResult
import common.data.Beverage
import common.data.BeverageOrder
import common.BeverageRepository
import observer.MachineStateObserver
import observer.Subject

class BeverageViewModel(private val repository: BeverageRepository,
    private val paymentRepository: PaymentRepository) {

    private val subject = Subject<BeverageMachineUiState>(BeverageMachineUiState.Init)
    val observer: MachineStateObserver<BeverageMachineUiState> = MachineStateObserver(subject)

    fun makePayment(cardId : String, password : String){
        when(paymentRepository.makePayment(cardId, password)){
            is BaseResult.Success -> subject.state = BeverageMachineUiState.PaymentSuccess
            is BaseResult.Error -> subject.state = BeverageMachineUiState.PaymentFailed
        }
    }

    fun getAllBeverage() {
        when (val response = repository.getAllBeverage()) {
            is BaseResult.Success -> subject.state = BeverageMachineUiState.BeverageListSuccess(response.order)
            is BaseResult.Error -> subject.state = BeverageMachineUiState.Error(response.msg)
        }
    }

    fun prepareBeverageBy(
        beverage: Beverage,
        espresso: Int = 0,
        foam: Int = 0,
        steamedMilk: Int = 0,
        hotChocolate: Int = 0
    ) {
        when (val order = repository.prepareBeverage(beverage, espresso, foam, steamedMilk, hotChocolate)) {
            is BaseResult.Success -> subject.state = BeverageMachineUiState.BeverageOrderSuccess(order.order)
            is BaseResult.Error -> subject.state = BeverageMachineUiState.Error(order.msg)
        }
    }

}

sealed class BeverageMachineUiState {
    data object Init : BeverageMachineUiState()
    data object PaymentSuccess : BeverageMachineUiState()
    data object PaymentFailed : BeverageMachineUiState()
    data class BeverageOrderSuccess(val order: BeverageOrder) : BeverageMachineUiState()
    data class BeverageListSuccess(val listOrBeverage: List<Beverage>) : BeverageMachineUiState()
    data class Error(val msg: String) : BeverageMachineUiState()
}