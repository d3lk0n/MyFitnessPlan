package com.example.trainingschedule.ui.exercises.buttons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingschedule.R
import com.example.trainingschedule.databinding.FragmentEditBinding
import com.example.trainingschedule.databinding.FragmentHomeBinding
import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.exercises.ExerciseListAdapter
import com.example.trainingschedule.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.Exception


class EditFragment : Fragment() {

    private val args by navArgs<EditFragmentArgs>()
    private val editViewModel by viewModel<EditViewModel> {
       parametersOf(args.exercise)
    }


    private var _binding: FragmentEditBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.root.findViewById<Button>(R.id.button_edit_save).setOnClickListener {
            onSaveClicked()
        }

        return root
    }

    private fun onSaveClicked() {
        val exercise = editViewModel.exercise

        exercise.name = binding.inputName.text.toString()
        exercise.description = binding.inputDescription.text.toString()

        val difficulty = binding.inputDifficulty.text.toString()
        if (difficulty.all { Character.isDigit(it)})
            exercise.difficulty = difficulty.toLong()
        else
            exercise.difficulty = 1

        editViewModel.editInRepo(exercise)

        findNavController().navigate(
            EditFragmentDirections.actionNavigationEditToNavigationExercises()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}