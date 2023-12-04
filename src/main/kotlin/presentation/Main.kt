package presentation

import common.data.Beverage
import common.ServiceLocator

private lateinit var beverage: Beverage
fun main() {
    val viewModel = BeverageViewModel(ServiceLocator.getBeverageRepository())
    viewModel.getAllBeverage()
    observe(viewModel.observer.subject.state)
    viewModel.prepareBeverageOrderBy(beverage)
    observe(viewModel.observer.subject.state)
}

private fun observe(state: BeverageMachineUiState){
    when(state){
        is BeverageMachineUiState.BeverageListSuccess -> {
            state.listOrBeverage.forEach {
                println("The name of the beverage is ${it.name} and price is ${it.price}")
            }
            println()
            beverage = state.listOrBeverage[1]
        }

        is BeverageMachineUiState.BeverageOrderSuccess ->{
            println("Please collect your ${state.order.name}")
            while (state.order.decorator.hasNext()){
                val decoratorItem = state.order.decorator.next()
                println("${decoratorItem.first} --> ${decoratorItem.second}" )
            }
        }

        is BeverageMachineUiState.Error ->{
            println(state.msg)
        }
        BeverageMachineUiState.Loading -> {}
    }
}
