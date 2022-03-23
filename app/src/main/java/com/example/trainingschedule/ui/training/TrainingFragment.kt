package com.example.trainingschedule.ui.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingschedule.databinding.FragmentTrainingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrainingFragment : Fragment() {

    private val trainingViewModel by viewModel<TrainingViewModel>()
    //private val trainingViewModel by viewModel<TrainingViewModel> {
    //    parametersOf(args)
    //}
    private var _binding: FragmentTrainingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTrainingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewTraining.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TrainingListAdapter(
                onShortExerciseClicked = ::onShortExerciseClicked
            ).also { adapter ->
                trainingViewModel.items.observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
            }
        }

        return root
    }

    private fun onShortExerciseClicked(id: Long) {
        findNavController().navigate(
            TrainingFragmentDirections.actionNavigationTrainingToNavigationShortExercises(id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}