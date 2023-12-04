package presentation

import java.awt.Color
import javax.swing.UIManager

open class BaseView {

    fun setNimbus(){
        UIManager.put("nimbusBase", Color.LIGHT_GRAY)
        try {
            for (info in UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus" == info.name) {
                    UIManager.setLookAndFeel(info.className)
                    break
                }
            }
        } catch (e: Exception) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName())
            } catch (_: Exception) {
            }
        }
    }
}