package com.example.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.example.home.R
import com.example.presentation.di.ViewModelFactoryProvider
import com.example.presentation.viewmodels.HomeViewModel
import interactors.HomeInteractor
import androidx.navigation.findNavController

class HomeFragment : Fragment() {
    private var picId: Int? = null
    private var greeting: String? = null

    private lateinit var greetingView: TextView
    private lateinit var pictureView: ImageView
    private lateinit var nameView: EditText
    private lateinit var letsgoButton: Button
    private lateinit var refreshTimeButton: ImageButton
    private lateinit var timeLabel: TextView
    // TODO: Почитайте про view binding и используйте его

    private val viewModel: HomeViewModel by lazy {
        // TODO: Почитайте про viewModels() делегат и используйте его
        ViewModelProvider(
            viewModelStore,
            ViewModelFactoryProvider.INSTANCE.getViewModelFactory(),
        ).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let { args ->
            val (picId, greeting) = args.obtainHomeFragmentState()
            this.picId = picId
            this.greeting = greeting
        }
        return inflater.inflate(R.layout.fragment_home, container, false)

        //wejf;lskjf;alskdfj;l
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            initViews()

            greetingView.text = greeting ?: return
            pictureView.setImageResource(picId ?: com.google.android.material.R.drawable.mtrl_ic_error)
            refreshTimeButton.setOnClickListener {
                viewModel.refreshTime()
            }
            letsgoButton.setOnClickListener {
                val name = nameView.text.toString().takeIf { it != "" }
                if (name.isNullOrEmpty()) {
                    findNavController().navigate(R.id.action_homeFragment_to_letsgo_nav_graph)
                } else {
                    val bundle = Bundle()
                    bundle.putString(NAME, name)
                    findNavController().navigate(R.id.action_homeFragment_to_letsgo_nav_graph, bundle)
                }
            }
        }

        viewModel.timeLivedata.observe(viewLifecycleOwner) { result ->
            result.onFailure { ex ->
                Toast.makeText(requireContext(), ex.message, Toast.LENGTH_LONG).show()
            }.onSuccess { time ->
                timeLabel.text = time.timeLine
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            saveHomeFragmentState(picId, greeting)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { state ->
            val (picId, greeting) = state.obtainHomeFragmentState()
            this.picId = picId
            this.greeting = greeting
        }
    }

    private fun View.initViews() {
        greetingView = findViewById(R.id.greeting)
        pictureView = findViewById(R.id.pic)
        nameView = findViewById(R.id.name)
        letsgoButton = findViewById(R.id.letsgo_button)
        refreshTimeButton = findViewById(R.id.refresh_time_button)
        timeLabel = findViewById(R.id.time_label)
    }

    companion object {
        @JvmStatic
        fun newInstance(picId: Int, greeting: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    saveHomeFragmentState(picId, greeting)
                }
            }
    }
}

private fun Bundle.saveHomeFragmentState(picId: Int?, greeting: String?) {
    putInt(PIC_ID, picId ?: 0)
    putString(GREETING, greeting)
}

private fun Bundle.obtainHomeFragmentState() = object {
    private var picId = getInt(PIC_ID)
    private var greeting = getString(GREETING)
    operator fun component1() = picId
    operator fun component2() = greeting
}

private const val PIC_ID = "PIC_ID"
private const val GREETING = "GREETING"
private const val NAME = "NAME"