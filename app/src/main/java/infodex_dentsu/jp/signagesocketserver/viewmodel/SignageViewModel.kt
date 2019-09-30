package infodex_dentsu.jp.signagesocketserver.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean

class SignageViewModel(context: Application): AndroidViewModel(context) {

    val logoImageVisibility: ObservableBoolean = ObservableBoolean()
    val image1Visibility: ObservableBoolean = ObservableBoolean()
    val image2Visibility: ObservableBoolean = ObservableBoolean()
    val image3Visibility: ObservableBoolean = ObservableBoolean()
    val image4Visibility: ObservableBoolean = ObservableBoolean()
    val image5Visibility: ObservableBoolean = ObservableBoolean()

    fun start() {
        logoImageVisibility.set(true)
        image1Visibility.set(true)
        image2Visibility.set(false)
        image3Visibility.set(false)
        image4Visibility.set(false)
        image5Visibility.set(false)
    }

    fun setDisplayImage(displayId: Int) {
        when (displayId) {
            1 -> {
                logoImageVisibility.set(false)
                image1Visibility.set(true)
                image2Visibility.set(false)
                image3Visibility.set(false)
                image4Visibility.set(false)
                image5Visibility.set(true)
            }
            2 -> {
                logoImageVisibility.set(false)
                image1Visibility.set(true)
                image2Visibility.set(true)
                image3Visibility.set(false)
                image4Visibility.set(false)
                image5Visibility.set(false)
            }
            3 -> {
                logoImageVisibility.set(false)
                image1Visibility.set(false)
                image2Visibility.set(true)
                image3Visibility.set(true)
                image4Visibility.set(false)
                image5Visibility.set(false)
            }
            4 -> {
                logoImageVisibility.set(false)
                image1Visibility.set(false)
                image2Visibility.set(false)
                image3Visibility.set(true)
                image4Visibility.set(true)
                image5Visibility.set(false)
            }
            5 -> {
                logoImageVisibility.set(false)
                image1Visibility.set(false)
                image2Visibility.set(false)
                image3Visibility.set(false)
                image4Visibility.set(true)
                image5Visibility.set(true)
            }
        }
    }
}