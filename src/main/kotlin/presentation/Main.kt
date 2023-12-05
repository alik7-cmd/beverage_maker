package presentation

import ServiceLocator
import common.data.BeverageType
import common.data.CoffeeType
import java.util.*
import kotlin.system.exitProcess


val input = Scanner(System.`in`)
var milk = 0
var chocolate = 0
var foam = 0
var espresso = 0
fun main() {

    val viewModel = BeverageViewModel(
        ServiceLocator.getBeverageRepository(),
        ServiceLocator.getPaymentService())
    viewModel.getAllBeverage()
    observe(viewModel)

    when(viewModel.beverage.type){
        BeverageType.COFFEE ->{
            println("Please select your options on your ${viewModel.beverage.name}")

            print("Select Espresso shot(1-5): ")
            espresso = input.nextInt()

            print("Select Milk(1-5): ")
            milk = input.nextInt()
            chocolate = 0
            foam = 0

            when(viewModel.beverage.coffeeType){
                CoffeeType.MOCHA ->{
                    print("Select Chocolate(1-5): ")
                    chocolate = input.nextInt()
                }

                CoffeeType.CAPPUCCINO, CoffeeType.LATTE ->{
                    print("Select Foam(1-5): ")
                    foam = input.nextInt()
                }

                else -> {}
            }
        }
        else -> {}
    }

    /*println("Enter payment pin: ")
    val paymentPin = input.nextInt().toString()
    viewModel.makePayment(paymentPin, beverage.price)*/
    viewModel.prepareBeverage(viewModel.beverage, espresso, foam, milk, chocolate)
    println()
    observe(viewModel)
    observe(viewModel)
}

private fun observe(viewModel: BeverageViewModel){
    when(val state = viewModel.observer.subject.state){
        is BeverageMachineUiState.BeverageListSuccess -> {
            val emoji = "\uD83C\uDF5C"
            println("-------------------")
            state.listOrBeverage.forEachIndexed { index, beverage ->
                println(index+1)
                println("Name: ${beverage.name} \nPrice: ${beverage.price} $emoji")
                println("-------------------")
            }
            println()
            print("Please select your beverage: ")
            val index = input.nextInt()
            viewModel.beverage = state.listOrBeverage[index-1]
        }

        is BeverageMachineUiState.BeverageOrderCreateSuccess ->{
            viewModel.order = state.order
            println("Enter payment pin: ")
            val paymentPin = input.nextInt().toString()
            viewModel.makePayment(paymentPin, viewModel.order.price)
        }

        is BeverageMachineUiState.Error ->{
            println(state.msg)
        }
        BeverageMachineUiState.Init -> {}
        BeverageMachineUiState.PaymentFailed -> {
            println("Payment failed")
            exitProcess(0)
        }
        BeverageMachineUiState.PaymentSuccess -> {
            viewModel.sendFinalOrderToMachine()
            println("You have selected ${viewModel.order.beverage.name}")
            println(viewModel.order.decorator.toString())
            println("Price -> "+ String.format("%.2f", viewModel.order.price))
        }

        BeverageMachineUiState.BeverageOrderSendSuccess -> println("Enjoy your drink!!")
    }
}
