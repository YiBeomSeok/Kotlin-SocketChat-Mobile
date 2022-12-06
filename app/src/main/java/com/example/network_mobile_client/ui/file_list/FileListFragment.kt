package com.example.network_mobile_client.ui.file_list

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import com.example.network_mobile_client.R
import com.example.network_mobile_client.databinding.FragmentFileListBinding
import com.example.network_mobile_client.network.ClientSocket
import com.example.network_mobile_client.ui.chat.adapter.ChatRecyclerViewAdapter
import java.io.*
import java.lang.Byte
import java.net.SocketException
import java.util.*

class FileListFragment : Fragment() {
    private lateinit var socket: ClientSocket
    private val mainHandler = Handler(Looper.getMainLooper())

    private lateinit var want: Uri
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri: Uri? ->
        if (uri != null) {
            want = uri
        }
    }

    companion object {
        fun newInstance() = FileListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFileListBinding.inflate(inflater, container, false)

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        getContent.launch("image/*")

        return binding.root
    }

    private fun initSocket() {
        Thread {
            Log.d("socket", "initSocket")
            socket = ClientSocket()
            val out = PrintWriter(
                BufferedWriter(OutputStreamWriter(socket.socket.getOutputStream())),
                true
            )
            val msg = "ScreenShot_20220530-124339"
            out.flush()
            println("데이터 찾는중")
            val dis = DataInputStream(FileInputStream(File(Environment.getExternalStorageDirectory(), msg)))

            val dos = DataOutputStream(socket.socket.getOutputStream())

            val buf = ByteArray(1024)

            var totalReadBytes = 0
            var readBytes = 0

            while(true) {
                readBytes = dis.read(buf)
                if(readBytes <= 0) break
                dos.write(buf, 0, readBytes)
                totalReadBytes += readBytes
            }

            dos.close()

        }.start()
    }
}