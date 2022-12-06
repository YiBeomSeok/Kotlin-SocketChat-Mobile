package com.example.network_mobile_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.network_mobile_client.ui.chat.ChatFragment
import com.example.network_mobile_client.ui.file_list.FileListFragment
import com.example.network_mobile_client.ui.main.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, MainFragment.newInstance())
                .commitNow()
        }
    }

    fun changeFragment(index: Int, hostIp: String = "10.0.2.2") {
        when (index) {
            0 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, MainFragment.newInstance()).commit()
            }

            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, ChatFragment.newInstance(hostIp)).commit()
            }

//            2 -> {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_container, FileListFragment.newInstance())
//                    .commit()
//            }
        }
    }
}