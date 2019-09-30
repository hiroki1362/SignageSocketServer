package infodex_dentsu.jp.signagesocketserver.server

import android.arch.lifecycle.MutableLiveData
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import timber.log.Timber
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.UnknownHostException

class SignageSocketServer: WebSocketServer {

    @Throws(UnknownHostException::class)
    constructor(port: Int): super(InetSocketAddress(port))
    constructor(address: InetSocketAddress): super(address)

    private var messageData = MutableLiveData<String>()

    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        conn?.send("Welcome to SignageSocketServer!")
        Timber.d("Server: ${conn?.remoteSocketAddress?.address?.hostAddress} entered!")
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        Timber.d("${conn.toString()} is close.")
    }

    override fun onMessage(conn: WebSocket?, message: String) {
        messageData.postValue(message)
        Timber.d("Message is 「${message}」")
    }

    override fun onStart() {
        Timber.d("Server Start!!")
        connectionLostTimeout = 0
        connectionLostTimeout = 100
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        ex?.printStackTrace()
    }

    fun getMessageData(): MutableLiveData<String> {
        return messageData
    }
}