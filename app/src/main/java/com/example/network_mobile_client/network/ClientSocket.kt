package com.example.network_mobile_client.network

import android.util.Log
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class ClientSocket(private val host: String = "10.0.2.2") {
    lateinit var socket: Socket
    private lateinit var inputStream: DataInputStream
    private lateinit var outputStream: DataOutputStream

    init {
        connect()
    }

    fun getInputStream(): DataInputStream {
        return inputStream
    }

    fun sendData(data: String) {
        outputStream.write(
            (data + "\n").toByteArray(Charsets.UTF_8)
        )
        outputStream.flush()
    }

    fun receiveData(): String {
        val sb = StringBuilder()
        if (inputStream.available() > 0) {
            val msg = inputStream.bufferedReader(Charsets.UTF_8).readLine()
            sb.append(msg)

            return sb.toString()
        }
        return ""
    }

    fun closeConnect() {
        outputStream.close()
        inputStream.close()
        socket.close()
    }

    private fun connect() {
        try {
            socket = Socket(HOST, PORT_NUMBER)
            outputStream = DataOutputStream(socket.getOutputStream())
            inputStream = DataInputStream(socket.getInputStream())
        } catch (e: Exception) {
            Log.d("socket", "socket connect exception")
            Log.d("socket", "e: $e")
        }
    }

    companion object {
        const val PORT_NUMBER = 8082

        // ADM
        // "10.0.2.2"
        // WiFi
        // "192.168.85.22"
        var HOST = "10.0.2.2"
    }
}