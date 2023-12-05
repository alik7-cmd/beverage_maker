package presentation

import PaymentRepository
import common.data.Beverage
import common.data.BeverageOrder
import common.BeverageRepository
import observer.MachineStateObserver
import observer.Subject

class BeverageViewModel(private val repository: BeverageRepository,
    private val paymentRepository: PaymentRepository) {

    private val subject = Subject<BeverageMachineUiState>(BeverageMachineUiState.Init)
    val observer: MachineStateObserver<BeverageMachineUiState> = MachineStateObserver(subject)

    fun makePayment(paymentPin : String, amount : Double){
        when(paymentRepository.makePayment(paymentPin, amount)){
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

    fun prepareBeverage(
        beverage: Beverage,
        espresso: Int = 0,
        foam: Int = 0,
        steamedMilk: Int = 0,
        hotChocolate: Int = 0
    ) {
        when (val order = repository.prepareBeverage(beverage, espresso, foam, steamedMilk, hotChocolate)) {
            is BaseResult.Success -> subject.state = BeverageMachineUiState.BeverageOrderCreateSuccess(order.order)
            is BaseResult.Error -> subject.state = BeverageMachineUiState.Error(order.msg)
        }
    }

    fun sendResponseToMachine(){

    }

}

sealed class BeverageMachineUiState {
    data object Init : BeverageMachineUiState()
    data object PaymentSuccess : BeverageMachineUiState()
    data object PaymentFailed : BeverageMachineUiState()
    data class BeverageOrderCreateSuccess(val order: BeverageOrder) : BeverageMachineUiState()
    data class BeverageListSuccess(val listOrBeverage: List<Beverage>) : BeverageMachineUiState()
    data class Error(val msg: String) : BeverageMachineUiState()
}