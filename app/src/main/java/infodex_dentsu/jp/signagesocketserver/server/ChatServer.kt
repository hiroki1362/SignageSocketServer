package infodex_dentsu.jp.signagesocketserver.server

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.InetSocketAddress
import java.net.UnknownHostException
import java.nio.ByteBuffer

class ChatServer : WebSocketServer {

    @Throws(UnknownHostException::class)
    constructor(port: Int) : super(InetSocketAddress(port)) {
    }

    constructor(address: InetSocketAddress) : super(address) {}

    override fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
        conn.send("Welcome to the server!") //This method sends a message to the new client
        broadcast("new connection: " + handshake.resourceDescriptor) //This method sends a message to all clients connected
        println(conn.remoteSocketAddress.address.hostAddress + " entered the room!")
    }

    override fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {
        broadcast(conn.toString() + " has left the room!")
        println(conn.toString() + " has left the room!")
    }

    override fun onMessage(conn: WebSocket, message: String) {
        broadcast(message)
        println(conn.toString() + ": " + message)
    }

    override fun onMessage(conn: WebSocket?, message: ByteBuffer?) {
        broadcast(message!!.array())
        println(conn.toString() + ": " + message)
    }

    override fun onError(conn: WebSocket?, ex: Exception) {
        ex.printStackTrace()
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    override fun onStart() {
        println("Server started!")
        connectionLostTimeout = 0
        connectionLostTimeout = 100
    }

    companion object {

        @Throws(InterruptedException::class, IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            var port = 8887 // 843 flash policy port
            try {
                port = Integer.parseInt(args[0])
            } catch (ex: Exception) {
            }

            val s = ChatServer(port)
            s.start()
            println("ChatServer started on port: " + s.port)

            val sysin = BufferedReader(InputStreamReader(System.`in`))
            while (true) {
                val `in` = sysin.readLine()
                s.broadcast(`in`)
                if (`in` == "exit") {
                    s.stop(1000)
                    break
                }
            }
        }
    }
}

