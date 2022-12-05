package com.example.network_mobile_client.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.network_mobile_client.MainActivity
import com.example.network_mobile_client.databinding.FragmentChatBinding
import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.concurrent.thread

class ChatFragment : Fragment() {
    companion object {
        fun newInstance() = ChatFragment()
    }

    private lateinit var socket: Socket
    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChatBinding.inflate(inflater, container, false)
        val message = binding.edtMessage.text

        thread{
            connect(8082)
            var isRead = false
            while(!isRead) {
                isRead = read()
            }
        }

        binding.btnSubmit.setOnClickListener {
            thread {
                // val testData =
                //    "GET /test.jpg HTTP/1.1\r\nHost: localhost:8082\r\nUser-Agent: Mozilla/5.0 Chrome/99.99\r\nContent-Type: Text\r\nContent-Length: 15\r\n\r\nHello, network!"

                val edtMessage = binding.edtMessage.text.toString()
                sendData(edtMessage)
                Log.d("socket", edtMessage)

                // GET 요청에 대한 Response를 통해 채팅 리스트를 불러온다.

                // 채팅 리스트를 기반으로 fragment의 채팅 리스트를 구현한다.
            }
        }

        binding.imgbtnQuit.setOnClickListener {
            flush()
            closeConnect()

            val mActivity = activity as MainActivity
            mActivity.changeFragment(0)
        }

        return binding.root
    }

    private fun connect(portNumber: Int) {
        try {
            //10.0.2.2
            //socket = Socket("192.168.148.182", portNumber)
            //socket = Socket("10.0.2.2", portNumber)
            socket = Socket("192.168.85.22", portNumber)

            outputStream = socket.getOutputStream()
            inputStream = socket.getInputStream()
        } catch (e: Exception) {
            Log.d("socket", "socket connect exception")
            Log.d("socket", "e: $e")
        }
    }

    private fun sendData(data: String) {
        outputStream.write(
            (data + "\n").toByteArray(Charsets.UTF_8)
        )
    }

    private fun flush() {
        outputStream.flush()
    }

    private fun read(): Boolean {
        var isRead = false
        if (inputStream.available() > 0) {
            isRead = true
        }
        inputStream.bufferedReader(Charsets.UTF_8).forEachLine {
            Log.d("socket", it)
        }
        return isRead
    }

    private fun closeConnect() {
        outputStream.close()
        inputStream.close()
        socket.close()
    }

    private fun request() {

    }
}