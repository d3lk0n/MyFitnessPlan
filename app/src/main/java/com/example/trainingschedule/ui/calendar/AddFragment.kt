package com.example.trainingschedule.ui.calendar

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
import com.example.trainingschedule.databinding.FragmentAddBinding
import com.example.trainingschedule.databinding.FragmentEditBinding
import com.example.trainingschedule.databinding.FragmentHomeBinding
import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.exercises.ExerciseListAdapter
import com.example.trainingschedule.ui.exercises.buttons.EditFragmentDirections
import com.example.trainingschedule.ui.home.HomeViewModel
import org.json.JSONObject.NULL
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class AddFragment : Fragment() {

    private val args by navArgs<AddFragmentArgs>()
    private val addViewModel by viewModel<AddViewModel>{
        parametersOf(args.list)
    }


    private var _binding: FragmentAddBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewAddExercises.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AddListAdapter(
                onAddClicked = ::onAddClicked
            ).also { adapter ->
                addViewModel.items.observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
            }
        }

        return root
    }

    private fun onAddClicked(exercise: Exercise) {
        Log.d("Test", "Amount of Elements already in List: ${addViewModel.list.size}")

        val list: List<Exercise> =
            if (addViewModel.list[0].id.toInt() == 0)
                mutableListOf(exercise)
            else
                addViewModel.list.toList() + exercise

        findNavController().navigate(
            AddFragmentDirections.actionNavigationAddToNavigationCalendar(list.toTypedArray())
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}