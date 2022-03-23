package com.example.trainingschedule.ui.training.shortexercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingschedule.databinding.FragmentExercisesBinding
import com.example.trainingschedule.databinding.FragmentShortExercisesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShortExercisesFragment : Fragment() {

    private val args by navArgs<ShortExercisesFragmentArgs>()
    private val shortExercisesViewModel by viewModel<ShortExercisesViewModel> {
        parametersOf(args.id)
    }

    private var _binding: FragmentShortExercisesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShortExercisesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewShortExercises.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ShortExercisesListAdapter().also { adapter ->
                shortExercisesViewModel.items.observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}