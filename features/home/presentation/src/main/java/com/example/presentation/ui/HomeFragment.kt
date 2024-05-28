package com.example.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding
import com.example.presentation.di.ViewModelFactoryProvider
import com.example.presentation.viewmodels.HomeViewModel

class HomeFragment : Fragment() {
    private var picId: Int? = null
    private var greeting: String? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        arguments?.let { args ->
            val (picId, greeting) = args.obtainHomeFragmentState()
            this.picId = picId
            this.greeting = greeting
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tvGreeting.text = greeting ?: return
            pic.setImageResource(picId ?: com.google.android.material.R.drawable.mtrl_ic_error)
            refreshTimeButton.setOnClickListener {
                viewModel.refreshTime()
            }
            letsgoButton.setOnClickListener {
                val name = name.text.toString().takeIf { it != "" }
                if (name.isNullOrEmpty()) {
                    view.findNavController().navigate(R.id.action_homeFragment_to_letsgo_nav_graph)
                } else {
                    val bundle = Bundle()
                    bundle.putString(NAME, name)
                    view.findNavController().navigate(R.id.action_homeFragment_to_letsgo_nav_graph, bundle)
                }
            }
        }

        viewModel.timeLivedata.observe(viewLifecycleOwner) { result ->
            result.onFailure { ex ->
                Toast.makeText(requireContext(), ex.message, Toast.LENGTH_LONG).show()
            }.onSuccess { time ->
                binding.timeLabel.text = time.timeLine
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            saveHomeFragmentState(picId, greeting)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { state ->
            val (picId, greeting) = state.obtainHomeFragmentState()
            this.picId = picId
            this.greeting = greeting
        }
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