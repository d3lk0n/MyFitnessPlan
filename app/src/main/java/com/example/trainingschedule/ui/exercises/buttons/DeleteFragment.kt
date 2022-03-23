package com.example.trainingschedule.ui.exercises.buttons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.trainingschedule.R
import com.example.trainingschedule.databinding.FragmentDeleteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DeleteFragment : Fragment(){

    private val args by navArgs<DeleteFragmentArgs>()
    private val deleteViewModel by viewModel<DeleteViewModel> {
        parametersOf(args.id)
    }

    private var _binding: FragmentDeleteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.root.findViewById<Button>(R.id.button_delete_yes).setOnClickListener {
            onDeleteYesClicked(deleteViewModel.id)
        }

        binding.root.findViewById<Button>(R.id.button_delete_no).setOnClickListener {
            onDeleteNoClicked()
        }

        return root
    }

    private fun onDeleteYesClicked(id: Long) {
        deleteViewModel.deleteInRepo(id)
        findNavController().navigate(
            DeleteFragmentDirections.actionNavigationDeleteToNavigationExercises()
        )
    }

    private fun onDeleteNoClicked() {
        findNavController().navigate(
            DeleteFragmentDirections.actionNavigationDeleteToNavigationExercises()
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
