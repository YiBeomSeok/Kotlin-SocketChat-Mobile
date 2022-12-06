package com.example.network_mobile_client.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.network_mobile_client.MainActivity
import com.example.network_mobile_client.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        initBtn()

        return binding.root
    }

    private fun initBtn() {
        val mActivity = activity as MainActivity
        binding.btnChatStart.setOnClickListener {
            val hostIP = binding.edtHostIp.text.toString()
            mActivity.changeFragment(1, hostIp = hostIP)
        }
    }
}