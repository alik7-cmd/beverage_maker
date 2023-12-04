package presentation

import javax.swing.SwingUtilities




fun main() {

    SwingUtilities.invokeLater { val machineHomeScreen = MachineHomeScreen()
        machineHomeScreen.setNimbus() }


}
