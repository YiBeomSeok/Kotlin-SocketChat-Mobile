package com.example.network_mobile_client.ui.file_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.network_mobile_client.R

class FileListFragment : Fragment() {

    companion object {
        fun newInstance() = FileListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_file_list, container, false)
    }

    /*
    GET /fileList HTTP/1.1
    accept: application/json
    content-type: application/json


    HTTP/1.1 200 OK
    "Content-Type: json; charset=utf-8

    "file1 file2 file3 file4 file5 ..."

     */

    /*
    POST /uploadFile HTTP/1.1
    accept: application/json
    content-type: application/json
    file

    HTTP/1.1 200 OK
    "Content-Type: json; charset=utf-8


     */
}