package com.example.network_mobile_client.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.network_mobile_client.MainActivity
import com.example.network_mobile_client.network.ClientSocket
import kotlin.concurrent.thread
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.network_mobile_client.databinding.FragmentChatBinding
import com.example.network_mobile_client.ui.chat.adapter.ChatRecyclerViewAdapter
import java.io.IOException
import java.net.SocketException
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

class ChatFragment(hostIP: String) : Fragment() {
    private lateinit var socket: ClientSocket
    private lateinit var chatView: RecyclerView
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChatBinding.inflate(inflater, container, false)

        initViewAdapter(binding)
        initSocket()
        initBtn(binding)

        return binding.root
    }

    private fun initSocket() {
        Thread {
            Log.d("socket", "여긴도착")
            socket = ClientSocket()
            while (true) {
                val inputStream = socket.getInputStream()
                try {
                    if(inputStream.available() > 0) {
                        inputStream.bufferedReader(Charsets.UTF_8).forEachLine {
                            val msg = it
                            mainHandler.post {
                                run {
                                    (chatView.adapter as ChatRecyclerViewAdapter).addChatData(
                                        msg,
                                        Calendar.getInstance()
                                    )
                                    (chatView.adapter as ChatRecyclerViewAdapter).notifyItemInserted((chatView.adapter as ChatRecyclerViewAdapter).itemCount)
                                }
                            }
                        }
                    }
                } catch (_: SocketException) {
                } catch (_: IOException) {
                }
            }
        }.start()
    }

    private fun initBtn(binding: FragmentChatBinding) {
        binding.btnSubmit.setOnClickListener {
            thread {
                val edtMessage = binding.edtMessage.text.toString()
                try {
                    socket.sendData(edtMessage)
                    Log.d("socket", edtMessage)
                } catch (_: UninitializedPropertyAccessException) {}
            }
        }

        binding.imgbtnQuit.setOnClickListener {
            try {
                socket.closeConnect()
            } catch (_: UninitializedPropertyAccessException) {}

            val mActivity = activity as MainActivity
            mActivity.changeFragment(0)
        }
    }

    private fun initViewAdapter(binding: FragmentChatBinding) {
        chatView = binding.recyclerMessages
        chatView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        chatView.adapter = ChatRecyclerViewAdapter(binding, mutableListOf())
        chatView.addItemDecoration(
            DividerItemDecoration(
                activity, LinearLayoutManager.VERTICAL
            )
        )
    }

    companion object {
        fun newInstance(hostIP: String) = ChatFragment(hostIP)
    }
}