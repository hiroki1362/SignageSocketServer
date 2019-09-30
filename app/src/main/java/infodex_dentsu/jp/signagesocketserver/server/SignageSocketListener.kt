package infodex_dentsu.jp.signagesocketserver.server

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import java.net.SocketException

class SignageSocketListener(context: Context) {

    private val context: Context = context
    private lateinit var socketServer: SignageSocketServer
    lateinit var message: MutableLiveData<String>

    fun getIpAddress(): String {
        when (isOnline()) {
            true -> return getMyIpAddress()
            false -> return "ネットワークに接続していません。"
        }
    }

    fun startServer(port: Int) {
        socketServer = SignageSocketServer(port)
        socketServer.start()
        message = socketServer.getMessageData()
    }

    fun stopServer() {
        socketServer.stop()
    }

    private fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo

        return activeNetworkInfo.isConnected
    }

    private fun getMyIpAddress(): String {
        var text = ""
        val wifiManager: WifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        try {
            var ip = wifiManager.connectionInfo.ipAddress
            text = String.format("%02d.%02d.%02d.%02d", (ip shr 0) and 0xff, (ip shr 8) and 0xff, (ip shr 16) and 0xff, (ip shr 24) and 0xff)
        } catch (e: SocketException) {
            e.printStackTrace()
            text = "エラーが発生しました"
        }

        return text
    }

}