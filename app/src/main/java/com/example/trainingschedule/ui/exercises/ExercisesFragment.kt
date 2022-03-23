package com.example.trainingschedule.ui.exercises

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingschedule.R
import com.example.trainingschedule.databinding.FragmentExercisesBinding
import com.example.trainingschedule.ui.data.ExerciseRepository
import com.example.trainingschedule.ui.data.ExerciseRepositoryImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExercisesFragment : Fragment() {
    private val exercisesViewModel by viewModel<ExercisesViewModel>()
    private var _binding: FragmentExercisesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentExercisesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewExercise.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ExerciseListAdapter(
                onEditClicked = ::onEditClicked,
                onDeleteClicked = ::onDeleteClicked
            ).also { adapter ->
                exercisesViewModel.items.observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
            }
        }

        binding.root.findViewById<Button>(R.id.button_add_single_exercise).setOnClickListener {
            onAddClicked()
        }

        return root
    }

    private fun onAddClicked() {
        val repo = exercisesViewModel.source
        val listLength = exercisesViewModel.listLength
        val exercise = repo.createEmptyExercise(listLength)
        findNavController().navigate(
            ExercisesFragmentDirections.actionNavigationExercisesToNavigationEdit(exercise)
        )
    }

    private fun onEditClicked(exercise: Exercise) {
        findNavController().navigate(
            ExercisesFragmentDirections.actionNavigationExercisesToNavigationEdit(exercise)
        )
    }

    private fun onDeleteClicked(id: Long) {
        findNavController().navigate(
            ExercisesFragmentDirections.actionNavigationExercisesToNavigationDelete(id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}