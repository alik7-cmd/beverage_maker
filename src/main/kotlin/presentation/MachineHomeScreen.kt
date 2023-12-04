package presentation

import common.ServiceLocator
import java.awt.Color
import java.awt.Font
import java.awt.GridLayout
import java.net.URI
import javax.swing.*


class MachineHomeScreen : BaseView() {

    init {
        val frame =  JFrame("Awesome Coffee Machine")
        frame.setSize(700,500)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        val panelForBeverage = JPanel()
        panelForBeverage.setBackground(Color.WHITE)
        panelForBeverage.setLayout(GridLayout(2, 3, 5,5))
        val viewModel = HomeViewModel(ServiceLocator.getBeverageRepository())
        viewModel.getAllBeverage()
        observe(viewModel.observer.subject.state, frame, panelForBeverage)
    }
}

private fun observe(state : BeverageMachineUiState,
                    frame : JFrame,
                    panelForBeverage : JPanel){
    when(state){

        is BeverageMachineUiState.BeverageListSuccess ->{
            state.listOrBeverage.forEach {
                val button = JButton("<html>${it.name}<br>${it.price}</html>")
                panelForBeverage.add(button)
                button.icon = ImageIcon(URI.create(it.image).toURL())//URI.create(it.image).toURL()
                button.verticalTextPosition = JButton.BOTTOM
                button.horizontalTextPosition = JButton.CENTER
                button.iconTextGap = 10
                button.setFont(Font("Arial", Font.PLAIN, 15))
            }
            val scrollPaneCenter = JScrollPane(panelForBeverage)
            frame.add(scrollPaneCenter)
            frame.isVisible = true
        }

        is BeverageMachineUiState.Error -> println(state.msg)
        else -> {}
    }

}