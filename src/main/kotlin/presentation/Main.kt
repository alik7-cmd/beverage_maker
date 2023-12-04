package presentation

import common.ServiceLocator


fun main() {
    val machineHomeScreen = MachineHomeScreen(ServiceLocator.getBeverageRepository())
    machineHomeScreen.setNimbus()
    machineHomeScreen.createFrame()

}
