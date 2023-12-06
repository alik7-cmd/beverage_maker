package presentation

import BaseResult
import BroadcastRepository
import PaymentRepository
import common.data.Beverage
import common.data.BeverageOrder
import common.BeverageRepository
import observer.MachineStateObserver
import observer.Subject

class BeverageViewModel(private val beverageRepository: BeverageRepository,
                        private val paymentRepository: PaymentRepository,
                        private val broadcastRepository: BroadcastRepository) {

    lateinit var beverage: Beverage
    lateinit var order : BeverageOrder
    lateinit var broadcastingContent : String

    private val subject = Subject<BeverageMachineUiState>(BeverageMachineUiState.Init)
    val observer: MachineStateObserver<BeverageMachineUiState> = MachineStateObserver(subject)

    init {
        getBroadcastingContent()
    }

    fun getBroadcastingContent(){
        when(val broadcastContent = broadcastRepository.getBroadcastContent()){
            is BaseResult.Success -> subject.state = BeverageMachineUiState.BroadcastContentSuccess(broadcastContent.content)
            is BaseResult.Error -> subject.state = BeverageMachineUiState.Error("")
        }

    }

    fun makePayment(paymentPin : String, amount : Double){
        when(paymentRepository.makePayment(paymentPin, amount)){
            is BaseResult.Success -> subject.state = BeverageMachineUiState.PaymentSuccess
            is BaseResult.Error -> subject.state = BeverageMachineUiState.PaymentFailed
        }
    }

    fun getAllBeverage() {
        when (val response = beverageRepository.getAllBeverage()) {
            is BaseResult.Success -> subject.state = BeverageMachineUiState.BeverageListSuccess(response.content)
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
        when (val order = beverageRepository.prepareBeverage(beverage, espresso, foam, steamedMilk, hotChocolate)) {
            is BaseResult.Success -> subject.state = BeverageMachineUiState.BeverageOrderCreateSuccess(order.content)
            is BaseResult.Error -> subject.state = BeverageMachineUiState.Error(order.msg)
        }
    }

    fun sendFinalOrderToMachine(){
        subject.state = BeverageMachineUiState.BeverageOrderSendSuccess
    }

}

sealed class BeverageMachineUiState {
    data object Init : BeverageMachineUiState()
    data class BroadcastContentSuccess(val broadcastContent : String) :  BeverageMachineUiState()
    data object PaymentSuccess : BeverageMachineUiState()
    data object PaymentFailed : BeverageMachineUiState()
    data class BeverageOrderCreateSuccess(val order: BeverageOrder) : BeverageMachineUiState()
    data class BeverageListSuccess(val listOrBeverage: List<Beverage>) : BeverageMachineUiState()
    data object BeverageOrderSendSuccess : BeverageMachineUiState()
    data class Error(val msg: String) : BeverageMachineUiState()
}