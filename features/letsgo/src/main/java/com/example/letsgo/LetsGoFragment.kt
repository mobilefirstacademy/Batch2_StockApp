package com.example.letsgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class LetsGoFragment : Fragment() {
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lets_go, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.text).text = getString(R.string.lets_start, name)
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String) =
            LetsGoFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, name)
                }
            }
    }
}

private const val NAME = "NAME"
