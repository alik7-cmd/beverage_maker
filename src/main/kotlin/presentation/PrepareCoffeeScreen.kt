package presentation

import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JSlider


class PrepareCoffeeScreen : BaseView() {

    fun createView(){
        val frame =  JFrame("Awesome Coffee Machine")
        frame.setSize(700,500)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        val panelForSelection = JPanel()
        val s = JSlider(0,5,1)

        // paint the ticks and tracks

        // paint the ticks and tracks
        s.setPaintTrack(true)
        s.setPaintTicks(true)
        s.setPaintLabels(true)
    }
}
