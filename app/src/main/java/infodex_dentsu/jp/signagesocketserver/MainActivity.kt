package infodex_dentsu.jp.signagesocketserver

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import infodex_dentsu.jp.signagesocketserver.controller.NavigationController
import infodex_dentsu.jp.signagesocketserver.server.SignageSocketListener
import infodex_dentsu.jp.signagesocketserver.utils.obtainViewModel
import infodex_dentsu.jp.signagesocketserver.viewmodel.SignageViewModel
import infodex_dentsu.jp.signagesocketserver.viewmodel.StartViewModel
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val port = 5000
    private val controller: NavigationController = NavigationController(this)
    lateinit var socketListener: SignageSocketListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFullScreen()
        controller.navigateToStartDisplay()
    }

    override fun onStart() {
        thread {
            socketListener = SignageSocketListener(this)
            socketListener.startServer(port)
        }
        super.onStart()
    }

    override fun onStop() {
        socketListener.stopServer()
        super.onStop()
    }

    fun getIpAddressFromSocketListener(): String {
        return socketListener.getIpAddress() + ":" + port
    }

    fun getStartViewModel(): StartViewModel = obtainViewModel(StartViewModel::class.java)
    fun getSignageViewModel(): SignageViewModel = obtainViewModel(SignageViewModel::class.java)

    /**
     * Set fullscreen.
     */
    private fun setFullScreen() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LOW_PROFILE
            or View.SYSTEM_UI_FLAG_IMMERSIVE
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}
