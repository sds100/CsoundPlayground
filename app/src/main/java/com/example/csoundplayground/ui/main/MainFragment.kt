package com.example.csoundplayground.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.csounds.CsoundObj
import com.example.csoundplayground.R
import kotlinx.android.synthetic.main.main_fragment.view.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val csoundObj = CsoundObj(false, true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.buttonTest.setOnClickListener {

            with(resources.openRawResource(R.raw.helloworld).bufferedReader()) {
                val file = createTempFile(readText())
                csoundObj.startCsound(file)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        csoundObj.stop()
    }

    fun createTempFile(csd: String): File? {
        var f: File? = null

        try {
            f = File.createTempFile("temp", ".csd", requireContext().cacheDir)
            val fos = FileOutputStream(f)
            fos.write(csd.toByteArray())
            fos.close()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return f
    }
}